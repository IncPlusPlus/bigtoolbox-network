package org.freedesktop.networkmanager.device;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.networkmanager.Device;
/** Virtual Ethernet Device */
@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Veth")
public interface Veth extends DBusInterface, Properties, Device {
  class PropertyNames {
    /** The object path of the device's peer. */
    public static final String Peer = "Peer";
  }
}
