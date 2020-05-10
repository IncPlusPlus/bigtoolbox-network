package org.freedesktop.networkmanager.device;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.networkmanager.Device;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.WireGuard")
public interface WireGuard extends DBusInterface, Properties, Device {
  class PropertyNames {
    /** 32-byte public WireGuard key. */
    public static final String PublicKey = "PublicKey";
    /** Local UDP listening port. */
    public static final String ListenPort = "ListenPort";
    /**
     * Optional 32-bit mark used to set routing policy for outgoing encrypted packets. See:
     * ip-rule(8)
     */
    public static final String FwMark = "FwMark";
  }
}
