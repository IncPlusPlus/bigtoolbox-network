package io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.NMInterop;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;

import java.io.IOException;

public class DbusHelpers {
	public static String NM_BUS_PATH = "org.freedesktop.NetworkManager";
	public static <I extends DBusInterface> I getRemoteObject(String _busname, String _objectpath, Class<I> _type) throws IOException {
		try {
			return NMInterop.getActiveInstance().getDbusConn().getRemoteObject(_busname,_objectpath,_type);
		}
		catch (DBusException e) {
			throw new IOException(e);
		}
	}
}
