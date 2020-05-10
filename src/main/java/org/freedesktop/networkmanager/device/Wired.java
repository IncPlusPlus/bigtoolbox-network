package org.freedesktop.networkmanager.device;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.networkmanager.Device;
/** Wired Ethernet Device */
@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Wired")
public interface Wired extends DBusInterface, Properties, Device {
  class PropertyNames {
    /**
     * Hardware address of the device.
     *
     * @deprecated Use the "HwAddress" property in "org.freedesktop.NetworkManager.Device" instead
     *     which exists since version NetworkManager 1.24.0.
     * @see Device.PropertyNames#HwAddress
     */
    @Deprecated public static final String HwAddress = "HwAddress";
    /** Permanent hardware address of the device. */
    public static final String PermHwAddress = "PermHwAddress";
    /** Design speed of the device, in megabits/second (Mb/s). */
    public static final String Speed = "Speed";
    /** Array of S/390 subchannels for S/390 or z/Architecture devices. */
    public static final String S390Subchannels = "S390Subchannels";

    /**
     * Indicates whether the physical carrier is found (e.g. whether a cable is plugged in or not).
     *
     * @deprecated check for the "carrier" flag in the "InterfaceFlags" property on the
     *     "org.freedesktop.NetworkManager.Device" interface.
     * @see Device.PropertyNames#InterfaceFlags
     */
    @Deprecated public static final String Carrier = "Carrier";
  }
}
