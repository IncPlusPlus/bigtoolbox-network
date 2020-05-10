package org.freedesktop.networkmanager.device;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.networkmanager.Device;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Adsl")
public interface Adsl extends DBusInterface, Properties, Device {
  class PropertyNames {

    /**
     * Indicates whether the physical carrier is found.
     *
     * @deprecated check for the "carrier" flag in the {@link Device.PropertyNames#InterfaceFlags} property on the
     *     "org.freedesktop.NetworkManager.Device" interface.
     * @see org.freedesktop.networkmanager.types.NMDeviceInterfaceFlags#NM_DEVICE_INTERFACE_FLAG_CARRIER
     * @see Device
     */
    @Deprecated public static final String Carrier = "Carrier";
  }
}
