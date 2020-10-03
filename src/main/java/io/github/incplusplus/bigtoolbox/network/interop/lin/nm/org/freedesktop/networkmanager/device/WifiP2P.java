package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.Variant;

/** Wi-Fi P2P Device */
@DBusInterfaceName("org.freedesktop.NetworkManager.Device.WifiP2P")
public interface WifiP2P extends DBusInterface, Properties, Device {

  /**
   * in the range of 1-600 seconds is supported. The default is 30 seconds.
   *
   * <p>Start a find operation for Wi-Fi P2P peers.
   *
   * @param options Options of find. Currently 'timeout' option with value of "i"
   */
  void StartFind(Map<String, Variant<?>> options);

  /** Stop an ongoing find operation again. */
  void StopFind();

  class PropertyNames {
    /**
     * Hardware address of the device.
     *
     * @deprecated Use the "HwAddress" property in "org.freedesktop.NetworkManager.Device" instead
     *     which exists since version NetworkManager 1.24.0.
     * @see Device.PropertyNames#HwAddress
     */
    @Deprecated public static final String HwAddress = "HwAddress";
    /** List of object paths of peers visible to this Wi-Fi P2P device. */
    public static final String Peers = "Peers";
  }

  /** Emitted when a new Wi-Fi P2P peer is found by the device. */
  class PeerAdded extends DBusSignal {

    /** The object path of the newly found access point. */
    private final DBusInterface _peer;

    PeerAdded(String _path, DBusInterface _peer) throws DBusException {
      super(_path, _peer);
      this._peer = _peer;
    }

    public DBusInterface getPeer() {
      return _peer;
    }
  }
  /** Emitted when a Wi-Fi P2P peer disappears from view of the device. */
  class PeerRemoved extends DBusSignal {
    /** The object path of the Wi-Fi P2P peer that has disappeared. */
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
