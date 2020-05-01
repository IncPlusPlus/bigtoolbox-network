package io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.systemd1;

import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

@SuppressWarnings("SpellCheckingInspection")
@DBusInterfaceName("org.freedesktop.systemd1.Manager")
public interface Manager extends DBusInterface, Properties {
	public CharSequence Dump();
	
	/**
	 * Get a systemd Unit of a certain name.
	 * For example, the call GetUnit("NetworkManager.service") returns
	 * the path "/org/freedesktop/systemd1/unit/NetworkManager_2eservice"
	 *
	 * @param unitName the name of the unit
	 * @return the dbus path to the object
	 */
	DBusPath GetUnit(CharSequence unitName);
}