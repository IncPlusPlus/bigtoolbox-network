package org.freedesktop.networkmanager.device;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.networkmanager.Device;
/** PPP Device */
@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Ppp")
public interface Ppp extends DBusInterface, Properties, Device {}
