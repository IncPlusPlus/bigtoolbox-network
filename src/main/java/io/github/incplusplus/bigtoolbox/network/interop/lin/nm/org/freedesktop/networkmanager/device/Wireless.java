package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NM80211Mode;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMDeviceWifiCapabilities;
import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.Variant;

/** Wi-Fi Device */
@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Wireless")
public interface Wireless extends DBusInterface, Properties, Device {

  /**
   * Get the list of access points visible to this device. Note that this list does not include
   * access points which hide their SSID. To retrieve a list of all access points (including hidden
   * ones) use the GetAllAccessPoints() method.
   *
   * @return List of access point object paths.
   * @deprecated
   */
  @Deprecated
  List<DBusPath> GetAccessPoints();

  /**
   * Get the list of all access points visible to this device, including hidden ones for which the
   * SSID is not yet known.
   *
   * @return List of access point object paths.
   */
  List<DBusPath> GetAllAccessPoints();

  /**
   * Request the device to scan. To know when the scan is finished, use the "PropertiesChanged"
   * signal from "org.freedesktop.DBus.Properties" to listen to changes to the "LastScan" property.
   *
   * @param options Options of scan. Currently 'ssids' option with value of "aay" type is supported.
   */
  void RequestScan(Map<String, Variant<?>> options);
  /** Emitted when a new access point is found by the device. */
  class AccessPointAdded extends DBusSignal {
    /** The object path of the newly found access point. */
    private final DBusPath accessPoint;

    AccessPointAdded(String _path, DBusPath _accessPoint) throws DBusException {
      super(_path, _accessPoint);
      this.accessPoint = _accessPoint;
    }

    public DBusPath getAccessPoint() {
      return accessPoint;
    }
  }
  /** Emitted when an access point disappears from view of the device. */
  class AccessPointRemoved extends DBusSignal {
    /** The object path of the access point that has disappeared. */
    private final DBusPath accessPoint;

    AccessPointRemoved(String _path, DBusPath _accessPoint) throws DBusException {
      super(_path, _accessPoint);
      this.accessPoint = _accessPoint;
    }

    public DBusPath getAccessPoint() {
      return accessPoint;
    }
  }

  class PropertyNames {
    /**
     * Hardware address of the device.
     *
     * @deprecated Use the "HwAddress" property in "org.freedesktop.NetworkManager.Device" instead
     *     which exists since version NetworkManager 1.24.0.
     * @see Device.PropertyNames#HwAddress
     */
    @Deprecated public static final String HwAddress = "HwAddress";
    /** Permanent hardware address of the device. */
    public static final String PermHwAddress = "PermHwAddress";
    /**
     * The operating mode of the wireless device. Returns {@link
     * NM80211Mode}
     */
    public static final String Mode = "Mode";
    /** The bit rate currently used by the wireless device, in kilobits/second (Kb/s). */
    public static final String Bitrate = "Bitrate";
    /** List of object paths of access point visible to this wireless device. */
    public static final String AccessPoints = "AccessPoints";
    /** Object path of the access point currently used by the wireless device. */
    public static final String ActiveAccessPoint = "ActiveAccessPoint";
    /**
     * The capabilities of the wireless device. Returns {@link
     * NMDeviceWifiCapabilities}
     */
    public static final String WirelessCapabilities = "WirelessCapabilities";
    /**
     * The timestamp (in CLOCK_BOOTTIME milliseconds) for the last finished network scan. A value of
     * -1 means the device never scanned for access points.
     */
    public static final String LastScan = "LastScan";
  }
}
