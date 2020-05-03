package io.github.incplusplus.bigtoolbox.network;

import io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.PropertiesExtractor;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.NMDeviceType;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.NMInterop;
import io.github.incplusplus.bigtoolbox.os.UnsupportedOSException;
import org.freedesktop.NetworkManager;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.types.Variant;
import org.freedesktop.networkmanager.Device;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws UnsupportedOSException, IOException, DBusException {
		try(NetworkController controller = NetworkControllerFactory.createNetworkController()){
//			System.out.println(Arrays.toString(controller.getInterfaces()));
			NetworkManager nm = NMInterop.getActiveInstance().getDbusConn().getRemoteObject("org.freedesktop.NetworkManager",
					"/org/freedesktop/NetworkManager", NetworkManager.class);
			List<Device> devices = nm.GetDevices().stream().map(NMDeviceType::dbusInterfaceToNMDevice).collect(
					Collectors.toList());
			Map<Device, Map<String, Map<String, Variant<?>>>> props = new HashMap<>();
			for (Device device : devices) {
				props.put(device, PropertiesExtractor.GetAllFromDevice(device));
			}
			System.out.println(props.size());
		}
	}
}
