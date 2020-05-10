package org.freedesktop.networkmanager.device;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.networkmanager.Device;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.OvsBridge")
public interface OvsBridge extends DBusInterface, Properties, Device {
  class PropertyNames {
    /** Array of object paths representing ports which are currently enslaved to this bridge. */
    public static final String Slaves = "Slaves";
  }
}
