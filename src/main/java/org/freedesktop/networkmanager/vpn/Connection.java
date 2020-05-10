package org.freedesktop.networkmanager.vpn;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.UInt32;

@DBusInterfaceName("org.freedesktop.NetworkManager.VPN.Connection")
public interface Connection extends DBusInterface, Properties {
  /** Emitted when the state of the VPN connection has changed. */
  class VpnStateChanged extends DBusSignal {
    /**
     * The new state of the VPN connection. Can be interpreted with {@link
     * org.freedesktop.networkmanager.types.NMVpnConnectionState}
     */
    private final UInt32 _state;
    /**
     * Reason code describing the change to the new state. Can be interpreted with {@link
     * org.freedesktop.networkmanager.types.NMActiveConnectionStateReason}
     */
    private final UInt32 _reason;

    VpnStateChanged(String _path, UInt32 _state, UInt32 _reason) throws DBusException {
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
    /**
     * The VPN-specific state of the connection.
     *
     * <p>Returns {@link org.freedesktop.networkmanager.types.NMVpnConnectionState}
     */
    public static final String VpnState = "VpnState";
    /** The banner string of the VPN connection. */
    public static final String Banner = "Banner";
  }
}
