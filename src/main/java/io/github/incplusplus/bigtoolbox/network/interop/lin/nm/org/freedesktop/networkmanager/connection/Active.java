package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.connection;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMActivationStateFlags;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMActiveConnectionState;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMActiveConnectionStateReason;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.UInt32;

/**
 * Objects that implement the Connection.Active interface represent an attempt to connect to a
 * network using the details provided by a Connection object. The Connection.Active object tracks
 * the life-cycle of the connection attempt and if successful indicates whether the connected
 * network is the "default" or preferred network for access. NetworkManager has the concept of
 * connections, which can be thought of as settings, a profile or a configuration that can be
 * applied on a networking device. Such settings-connections are exposed as D-Bus object and the
 * active-connection expresses this relationship between device and settings-connection. At any time
 * a settings-connection can only be activated on one device and vice versa. However, during
 * activation and deactivation multiple active-connections can reference the same device or
 * settings-connection as they are waiting to be activated or to be deactivated.
 */
@DBusInterfaceName("org.freedesktop.NetworkManager.Connection.Active")
public interface Active extends DBusInterface, Properties {

  /** Emitted when the state of the active connection has changed. */
  class StateChanged extends DBusSignal {

    /**
     * The new state of the active connection.
     *
     * @see NMActiveConnectionState
     */
    private final UInt32 _state;
    /**
     * Reason code describing the change to the new state.
     *
     * @see NMActiveConnectionStateReason
     */
    private final UInt32 _reason;

    StateChanged(String _path, UInt32 _state, UInt32 _reason) throws DBusException {
      super(_path, _state, _reason);
      this._state = _state;
      this._reason = _reason;
    }

    public UInt32 getState() {
      return _state;
    }

    public UInt32 getReason() {
      return _reason;
    }
  }

  class PropertyNames {
    /** The path of the connection. */
    public static final String Connection = "Connection";
    /**
     * A specific object associated with the active connection. This property reflects the specific
     * object used during connection activation, and will not change over the lifetime of the
     * ActiveConnection once set.
     */
    public static final String SpecificObject = "SpecificObject";
    /**
     * The ID of the connection, provided as a convenience so that clients do not have to retrieve
     * all connection details.
     */
    public static final String Id = "Id";
    /**
     * The UUID of the connection, provided as a convenience so that clients do not have to retrieve
     * all connection details.
     */
    public static final String Uuid = "Uuid";
    /**
     * The type of the connection, provided as a convenience so that clients do not have to retrieve
     * all connection details.
     */
    public static final String Type = "Type";
    /** Array of object paths representing devices which are part of this active connection. */
    public static final String Devices = "Devices";
    /**
     * The state of this active connection.
     *
     * <p>Returns: {@link NMActiveConnectionState}
     */
    public static final String State = "State";
    /**
     * The state flags of this active connection.
     *
     * <p>Returns {@link NMActivationStateFlags}
     */
    public static final String StateFlags = "StateFlags";
    /**
     * Whether this active connection is the default IPv4 connection, i.e. whether it currently owns
     * the default IPv4 route.
     */
    public static final String Default = "Default";
    /**
     * Object path of the Ip4Config object describing the configuration of the connection. Only
     * valid when the connection is in the {@link
     * NMActiveConnectionState#NM_ACTIVE_CONNECTION_STATE_ACTIVATED} state.
     */
    public static final String Ip4Config = "Ip4Config";
    /**
     * Object path of the Dhcp4Config object describing the DHCP options returned by the DHCP server
     * (assuming the connection used DHCP). Only valid when the connection is in the {@link
     * NMActiveConnectionState#NM_ACTIVE_CONNECTION_STATE_ACTIVATED} state.
     */
    public static final String Dhcp4Config = "Dhcp4Config";
    /**
     * Whether this active connection is the default IPv6 connection, i.e. whether it currently owns
     * the default IPv6 route.
     */
    public static final String Default6 = "Default6";
    /**
     * Object path of the Ip6Config object describing the configuration of the connection. Only
     * valid when the connection is in the {@link
     * NMActiveConnectionState#NM_ACTIVE_CONNECTION_STATE_ACTIVATED} state.
     */
    public static final String Ip6Config = "Ip6Config";
    /**
     * Object path of the Dhcp6Config object describing the DHCP options returned by the DHCP server
     * (assuming the connection used DHCP). Only valid when the connection is in the {@link
     * NMActiveConnectionState#NM_ACTIVE_CONNECTION_STATE_ACTIVATED} state.
     */
    public static final String Dhcp6Config = "Dhcp6Config";
    /** Whether this active connection is also a VPN connection. */
    public static final String Vpn = "Vpn";
    /** The path to the master device if the connection is a slave. */
    public static final String Master = "Master";
  }
}
