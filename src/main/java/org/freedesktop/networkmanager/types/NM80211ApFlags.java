package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * 802.11 access point flags.
 */
public enum NM80211ApFlags {
  /**
   * access point has no special capabilities
   */
  NM_802_11_AP_FLAGS_NONE(0),
  /**
   * access point requires authentication and encryption (usually means WEP)
   */
  NM_802_11_AP_FLAGS_PRIVACY(1),
  /**
   * access point supports some WPS method
   */
  NM_802_11_AP_FLAGS_WPS(2),
  /**
   * access point supports push-button WPS
   */
  NM_802_11_AP_FLAGS_WPS_PBC(4),
  /**
   * access point supports PIN-based WPS
   */
  NM_802_11_AP_FLAGS_WPS_PIN(8);

  private static final Map<UInt32, NM80211ApFlags> NM_80211_AP_FLAGS_MAP;

  static {
    NM_80211_AP_FLAGS_MAP =
        Arrays.stream(NM80211ApFlags.values())
            .collect(Collectors.toMap(NM80211ApFlags::getValue, Function.identity()));
  }

  private final UInt32 value;

  NM80211ApFlags(int i) {
    this.value = new UInt32(i);
  }

  public static NM80211ApFlags getNM80211ApFlags(UInt32 uInt32) {
    return NM_80211_AP_FLAGS_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
