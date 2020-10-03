package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Statistics")
public interface Statistics extends DBusInterface, Properties, Device {
  class PropertyNames {
    /**
     * Refresh rate of the rest of properties of this interface. The properties are guaranteed to be
     * refreshed each RefreshRateMs milliseconds in case the underlying counter has changed too. If
     * zero, there is no guaranteed refresh rate of the properties.
     */
    public static final String RefreshRateMs = "RefreshRateMs";
    /** Number of transmitted bytes */
    public static final String TxBytes = "TxBytes";
    /** Number of received bytes */
    public static final String RxBytes = "RxBytes";
  }
}
