package org.freedesktop.networkmanager.connection;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
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
public interface Active extends DBusInterface {

  /**
   * Emitted when the state of the active connection has changed.
   */
  class StateChanged extends DBusSignal {

    /**
     * (NMActiveConnectionState) The new state of the active connection.
     */
    private final UInt32 _state;
    /**
     * (NMActiveConnectionStateReason) Reason code describing the change to the new state.
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


}