package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMDeviceInterfaceFlags;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Bond")
public interface Bond extends DBusInterface, Properties, Device {
  class PropertyNames {

    /**
     * Hardware address of the device.
     *
     * @deprecated Use the "HwAddress" property in "org.freedesktop.NetworkManager.Device" instead
     *     which exists since version NetworkManager 1.24.0.
     * @see Device.PropertyNames#HwAddress
     */
    @Deprecated public static final String HwAddress = "HwAddress";
    /**
     * Indicates whether the physical carrier is found (e.g. whether a cable is plugged in or not).
     *
     * @deprecated check for the "lower-up" flag in the "InterfaceFlags" property on the
     *     "org.freedesktop.NetworkManager.Device" interface.
     * @see Device.PropertyNames#InterfaceFlags
     * @see NMDeviceInterfaceFlags#NM_DEVICE_INTERFACE_FLAG_LOWER_UP
     */
    @Deprecated public static final String Carrier = "Carrier";
    /** Array of object paths representing devices which are currently enslaved to this device. */
    public static final String Slaves = "Slaves";
  }
}
