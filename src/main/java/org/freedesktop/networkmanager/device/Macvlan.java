package org.freedesktop.networkmanager.device;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.networkmanager.Device;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Macvlan")
public interface Macvlan extends DBusInterface, Properties, Device {
  class PropertyNames {
    /** The object path of the parent device. */
    public static final String Parent = "Parent";
    /** The macvlan mode, one of "private", "vepa", "bridge", or "passthru". */
    public static final String Mode = "Mode";
    /** Whether the device is blocked from going into promiscuous mode. */
    public static final String NoPromisc = "NoPromisc";
    /** Whether the device is a macvtap. */
    public static final String Tap = "Tap";
  }
}
