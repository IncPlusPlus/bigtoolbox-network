package io.github.incplusplus.bigtoolbox.network;

import io.github.incplusplus.bigtoolbox.network.interfaces.WiFiAdapter;
import io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.PropertiesExtractor;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.NMInterop;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.NetworkManager;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMDeviceType;
import io.github.incplusplus.bigtoolbox.os.UnsupportedOSException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.types.Variant;

public class Main {
  public static void main(String[] args) throws UnsupportedOSException, IOException, DBusException {
    //    getAllProps();
    try (NetworkController controller = NetworkControllerFactory.createNetworkController()) {
      Interface[] ifaces = controller.getInterfaces();
      for (Interface i : Arrays.stream(ifaces).filter(Objects::nonNull).toArray(Interface[]::new)) {
        if(i instanceof WiFiAdapter) {
          WiFiAdapter adapter = (WiFiAdapter) i;
          System.out.println(Arrays.toString(((WiFiAdapter) i).getAccessPoints()));
        }
      }
    }
  }

  private static void getAllProps() throws UnsupportedOSException, IOException, DBusException {
    //    try (NetworkController controller = NetworkControllerFactory.createNetworkController()) {
    //			System.out.println(Arrays.toString(controller.getInterfaces()));
    NetworkManager nm =
        NMInterop.getActiveInstance()
            .getDbusConn()
            .getRemoteObject(
                "org.freedesktop.NetworkManager",
                "/org/freedesktop/NetworkManager",
                NetworkManager.class);
    List<Device> devices =
        nm.GetDevices().stream()
            .map(NMDeviceType::dbusInterfaceToNMDevice)
            .collect(Collectors.toList());
    Map<Device, Map<String, Map<String, Variant<?>>>> props = new HashMap<>();
    for (Device device : devices) {
      props.put(device, PropertiesExtractor.GetAllFromDevice(device));
    }
    System.out.println(props.size());
    //    }
  }
}
