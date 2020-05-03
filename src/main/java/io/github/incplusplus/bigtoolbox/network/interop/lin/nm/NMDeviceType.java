package io.github.incplusplus.bigtoolbox.network.interop.lin.nm;

import io.github.incplusplus.bigtoolbox.network.Interface;
import io.github.incplusplus.bigtoolbox.network.interfaces.lin.nm.WiFiAdapter;
import io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.PropertiesExtractor;
import org.freedesktop.dbus.RemoteInvocationHandler;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.networkmanager.Device;
import org.freedesktop.networkmanager.device.Generic;
import org.freedesktop.networkmanager.device.Wired;
import org.freedesktop.networkmanager.device.Wireless;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.DbusHelpers.NM_BUS_PATH;
import static io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.DbusHelpers.getRemoteObject;
import static java.util.Objects.isNull;

public enum NMDeviceType {
	/** An unknown device */
	NM_DEVICE_TYPE_UNKNOWN(0),
	/** Generic support for unrecognized device types */
	NM_DEVICE_TYPE_GENERIC(14),
	/** A wired ethernet device */
	NM_DEVICE_TYPE_ETHERNET(1),
	/** An 802.11 Wi-Fi device */
	NM_DEVICE_TYPE_WIFI(2),
	/** Not used */
	NM_DEVICE_TYPE_UNUSED1(3),
	/** Not used */
	NM_DEVICE_TYPE_UNUSED2(4),
	/** A Bluetooth device supporting PAN or DUN access protocols */
	NM_DEVICE_TYPE_BT(5),
	/** An OLPC XO mesh networking device */
	NM_DEVICE_TYPE_OLPC_MESH(6),
	/** An 802.16e Mobile WiMAX broadband device */
	NM_DEVICE_TYPE_WIMAX(7),
	/** A modem supporting analog telephone, CDMA/EVDO, GSM/UMTS, or LTE network access protocols */
	NM_DEVICE_TYPE_MODEM(8),
	/** An IP-over-InifiBand device */
	NM_DEVICE_TYPE_INFINIBAND(9),
	/** A bond master interface */
	NM_DEVICE_TYPE_BOND(10),
	/** An 802.1Q VLAN interface */
	NM_DEVICE_TYPE_VLAN(11),
	/** ADSL modem */
	NM_DEVICE_TYPE_ADSL(12),
	/** A bridge master interface */
	NM_DEVICE_TYPE_BRIDGE(13),
	/** A team master interface */
	NM_DEVICE_TYPE_TEAM(15),
	/** A TUN or TAP interface */
	NM_DEVICE_TYPE_TUN(16),
	/** A IP tunnel interface */
	NM_DEVICE_TYPE_IP_TUNNEL(17),
	/** A MACVLAN interface */
	NM_DEVICE_TYPE_MACVLAN(18),
	/** A VXLAN interface */
	NM_DEVICE_TYPE_VXLAN(19),
	/** A VETH interface */
	NM_DEVICE_TYPE_VETH(20),
	/** A MACsec interface */
	NM_DEVICE_TYPE_MACSEC(21),
	/** A dummy interface */
	NM_DEVICE_TYPE_DUMMY(22),
	/** A PPP interface */
	NM_DEVICE_TYPE_PPP(23),
	/** A Open vSwitch interface */
	NM_DEVICE_TYPE_OVS_INTERFACE(24),
	/** A Open vSwitch port */
	NM_DEVICE_TYPE_OVS_PORT(25),
	/** A Open vSwitch bridge */
	NM_DEVICE_TYPE_OVS_BRIDGE(26),
	/** A IEEE 802.15.4 (WPAN) MAC Layer Device */
	NM_DEVICE_TYPE_WPAN(27),
	/** 6LoWPAN interface */
	NM_DEVICE_TYPE_6LOWPAN(28),
	/** A WireGuard interface */
	NM_DEVICE_TYPE_WIREGUARD(29),
	/** An 802.11 Wi-Fi P2P device */
	NM_DEVICE_TYPE_WIFI_P2P(30);
	
	private final int DeviceType;
	private final static NMDeviceType[] devicesOrderedByDeviceType;
	private final static Map<Class<? extends Device>, NMDeviceType> deviceTypesByDeviceInterface = new HashMap<>();
	
	static {
		devicesOrderedByDeviceType = Arrays.stream(NMDeviceType.values()).sorted(
				Comparator.comparingInt(NMDeviceType::getDeviceType)).toArray(NMDeviceType[]::new);
		deviceTypesByDeviceInterface.put(Device.class,NM_DEVICE_TYPE_GENERIC);
		deviceTypesByDeviceInterface.put(Generic.class,NM_DEVICE_TYPE_GENERIC);
		deviceTypesByDeviceInterface.put(Wired.class,NM_DEVICE_TYPE_ETHERNET);
		deviceTypesByDeviceInterface.put(Wireless.class,NM_DEVICE_TYPE_WIFI);
	}
	
	public int getDeviceType() {
		return DeviceType;
	}
	
	NMDeviceType(int value) {
		this.DeviceType = value;
	}
	
	/**
	 * @param dBusInterface the interface
	 * @return the {@link Device} present at the specified {@link DBusInterface}; null if an exception was encountered
	 */
	public static Device dbusInterfaceToNMDevice(DBusInterface dBusInterface) {
		try {
			DBusConnection dbusConn = NMInterop.getActiveInstance().getDbusConn();
			Properties deviceProps = dbusConn.getRemoteObject(NM_BUS_PATH,
					dBusInterface.getObjectPath(), Properties.class);
			UInt32 deviceType = deviceProps.Get("org.freedesktop.NetworkManager.Device", "DeviceType");
			return getDeviceAtPath(deviceType, dBusInterface.getObjectPath());
		}
		catch (DBusException | IOException e) {
			return null;
		}
	}
	
	private static Device getDeviceAtPath(UInt32 deviceType, String objectPath) throws IOException {
		return getDeviceAtPath(devicesOrderedByDeviceType[deviceType.intValue()], objectPath);
	}
	
	private static Device getDeviceAtPath(NMDeviceType deviceType, String objectPath) throws IOException {
		return switch (deviceType) {
			case NM_DEVICE_TYPE_ETHERNET -> getRemoteObject(NM_BUS_PATH, objectPath, Wired.class);
			case NM_DEVICE_TYPE_WIFI -> getRemoteObject(NM_BUS_PATH, objectPath, Wireless.class);
			case NM_DEVICE_TYPE_GENERIC -> getRemoteObject(NM_BUS_PATH, objectPath, Generic.class);
			default -> getRemoteObject(NM_BUS_PATH, objectPath, Device.class);
		};
	}
	
	public static Interface encapsulateNMDeviceInInterface(Device device) {
		if (isNull(device))
			return null;
		return switch (deviceTypesByDeviceInterface.get(device.getClass().getInterfaces()[0])) {
			case NM_DEVICE_TYPE_ETHERNET -> null;
			case NM_DEVICE_TYPE_WIFI -> new WiFiAdapter(device);
			default -> null;
		};
	}
}
