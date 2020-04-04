package io.github.incplusplus.bigtoolbox.network.wlan.interop.lin;

import io.github.incplusplus.bigtoolbox.network.wlan.interop.lin.dbushelpers.systemd1.Manager;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.types.Variant;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

public enum NetTool {
	NetworkManager(new Function<String, Boolean>() {
		@Override
		public Boolean apply(String s) {
			try (DBusConnection dbusConn = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM)) {
				DBusPath nmUnit = dbusConn.getRemoteObject(
						"org.freedesktop.systemd1",
						"/org/freedesktop/systemd1",
						Manager.class)
						.GetUnit("bluetooth.service");
				Properties nmProps = dbusConn.getRemoteObject("org.freedesktop.systemd1", nmUnit.toString(),
						Properties.class);
				Map<String, Variant<?>> unitProps = nmProps.GetAll("org.freedesktop.systemd1.Unit");
				Map<String, Variant<?>> serviceProps = nmProps.GetAll("org.freedesktop.systemd1.Service");
				String ActiveState = (String) unitProps.get("ActiveState").getValue();
				String SubState = (String) unitProps.get("SubState").getValue();
				String StatusText = (String) serviceProps.get("StatusText").getValue();
				return ActiveState.equals("active") && SubState.equals("running");
			}
			catch (DBusException | IOException e) {
				e.printStackTrace();
				return false;
			}
		}
	}),
	wpa_supplicant(new Function<String, Boolean>() {
		@Override
		public Boolean apply(String s) {return false;}
	});
	
	private final Function<String, Boolean> isPresentFunc;
	
	NetTool(Function<String, Boolean> isPresent) {
		this.isPresentFunc = isPresent;
	}
	
	/**
	 * @param s an optional parameter; unused at the moment
	 * @return whether this networking tool is present on the system
	 */
	public boolean isPresent(String s) {
		return isPresentFunc.apply(s);
	}
}
