package org.freedesktop.networkmanager.device;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.networkmanager.Device;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Generic")
public interface Generic extends DBusInterface, Properties, Device {
  class PropertyNames {
    /**
     * Hardware address of the device.
     *
     * @deprecated Use the "HwAddress" property in "org.freedesktop.NetworkManager.Device" instead
     *     which exists since version NetworkManager 1.24.0.
     * @see Device.PropertyNames#HwAddress
     */
    @Deprecated public static final String HwAddress = "HwAddress";
    /** A (non-localized) description of the interface type, if known. */
    public static final String TypeDescription = "TypeDescription";
  }
}
