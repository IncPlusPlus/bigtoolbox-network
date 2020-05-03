package org.freedesktop.networkmanager;

import org.freedesktop.Pair;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.UInt64;
import org.freedesktop.dbus.types.Variant;

import java.util.Map;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device")
public interface Device extends DBusInterface, Properties {
	public void Reapply(Map<CharSequence, Map<CharSequence, Variant<?>>> connection, UInt64 version_id, UInt32 flags);
	
	public Pair<Map<CharSequence, Map<CharSequence, Variant<?>>>, UInt64> GetAppliedConnection(UInt32 flags);
	
	public void Disconnect();
	
	public void Delete();
}
