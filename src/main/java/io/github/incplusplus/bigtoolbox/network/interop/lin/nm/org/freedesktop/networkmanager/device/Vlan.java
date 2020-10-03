package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

/** Virtual LAN Device */
@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Vlan")
public interface Vlan extends DBusInterface, Properties, Device {
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
     * @deprecated check for the "carrier" flag in the "InterfaceFlags" property on the
     *     "org.freedesktop.NetworkManager.Device" interface.
     * @see Device.PropertyNames#InterfaceFlags
     */
    @Deprecated public static final String Carrier = "Carrier";
    /** Object path of the parent device of this VLAN device. */
    public static final String Parent = "Parent";
    /** The VLAN ID of this VLAN interface. */
    public static final String VlanId = "VlanId";
  }
}
