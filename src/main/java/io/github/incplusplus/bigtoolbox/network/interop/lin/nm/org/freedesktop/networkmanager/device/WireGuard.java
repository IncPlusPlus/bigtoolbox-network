package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

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
