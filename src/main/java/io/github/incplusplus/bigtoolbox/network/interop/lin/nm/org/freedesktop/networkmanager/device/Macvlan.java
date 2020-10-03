package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

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
