package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.IPTunnel")
public interface IPTunnel extends DBusInterface, Properties, Device {
  class PropertyNames {
    /** The tunneling mode. */
    public static final String Mode = "Mode";
    /** The object path of the parent device. */
    public static final String Parent = "Parent";
    /** The local endpoint of the tunnel. */
    public static final String Local = "Local";
    /** The remote endpoint of the tunnel. */
    public static final String Remote = "Remote";
    /**
     * The TTL assigned to tunneled packets. 0 is a special value meaning that packets inherit the
     * TTL value
     */
    public static final String Ttl = "Ttl";
    /** The type of service (IPv4) or traffic class (IPv6) assigned to tunneled packets. */
    public static final String Tos = "Tos";
    /** Whether path MTU discovery is enabled on this tunnel. */
    public static final String PathMtuDiscovery = "PathMtuDiscovery";
    /** The key used for incoming packets. */
    public static final String InputKey = "InputKey";
    /** The key used for outgoing packets. */
    public static final String OutputKey = "OutputKey";
    /**
     * How many additional levels of encapsulation are permitted to be prepended to packets. This
     * property applies only to IPv6 tunnels.
     */
    public static final String EncapsulationLimit = "EncapsulationLimit";
    /** The flow label to assign to tunnel packets. This property applies only to IPv6 tunnels. */
    public static final String FlowLabel = "FlowLabel";
    /** Tunnel flags. */
    public static final String Flags = "Flags";
  }
}
