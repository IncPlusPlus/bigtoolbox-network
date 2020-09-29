package io.github.incplusplus.bigtoolbox.network.interfaces.lin.nm;

import static io.github.incplusplus.bigtoolbox.network.interop.lin.CLIUtils.exec;
import static io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.DbusHelpers.NM_BUS_PATH;
import static io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.DbusHelpers.getProperty;
import static io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.DbusHelpers.getRemoteObject;
import static io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.PropertiesExtractor.getDeviceProperty;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.incplusplus.bigtoolbox.network.Interface;
import io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.DbusHelpers;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device.PropertyNames;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.IP4Config;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.IP6Config;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMDeviceState;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;
import org.freedesktop.dbus.DBusMap;
import org.freedesktop.dbus.exceptions.DBusExecutionException;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.types.Variant;

public class GenericDevice implements Interface {

  final Device device;

  public GenericDevice(Device device, Class<? extends Device> expectedDeviceType) {
    if (!(expectedDeviceType.isInstance(device))) {
      throw new RuntimeException(
          "The class '"
              + expectedDeviceType.getName()
              + "' may only be constructed using a 'device' of type '"
              + expectedDeviceType.getName()
              + "'");
    }
    this.device = device;
  }

  @Override
  public String getName() throws IOException {
    Properties properties = getRemoteObject(NM_BUS_PATH, device.getObjectPath(), Properties.class);
    return properties.Get("org.freedesktop.NetworkManager.Device", Device.PropertyNames.Interface)
        .toString();
  }

  @Override
  public String getDescription() throws IOException {
    Optional<Map<String, Object>> hwDetailsOptional = getHardwareDetails();
    if (hwDetailsOptional.isEmpty()) {
      return "";
    }
    Map<String, Object> hwDetails = hwDetailsOptional.get();
    return hwDetails.getOrDefault("product", "").toString();
  }

  @Override
  public String getVendor() throws IOException {
    Optional<Map<String, Object>> hwDetailsOptional = getHardwareDetails();
    if (hwDetailsOptional.isEmpty()) {
      return "";
    }
    Map<String, Object> hwDetails = hwDetailsOptional.get();
    return hwDetails.getOrDefault("vendor", "").toString();
  }

  @Override
  public String getMacAddress() throws IOException {
    // If the version of NetworkManager is less than 1.24.0
    if (DbusHelpers.versionCompare(DbusHelpers.getNetworkManagerVersion(), "1.24.0") < 0) {
      // Then the HwAddress property in org.freedesktop.NetworkManager.Device is not yet exposed.
      // See https://gitlab.freedesktop.org/NetworkManager/NetworkManager/-/blob/master/NEWS
      try {
        return getProperty(device, "HwAddress");
      } catch (IllegalArgumentException e) {
        if (e.getCause() instanceof DBusExecutionException
            && ((DBusExecutionException) e.getCause())
                .getType()
                .equals("org.freedesktop.DBus.Error.InvalidArgs")) {
          // It is likely that this particular device simply doesn't have the HwAddress property
          return "";
        } else {
          throw e;
        }
      }
    } else {
      Properties properties =
          getRemoteObject(NM_BUS_PATH, device.getObjectPath(), Properties.class);
      return properties.Get("org.freedesktop.NetworkManager.Device", PropertyNames.HwAddress)
          .toString();
    }
  }

  @Override
  public String[] getIpAddresses() throws IOException {
    Pair<Optional<IP4Config>, Optional<IP6Config>> ipConfigs = getIPConfigs();
    List<String> addresses = new ArrayList<>();
    if (ipConfigs.getLeft().isPresent()) {
      IP4Config ip4Config = ipConfigs.getLeft().get();
      //noinspection unchecked
      for (DBusMap<String, Variant<?>> i :
          (List<DBusMap<String, Variant<?>>>)
              getProperty(ip4Config, IP4Config.PropertyNames.AddressData)) {
        addresses.add((String) i.get("address").getValue());
      }
    }
    if (ipConfigs.getRight().isPresent()) {
      IP6Config ip6Config = ipConfigs.getRight().get();
      //noinspection unchecked
      for (DBusMap<String, Variant<?>> i :
          (List<DBusMap<String, Variant<?>>>)
              getProperty(ip6Config, IP6Config.PropertyNames.AddressData)) {
        addresses.add((String) i.get("address").getValue());
      }
    }

    return addresses.toArray(String[]::new);
  }

  /**
   * Get the {@link IP4Config} and an {@link IP6Config} of this device. This is only valid when
   * {@link NMDeviceState} is {@link NMDeviceState#NM_DEVICE_STATE_ACTIVATED}
   *
   * @return a pair of optionals, possibly containing an {@link IP4Config} and an {@link IP6Config}
   * @throws IOException if any DBus-related exceptions occur
   */
  Pair<Optional<IP4Config>, Optional<IP6Config>> getIPConfigs() throws IOException {
    if (NMDeviceState.getNMDeviceState(getDeviceProperty(device, PropertyNames.State))
        == NMDeviceState.NM_DEVICE_STATE_ACTIVATED) {
      Properties properties =
          getRemoteObject(NM_BUS_PATH, device.getObjectPath(), Properties.class);
      String ip4ConfigPath =
          properties.Get("org.freedesktop.NetworkManager.Device", "Ip4Config").toString();
      String ip6ConfigPath =
          properties.Get("org.freedesktop.NetworkManager.Device", "Ip6Config").toString();
      IP4Config ip4Config = getRemoteObject(NM_BUS_PATH, ip4ConfigPath, IP4Config.class);
      IP6Config ip6Config = getRemoteObject(NM_BUS_PATH, ip6ConfigPath, IP6Config.class);
      return Pair.of(Optional.of(ip4Config), Optional.of(ip6Config));
    } else {
      return Pair.of(Optional.empty(), Optional.empty());
    }
  }

  private Optional<Map<String, Object>> getHardwareDetails() throws IOException {
    List<Map<String, Object>> allHw = getAllHardwareDetails();
    for (Map<String, Object> hwDetails : allHw) {
      if (hwDetails
          .getOrDefault(
              "logicalname",
              "this default value exists only to prevent a null pointer exception if 'logicalname' didn't exist as a key for some reason")
          .equals(this.getName())) {
        return Optional.of(hwDetails);
      }
    }
    return Optional.empty();
  }

  private List<Map<String, Object>> getAllHardwareDetails() throws IOException {
    try {
      if (exec("which lshw").getExitCode() == 0) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
            exec("lshw -C network -json").getStdOut(),
            new TypeReference<List<Map<String, Object>>>() {});
      } else {
        // user needs to have lshw installed to get any more detailed info
        return Collections.emptyList();
      }
    } catch (InterruptedException e) {
      throw new IOException(e);
    }
  }
}
