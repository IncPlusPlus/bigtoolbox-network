package org.freedesktop;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

import java.util.List;
import java.util.Map;

@SuppressWarnings("UnnecessaryInterfaceModifier")
@DBusInterfaceName("org.freedesktop.NetworkManager")
public interface NetworkManager extends DBusInterface, Properties {
	public static class CheckPermissions extends DBusSignal {
		public CheckPermissions(String path) throws DBusException {
			super(path);
		}
	}
	
	public static class StateChanged extends DBusSignal {
		public final UInt32 state;
		
		public StateChanged(String path, UInt32 state) throws DBusException {
			super(path, state);
			this.state = state;
		}
	}
	
	public static class DeviceAdded extends DBusSignal {
		public final DBusInterface device_path;
		
		public DeviceAdded(String path, DBusInterface device_path) throws DBusException {
			super(path, device_path);
			this.device_path = device_path;
		}
	}
	
	public static class DeviceRemoved extends DBusSignal {
		public final DBusInterface device_path;
		
		public DeviceRemoved(String path, DBusInterface device_path) throws DBusException {
			super(path, device_path);
			this.device_path = device_path;
		}
	}
	
	public void Reload(UInt32 flags);
	
	public List<DBusInterface> GetDevices();
	
	public List<DBusInterface> GetAllDevices();
	
	public DBusInterface GetDeviceByIpIface(String iface);
	
	public DBusInterface ActivateConnection(DBusInterface connection, DBusInterface device,
	                                        DBusInterface specificObject);
	
	public Pair<DBusInterface, DBusInterface> AddAndActivateConnection(Map<String, Map<String, Variant<?>>> connection,
	                                                                   DBusInterface device,
	                                                                   DBusInterface specificObject);
	
	public Triplet<DBusInterface, DBusInterface, Map<String, Variant<?>>> AddAndActivateConnection2(
			Map<String, Map<String, Variant<?>>> connection,
			DBusInterface device, DBusInterface specificObject,
			Map<String, Variant<?>> options);
	
	public void DeactivateConnection(DBusInterface activeConnection);
	
	public void Sleep(boolean sleep);
	
	public void Enable(boolean enable);
	
	public Map<String, String> GetPermissions();
	
	public void SetLogging(String level, String domains);
	
	public Pair<String, String> GetLogging();
	
	public UInt32 CheckConnectivity();
	
	public UInt32 state();
	
	public DBusInterface CheckpointCreate(List<DBusInterface> devices, UInt32 rollbackTimeout, UInt32 flags);
	
	public void CheckpointDestroy(DBusInterface checkpoint);
	
	public Map<String, UInt32> CheckpointRollback(DBusInterface checkpoint);
	
	public void CheckpointAdjustRollbackTimeout(DBusInterface checkpoint, UInt32 addTimeout);
}