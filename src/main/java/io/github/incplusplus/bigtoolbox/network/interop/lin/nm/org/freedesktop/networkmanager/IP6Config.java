package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

/** IPv6 Configuration Set */
@DBusInterfaceName("org.freedesktop.NetworkManager.IP6Config")
public interface IP6Config extends DBusInterface, Properties {
  class PropertyNames {
    /**
     * Array of tuples of IPv6 address/prefix/gateway.
     *
     * @deprecated use {@link #AddressData} and {@link #Gateway}.
     */
    @Deprecated public static final String Addresses = "Addresses";
    /**
     * Array of IP address data objects. All addresses will include "address" (an IP address
     * string), and "prefix" (a uint). Some addresses may include additional attributes.
     */
    public static final String AddressData = "AddressData";
    /** The gateway in use. */
    public static final String Gateway = "Gateway";
    /**
     * Tuples of IPv6 route/prefix/next-hop/metric.
     *
     * @deprecated use {@link #RouteData}
     */
    @Deprecated public static final String Routes = "Routes";
    /**
     * Array of IP route data objects. All routes will include "dest" (an IP address string) and
     * "prefix" (a uint). Some routes may include "next-hop" (an IP address string), "metric" (a
     * uint), and additional attributes.
     */
    public static final String RouteData = "RouteData";
    /** The nameservers in use. */
    public static final String Nameservers = "Nameservers";
    /** A list of domains this address belongs to. */
    public static final String Domains = "Domains";
    /** A list of dns searches. */
    public static final String Searches = "Searches";
    /**
     * A list of DNS options that modify the behavior of the DNS resolver. See resolv.conf(5) manual
     * page for the list of supported options.
     */
    public static final String DnsOptions = "DnsOptions";
    /** The relative priority of DNS servers. */
    public static final String DnsPriority = "DnsPriority";
  }
}
