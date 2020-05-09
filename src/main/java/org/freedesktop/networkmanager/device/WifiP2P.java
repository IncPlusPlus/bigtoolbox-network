package org.freedesktop.networkmanager.device;

import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.Variant;
import org.freedesktop.networkmanager.Device;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.WifiP2P")
public interface WifiP2P extends DBusInterface, Properties, Device {

  void StartFind(Map<String, Variant<?>> options);

  void StopFind();

  class PeerAdded extends DBusSignal {

    private final DBusInterface _peer;

    PeerAdded(String _path, DBusInterface _peer) throws DBusException {
      super(_path, _peer);
      this._peer = _peer;
    }

    public DBusInterface getPeer() {
      return _peer;
    }
  }

  class PeerRemoved extends DBusSignal {

    private final DBusInterface _peer;

    PeerRemoved(String _path, DBusInterface _peer) throws DBusException {
      super(_path, _peer);
      this._peer = _peer;
    }

    public DBusInterface getPeer() {
      return _peer;
    }
  }
}