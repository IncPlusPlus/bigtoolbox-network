package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * 802.11 access point security and authentication flags. These flags describe the current security
 * requirements of an access point as determined from the access point's beacon.
 */
public enum NM80211ApSecurityFlags {
  /** the access point has no special security requirements */
  NM_802_11_AP_SEC_NONE(0),
  /** 40/64-bit WEP is supported for pairwise/unicast encryption */
  NM_802_11_AP_SEC_PAIR_WEP40(1),
  /** 104/128-bit WEP is supported for pairwise/unicast encryption */
  NM_802_11_AP_SEC_PAIR_WEP104(2),
  /** TKIP is supported for pairwise/unicast encryption */
  NM_802_11_AP_SEC_PAIR_TKIP(4),
  /** AES/CCMP is supported for pairwise/unicast encryption */
  NM_802_11_AP_SEC_PAIR_CCMP(8),
  /** 40/64-bit WEP is supported for group/broadcast encryption */
  NM_802_11_AP_SEC_GROUP_WEP40(10),
  /** 104/128-bit WEP is supported for group/broadcast encryption */
  NM_802_11_AP_SEC_GROUP_WEP104(20),
  /** TKIP is supported for group/broadcast encryption */
  NM_802_11_AP_SEC_GROUP_TKIP(40),
  /** AES/CCMP is supported for group/broadcast encryption */
  NM_802_11_AP_SEC_GROUP_CCMP(80),
  /** WPA/RSN Pre-Shared Key encryption is supported */
  NM_802_11_AP_SEC_KEY_MGMT_PSK(100),
  /** 802.1x authentication and key management is supported */
  NM_802_11_AP_SEC_KEY_MGMT_802_1X(200),
  /** WPA/RSN Simultaneous Authentication of Equals is supported */
  NM_802_11_AP_SEC_KEY_MGMT_SAE(400),
  /** WPA/RSN Opportunistic Wireless Encryption is supported */
  NM_802_11_AP_SEC_KEY_MGMT_OWE(800);

  private static final Map<UInt32, NM80211ApSecurityFlags> NM_80211_AP_SECURITY_FLAGS_MAP;

  static {
    NM_80211_AP_SECURITY_FLAGS_MAP =
        Arrays.stream(NM80211ApSecurityFlags.values())
            .collect(Collectors.toMap(NM80211ApSecurityFlags::getValue, Function.identity()));
  }

  private final UInt32 value;

  NM80211ApSecurityFlags(int i) {
    this.value = new UInt32(i);
  }

  public static NM80211ApSecurityFlags getNM80211ApSecurityFlags(UInt32 uInt32) {
    return NM_80211_AP_SECURITY_FLAGS_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
