package org.freedesktop.networkmanager.device;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.networkmanager.Device;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Bluetooth")
public interface Bluetooth extends DBusInterface, Properties, Device {
  class PropertyNames {

    /**
     * Bluetooth hardware address of the device.
     *
     * @deprecated Use the "HwAddress" property in "org.freedesktop.NetworkManager.Device" instead
     *     which exists since version NetworkManager 1.24.0.
     * @see org.freedesktop.networkmanager.Device.PropertyNames#HwAddress
     * @see Device
     */
    @Deprecated public static final String HwAddress = "HwAddress";
    /** Bluetooth name of the device. */
    public static final String Name = "Name";
    /**
     * Bluetooth capabilities of the device (either DUN or NAP). Returns {@link
     * org.freedesktop.networkmanager.types.NMBluetoothCapabilities}
     */
    public static final String BtCapabilities = "BtCapabilities";
  }
}
