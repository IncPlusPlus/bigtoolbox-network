package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

/** VXLAN Device */
@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Vxlan")
public interface Vxlan extends DBusInterface, Properties, Device {
  class PropertyNames {
    /** The object path of the parent device (if the VXLAN is not purely internal to this host). */
    public static final String Parent = "Parent";
    /**
     * Hardware address of the device.
     *
     * @deprecated Use the "HwAddress" property in "org.freedesktop.NetworkManager.Device" instead
     *     which exists since version NetworkManager 1.24.0.
     * @see Device.PropertyNames#HwAddress
     */
    @Deprecated public static final String HwAddress = "HwAddress";
    /** The VXLAN Network Identifier (VNI). */
    public static final String Id = "Id";
    /**
     * The IP (v4 or v6) multicast group used to communicate with other physical hosts on this
     * VXLAN.
     */
    public static final String Group = "Group";
    /** The local IPv4 or IPv6 address to use when sending VXLAN packets to other physical hosts. */
    public static final String Local = "Local";
    /** The value to use in the IP ToS field for VXLAN packets sent to other physical hosts. */
    public static final String Tos = "Tos";
    /** The value to use in the IP TTL field for VXLAN packets sent to other physical hosts. */
    public static final String Ttl = "Ttl";
    /** True if the VXLAN dynamically learns remote IP addresses. */
    public static final String Learning = "Learning";
    /** The interval in seconds at which the kernel purges stale cached addresses. */
    public static final String Ageing = "Ageing";
    /** The maximum number of entries that can be added to the VXLAN's forwarding table. */
    public static final String Limit = "Limit";
    /** Destination port for outgoing VXLAN packets. */
    public static final String DstPort = "DstPort";
    /** The lowest source port number to use for outgoing VXLAN packets. */
    public static final String SrcPortMin = "SrcPortMin";
    /** The highest source port number to use for outgoing VXLAN packets. */
    public static final String SrcPortMax = "SrcPortMax";
    /** True if the VXLAN is implementing DOVE ARP proxying for remote clients. */
    public static final String Proxy = "Proxy";
    /**
     * True if the VXLAN is implementing DOVE route short-circuiting of known remote IP addresses.
     */
    public static final String Rsc = "Rsc";
    /** True if the VXLAN will emit netlink notifications of L2 switch misses. */
    public static final String L2miss = "L2miss";
    /** True if the VXLAN will emit netlink notifications of L3 switch misses. */
    public static final String L3miss = "L3miss";
  }
}
