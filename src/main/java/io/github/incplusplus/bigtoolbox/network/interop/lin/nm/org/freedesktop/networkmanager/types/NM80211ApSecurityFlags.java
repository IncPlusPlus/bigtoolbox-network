package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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

  public static List<NM80211ApSecurityFlags> getNM80211ApSecurityFlags(UInt32 uInt32) {
    int remainingFlags = uInt32.intValue();
    List<NM80211ApSecurityFlags> flags = new ArrayList<>();
    for (NM80211ApSecurityFlags flag :
        Arrays.stream(NM80211ApSecurityFlags.values())
            // sort in descending order
            .sorted(
                Comparator.comparingInt(
                        value1 -> ((NM80211ApSecurityFlags) value1).getValue().intValue())
                    .reversed())
            .collect(Collectors.toList())) {
      if (remainingFlags - flag.getValue().intValue() >= 0) {
        remainingFlags -= flag.getValue().intValue();
        flags.add(flag);
      }
    }
    if (remainingFlags > 0) {
      // There are flags we don't know about or the flag values in this code are out of date. This
      // means we can't rely on any of the info we have here. Throw it all out.
      // TODO: Create a proper exception
      throw new RuntimeException(
          "Failed to find the corresponding NM80211ApSecurityFlags for the provided value. "
              + "This library may be out of date or incompatible with your version of NetworkManger");
    }

    return flags;
  }

  public UInt32 getValue() {
    return value;
  }
}
