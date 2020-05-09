package io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers;

import static io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.DbusHelpers.NM_BUS_PATH;
import static io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.DbusHelpers.getRemoteObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.types.Variant;
import org.freedesktop.networkmanager.Device;

public final class PropertiesExtractor {
  /**
   * Get all properties from this device. This method will attempt to extract all of the properties
   * from the interface that the provided {@link Device} directly implements as well as all other
   * property-providing interfaces at the path of the provided device.
   *
   * @param device a device to get properties from
   * @return Get all properties from all interfaces this device implements including that of the
   *     device itself
   * @throws IOException
   */
  public static Map<String, Map<String, Variant<?>>> GetAllFromDevice(Device device)
      throws IOException {
    Map<String, Map<String, Variant<?>>> out = new HashMap<>();
    Properties deviceProps = getRemoteObject(NM_BUS_PATH, device.getObjectPath(), Properties.class);
    for (Class<?> interf : device.getClass().getInterfaces()) {
      String dbusInterfaceName = interf.getAnnotation(DBusInterfaceName.class).value();
      out.put(dbusInterfaceName, deviceProps.GetAll(dbusInterfaceName));
      for (Class<?> subint : interf.getInterfaces()) {
        if (subint.equals(Properties.class) || subint.equals(DBusInterface.class)) continue;
        String dbusParentInterfaceName = subint.getAnnotation(DBusInterfaceName.class).value();
        out.put(dbusParentInterfaceName, deviceProps.GetAll(dbusParentInterfaceName));
      }
    }
    return out;
  }
}
