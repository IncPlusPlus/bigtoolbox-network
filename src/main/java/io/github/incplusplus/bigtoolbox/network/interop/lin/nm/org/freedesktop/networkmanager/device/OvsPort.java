package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.OvsPort")
public interface OvsPort extends DBusInterface, Properties, Device {
  class PropertyNames {
    /** Array of object paths representing interfaces which are currently enslaved to this port. */
    public static final String Slaves = "Slaves";
  }
}
