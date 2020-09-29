package io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.NMInterop;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.NetworkManager;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.NetworkManager.PropertyNames;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.DBusExecutionException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

public class DbusHelpers {
  public static String NM_BUS_PATH = "org.freedesktop.NetworkManager";

  public static <I extends DBusInterface> I getRemoteObject(
      String _busname, String _objectpath, Class<I> _type) throws IOException {
    try {
      return NMInterop.getActiveInstance()
          .getDbusConn()
          .getRemoteObject(_busname, _objectpath, _type);
    } catch (DBusException e) {
      throw new IOException(e);
    }
  }

  /**
   * Get a property value from an instance of an interface which extends {@link Properties}. This
   * WILL NOT WORK to find Properties values of ancestors of objectWithProperties.
   *
   * @param objectWithProperties an object that extends Properties
   * @param propertyName the name of the property to retrieve
   * @return the value of the property
   * @throws IOException if there is a DBus exception
   */
  public static <A, P extends Properties> A getProperty(P objectWithProperties, String propertyName)
      throws IOException {
    Properties propertiesObject =
        getRemoteObject(NM_BUS_PATH, objectWithProperties.getObjectPath(), Properties.class);
    Class<?>[] interfaces = objectWithProperties.getClass().getInterfaces();
    // If any of the interfaces of this object (this is to account for it being
    // a Proxy) have a DBus interface name...
    if (Arrays.stream(interfaces)
        .anyMatch(aClass -> aClass.isAnnotationPresent(DBusInterfaceName.class))) {
      List<Class<?>> validInterfaces =
          Arrays.stream(interfaces)
              .filter(aClass -> aClass.isAnnotationPresent(DBusInterfaceName.class))
              .collect(Collectors.toList());

      // We only keep track of this to have something ready-made to throw in
      // case we never find the user-specified property
      DBusExecutionException possibleExecutionException = null;
      for (Class<?> i : validInterfaces) {
        try {
          return propertiesObject.Get(
              i.getAnnotation(DBusInterfaceName.class).value(), propertyName);
        } catch (DBusExecutionException e) {
          if (e.getType().equals("org.freedesktop.DBus.Error.InvalidArgs")) {
            // Caused by 'no such property' in this particular interface. Expected behavior
            possibleExecutionException = e;
          }
        }
      }
      throw possibleExecutionException == null
          ? new IllegalArgumentException()
          : new IllegalArgumentException(possibleExecutionException);
    } else {
      throw new IllegalStateException(
          "Illegal attempt to get properties from a class that is not annotated with a DBus interface name.");
    }
  }

  public static String getNetworkManagerVersion() throws IOException {
    NetworkManager networkManager =
        getRemoteObject(NM_BUS_PATH, "/org/freedesktop/NetworkManager", NetworkManager.class);
    return getProperty(networkManager, PropertyNames.Version).toString();
  }

  /**
   * Compares two version numbers that have multiple decimal points.
   *
   * @param str1 the first version to compare
   * @param str2 the second version to compare
   * @return -1 if str1 is less than str2, 0 if they are equal, and 1 if str1 is greater than str2
   */
  public static int versionCompare(String str1, String str2) {
    String[] vals1 = str1.split("\\.");
    String[] vals2 = str2.split("\\.");
    int i = 0;
    // set index to first non-equal ordinal or length of shortest version string
    while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
      i++;
    }
    // compare first non-equal ordinal number
    if (i < vals1.length && i < vals2.length) {
      int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
      return Integer.signum(diff);
    }
    // the strings are equal or one string is a substring of the other
    // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
    return Integer.signum(vals1.length - vals2.length);
  }
}
