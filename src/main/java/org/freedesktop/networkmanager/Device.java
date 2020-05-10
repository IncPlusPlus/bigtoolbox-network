package org.freedesktop.networkmanager;

import java.util.Map;
import org.freedesktop.Pair;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.UInt64;
import org.freedesktop.dbus.types.Variant;
import org.freedesktop.networkmanager.types.NMDeviceState;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device")
public interface Device extends DBusInterface, Properties {
  void Reapply(
      Map<CharSequence, Map<CharSequence, Variant<?>>> connection, UInt64 version_id, UInt32 flags);

  Pair<Map<CharSequence, Map<CharSequence, Variant<?>>>, UInt64> GetAppliedConnection(UInt32 flags);

  void Disconnect();

  void Delete();

  class PropertyNames {
    /**
     * Operating-system specific transient device hardware identifier. This is an opaque string
     * representing the underlying hardware for the device, and shouldn't be used to keep track of
     * individual devices. For some device types (Bluetooth, Modems) it is an identifier used by the
     * hardware service (ie bluez or ModemManager) to refer to that device, and client programs use
     * it get additional information from those services which NM does not provide. The Udi is not
     * guaranteed to be consistent across reboots or hotplugs of the hardware. If you're looking for
     * a way to uniquely track each device in your application, use the object path. If you're
     * looking for a way to track a specific piece of hardware across reboot or hotplug, use a MAC
     * address or USB serial number.
     *
     * <p>Note that non-UTF-8 characters are backslash escaped. Use g_strcompress() to obtain the
     * true (non-UTF-8) string.
     */
    public static final String Udi = "Udi";
    /**
     * The name of the device's control (and often data) interface. Note that non UTF-8 characters
     * are backslash escaped, so the resulting name may be longer then 15 characters. Use
     * g_strcompress() to revert the escaping.
     */
    public static final String Interface = "Interface";
    /**
     * The name of the device's data interface when available. This property may not refer to the
     * actual data interface until the device has successfully established a data connection,
     * indicated by the device's State becoming ACTIVATED. Note that non UTF-8 characters are
     * backslash escaped, so the resulting name may be longer then 15 characters. Use
     * g_strcompress() to revert the escaping.
     */
    public static final String IpInterface = "IpInterface";
    /**
     * The driver handling the device. Non-UTF-8 sequences are backslash escaped. Use
     * g_strcompress() to revert.
     */
    public static final String Driver = "Driver";
    /**
     * The version of the driver handling the device. Non-UTF-8 sequences are backslash escaped. Use
     * g_strcompress() to revert.
     */
    public static final String DriverVersion = "DriverVersion";
    /**
     * The firmware version for the device. Non-UTF-8 sequences are backslash escaped. Use
     * g_strcompress() to revert.
     */
    public static final String FirmwareVersion = "FirmwareVersion";
    /**
     * Flags describing the capabilities of the device. See {@link
     * org.freedesktop.networkmanager.types.NMDeviceCapabilities} for possible values.
     */
    public static final String Capabilities = "Capabilities";
    /** @deprecated use the 'Addresses' property of the 'Ip4Config' object instead. */
    @Deprecated public static final String Ip4Address = "Ip4Address";
    /**
     * The current state of the device. Returns {@link
     * org.freedesktop.networkmanager.types.NMDeviceState}
     */
    public static final String State = "State";
    /**
     * The current state and reason for changing to that state. Returns a tuple of {@link
     * org.freedesktop.networkmanager.types.NMDeviceState} and {@link
     * org.freedesktop.networkmanager.types.NMDeviceStateReason}
     */
    public static final String StateReason = "StateReason";
    /**
     * Object path of an ActiveConnection object that "owns" this device during activation. The
     * ActiveConnection object tracks the life-cycle of a connection to a specific network and
     * implements the org.freedesktop.NetworkManager.Connection.Active D-Bus interface.
     */
    public static final String ActiveConnection = "ActiveConnection";
    /**
     * Object path of the Ip4Config object describing the configuration of the device. Only valid
     * when the device is in the {@link NMDeviceState#NM_DEVICE_STATE_ACTIVATED} state.
     */
    public static final String Ip4Config = "Ip4Config";
    /**
     * Object path of the Dhcp4Config object describing the DHCP options returned by the DHCP
     * server. Only valid when the device is in the {@link NMDeviceState#NM_DEVICE_STATE_ACTIVATED}
     * state.
     */
    public static final String Dhcp4Config = "Dhcp4Config";
    /**
     * Object path of the Ip6Config object describing the configuration of the device. Only valid
     * when the device is in the {@link NMDeviceState#NM_DEVICE_STATE_ACTIVATED} state.
     */
    public static final String Ip6Config = "Ip6Config";
    /**
     * Object path of the Dhcp6Config object describing the DHCP options returned by the DHCP
     * server. Only valid when the device is in the {@link NMDeviceState#NM_DEVICE_STATE_ACTIVATED}
     * state.
     */
    public static final String Dhcp6Config = "Dhcp6Config";
    /**
     * Whether or not this device is managed by NetworkManager. Setting this property has a similar
     * effect to configuring the device as unmanaged via the keyfile.unmanaged-devices setting in
     * NetworkManager.conf. Changes to this value are not persistent and lost after NetworkManager
     * restart.
     */
    public static final String Managed = "Managed";
    /**
     * If TRUE, indicates the device is allowed to autoconnect. If FALSE, manual intervention is
     * required before the device will automatically connect to a known network, such as activating
     * a connection using the device, or setting this property to TRUE. This property cannot be set
     * to TRUE for default-unmanaged devices, since they never autoconnect.
     */
    public static final String Autoconnect = "Autoconnect";
    /** If TRUE, indicates the device is likely missing firmware necessary for its operation. */
    public static final String FirmwareMissing = "FirmwareMissing";
    /**
     * If TRUE, indicates the NetworkManager plugin for the device is likely missing or
     * misconfigured.
     */
    public static final String NmPluginMissing = "NmPluginMissing";
    /**
     * The general type of the network device; ie Ethernet, Wi-Fi, etc. Returns {@link
     * org.freedesktop.networkmanager.types.NMDeviceType}
     */
    public static final String DeviceType = "DeviceType";
    /**
     * An array of object paths of every configured connection that is currently 'available' through
     * this device.
     */
    public static final String AvailableConnections = "AvailableConnections";
    /**
     * If non-empty, an (opaque) indicator of the physical network port associated with the device.
     * This can be used to recognize when two seemingly-separate hardware devices are actually just
     * different virtual interfaces to the same physical port.
     */
    public static final String PhysicalPortId = "PhysicalPortId";
    /** The device MTU (maximum transmission unit). */
    public static final String Mtu = "Mtu";
    /**
     * Whether the amount of traffic flowing through the device is subject to limitations, for
     * example set by service providers. Returns {@link
     * org.freedesktop.networkmanager.types.NMMetered}
     */
    public static final String Metered = "Metered";
    /**
     * Array of LLDP neighbors; each element is a dictionary mapping LLDP TLV names to variant boxed
     * values.
     */
    public static final String LldpNeighbors = "LldpNeighbors";
    /**
     * True if the device exists, or False for placeholder devices that do not yet exist but could
     * be automatically created by NetworkManager if one of their AvailableConnections was
     * activated.
     */
    public static final String Real = "Real";
    /**
     * The result of the last IPv4 connectivity check. Returns {@link
     * org.freedesktop.networkmanager.types.NMConnectivityState}
     */
    public static final String Ip4Connectivity = "Ip4Connectivity";
    /**
     * The result of the last IPv6 connectivity check. Returns {@link
     * org.freedesktop.networkmanager.types.NMConnectivityState}
     */
    public static final String Ip6Connectivity = "Ip6Connectivity";
    /**
     * The flags of the network interface. See {@link
     * org.freedesktop.networkmanager.types.NMDeviceInterfaceFlags} for the currently defined flags.
     */
    public static final String InterfaceFlags = "InterfaceFlags";
    /**
     * The hardware address of the device.
     *
     * <p>This replaces the other 'HwAddress' properties on the device-specific D-Bus interfaces.
     */
    public static final String HwAddress = "HwAddress";
  }

  class StateChanged extends DBusSignal {
    public final UInt32 new_state;
    public final UInt32 old_state;
    public final UInt32 reason;

    public StateChanged(String path, UInt32 new_state, UInt32 old_state, UInt32 reason)
        throws DBusException {
      super(path, new_state, old_state, reason);
      this.new_state = new_state;
      this.old_state = old_state;
      this.reason = reason;
    }
  }
}
