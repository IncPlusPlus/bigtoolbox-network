package org.freedesktop.networkmanager.device;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.networkmanager.Device;

/** VRF Device */
@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Vrf")
public interface Vrf extends DBusInterface, Properties, Device {
  class PropertyNames {
    /** The routing table of the VRF. */
    public static final String Table = "Table";
  }
}
