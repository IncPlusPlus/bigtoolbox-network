package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/** 802.11 specific device encryption and authentication capabilities. */
public enum NMDeviceWifiCapabilities {
  /** device has no encryption/authentication capabilities */
  NM_WIFI_DEVICE_CAP_NONE(0),
  /** device supports 40/64-bit WEP encryption */
  NM_WIFI_DEVICE_CAP_CIPHER_WEP40(1),
  /** device supports 104/128-bit WEP encryption */
  NM_WIFI_DEVICE_CAP_CIPHER_WEP104(2),
  /** device supports TKIP encryption */
  NM_WIFI_DEVICE_CAP_CIPHER_TKIP(4),
  /** device supports AES/CCMP encryption */
  NM_WIFI_DEVICE_CAP_CIPHER_CCMP(8),
  /** device supports WPA1 authentication */
  NM_WIFI_DEVICE_CAP_WPA(10),
  /** device supports WPA2/RSN authentication */
  NM_WIFI_DEVICE_CAP_RSN(20),
  /** device supports Access Point mode */
  NM_WIFI_DEVICE_CAP_AP(40),
  /** device supports Ad-Hoc mode */
  NM_WIFI_DEVICE_CAP_ADHOC(80),
  /** device reports frequency capabilities */
  NM_WIFI_DEVICE_CAP_FREQ_VALID(100),
  /** device supports 2.4GHz frequencies */
  NM_WIFI_DEVICE_CAP_FREQ_2GHZ(200),
  /** device supports 5GHz frequencies */
  NM_WIFI_DEVICE_CAP_FREQ_5GHZ(400),
  /** device supports acting as a mesh point. */
  NM_WIFI_DEVICE_CAP_MESH(1000),
  /** device supports WPA2/RSN in an IBSS network. */
  NM_WIFI_DEVICE_CAP_IBSS_RSN(2000);

  private static final Map<UInt32, NMDeviceWifiCapabilities> NM_DEVICE_WIFI_CAPABILITIES_MAP;

  static {
    NM_DEVICE_WIFI_CAPABILITIES_MAP =
        Arrays.stream(NMDeviceWifiCapabilities.values())
            .collect(Collectors.toMap(NMDeviceWifiCapabilities::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMDeviceWifiCapabilities(int i) {
    this.value = new UInt32(i);
  }

  public static NMDeviceWifiCapabilities getNMDeviceWifiCapabilities(UInt32 uInt32) {
    return NM_DEVICE_WIFI_CAPABILITIES_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
