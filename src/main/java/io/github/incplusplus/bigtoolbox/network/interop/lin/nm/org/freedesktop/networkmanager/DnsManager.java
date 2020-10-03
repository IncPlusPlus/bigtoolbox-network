package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager;

import org.freedesktop.dbus.annotations.DBusInterfaceName;

/** DNS Configuration State */
@DBusInterfaceName("org.freedesktop.NetworkManager.DnsManager")
public interface DnsManager {
  class PropertyNames {
    /** The current DNS processing mode. */
    public static final String Mode = "Mode";
    /** The current resolv.conf management mode. */
    public static final String RcManager = "RcManager";
    /**
     * The current DNS configuration represented as an array of dictionaries. Each dictionary has
     * the "nameservers", "priority" keys and, optionally, "interface" and "vpn". "nameservers" is
     * the list of DNS servers, "priority" their relative priority, "interface" the interface on
     * which these servers are contacted, "vpn" a boolean telling whether the configuration was
     * obtained from a VPN connection.
     */
    public static final String Configuration = "Configuration";
  }
}
