package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMDeviceInterfaceFlags;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Adsl")
public interface Adsl extends DBusInterface, Properties, Device {
  class PropertyNames {

    /**
     * Indicates whether the physical carrier is found.
     *
     * @deprecated check for the "carrier" flag in the {@link Device.PropertyNames#InterfaceFlags}
     *     property on the "org.freedesktop.NetworkManager.Device" interface.
     * @see NMDeviceInterfaceFlags#NM_DEVICE_INTERFACE_FLAG_CARRIER
     * @see Device
     */
    @Deprecated public static final String Carrier = "Carrier";
  }
}
