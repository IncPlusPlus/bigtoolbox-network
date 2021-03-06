package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

/** Options and configuration returned by the IPv4 DHCP server. */
@DBusInterfaceName("org.freedesktop.NetworkManager.DHCP4Config")
public interface DHCP4Config extends DBusInterface, Properties {
  class PropertyNames {
    /** Configuration options returned by a DHCP server, if any. */
    public static final String Options = "Options";
  }
}
