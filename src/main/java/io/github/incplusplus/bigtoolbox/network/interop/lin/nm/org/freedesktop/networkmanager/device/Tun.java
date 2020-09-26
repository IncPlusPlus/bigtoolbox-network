package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

/** Userspace Tunneling Device */
@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Tun")
public interface Tun extends DBusInterface, Properties, Device {
  class PropertyNames {
    /** The uid of the tunnel owner, or -1 if it has no owner. */
    public static final String Owner = "Owner";
    /** The gid of the tunnel group, or -1 if it has no owner. */
    public static final String Group = "Group";
    /** The tunnel mode, either "tun" or "tap". */
    public static final String Mode = "Mode";
    /**
     * The tunnel's "TUN_NO_PI" flag; true if no protocol info is prepended to the tunnel packets.
     */
    public static final String NoPi = "NoPi";
    /**
     * The tunnel's "TUN_VNET_HDR" flag; true if the tunnel packets include a virtio network header.
     */
    public static final String VnetHdr = "VnetHdr";
    /**
     * The tunnel's "TUN_TAP_MQ" flag; true if callers can connect to the tap device multiple times,
     * for multiple send/receive queues.
     */
    public static final String MultiQueue = "MultiQueue";
    /**
     * Hardware address of the device.
     *
     * @deprecated Use the "HwAddress" property in "org.freedesktop.NetworkManager.Device" instead
     *     which exists since version NetworkManager 1.24.0.
     * @see Device.PropertyNames#HwAddress
     */
    @Deprecated public static final String HwAddress = "HwAddress";
  }
}
