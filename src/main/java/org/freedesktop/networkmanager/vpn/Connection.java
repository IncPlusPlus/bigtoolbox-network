package org.freedesktop.networkmanager.vpn;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.UInt32;

@DBusInterfaceName("org.freedesktop.NetworkManager.VPN.Connection")
public interface Connection extends DBusInterface, Properties {

  class VpnStateChanged extends DBusSignal {

    private final UInt32 _state;
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
}