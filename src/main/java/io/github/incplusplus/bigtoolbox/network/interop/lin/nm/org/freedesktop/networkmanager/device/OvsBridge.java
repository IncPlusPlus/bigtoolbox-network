package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.OvsBridge")
public interface OvsBridge extends DBusInterface, Properties, Device {
  class PropertyNames {
    /** Array of object paths representing ports which are currently enslaved to this bridge. */
    public static final String Slaves = "Slaves";
  }
}
