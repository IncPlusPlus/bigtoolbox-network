package org.freedesktop.networkmanager.device;

import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.Variant;
import org.freedesktop.networkmanager.Device;

import java.util.List;
import java.util.Map;

/**
 * Auto-generated class.
 */
@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Wireless")
public interface Wireless extends DBusInterface, Properties, Device {
	public List<DBusPath> GetAccessPoints();
	
	public List<DBusPath> GetAllAccessPoints();
	
	public void RequestScan(Map<String, Variant<?>> options);
	
	public static class AccessPointAdded extends DBusSignal {
		
		private final DBusPath accessPoint;
		
		AccessPointAdded(String _path, String _interfaceName, DBusPath _accessPoint) throws DBusException {
			super(_path, _interfaceName);
			this.accessPoint = _accessPoint;
		}
		
		public DBusPath getAccessPoint() {
			return accessPoint;
		}
		
	}
	
	public static class AccessPointRemoved extends DBusSignal {
		
		private final DBusPath accessPoint;
		
		AccessPointRemoved(String _path, String _interfaceName, DBusPath _accessPoint) throws DBusException {
			super(_path, _interfaceName);
			this.accessPoint = _accessPoint;
		}
		
		public DBusPath getAccessPoint() {
			return accessPoint;
		}
		
	}
}
