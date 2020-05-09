package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * Indicates the 802.11 mode an access point or device is currently in.
 */
public enum NM80211Mode {
  /**
   * the device or access point mode is unknown
   */
  NM_802_11_MODE_UNKNOWN(0),
  /**
   * for both devices and access point objects, indicates the object is part of an Ad-Hoc 802.11
   * network without a central coordinating access point.
   */
  NM_802_11_MODE_ADHOC(1),
  /**
   * the device or access point is in infrastructure mode. For devices, this indicates the device is
   * an 802.11 client/station. For access point objects, this indicates the object is an access
   * point that provides connectivity to clients.
   */
  NM_802_11_MODE_INFRA(2),
  /**
   * the device is an access point/hotspot. Not valid for access point objects; used only for
   * hotspot mode on the local machine.
   */
  NM_802_11_MODE_AP(3),
  /**
   * the device is a 802.11s mesh point.
   */
  NM_802_11_MODE_MESH(4);

  private static final Map<UInt32, NM80211Mode> NM_80211_MODE_MAP;

  static {
    NM_80211_MODE_MAP =
        Arrays.stream(NM80211Mode.values())
            .collect(Collectors.toMap(NM80211Mode::getValue, Function.identity()));
  }

  private final UInt32 value;

  NM80211Mode(int i) {
    this.value = new UInt32(i);
  }

  public static NM80211Mode getNM80211Mode(UInt32 uInt32) {
    return NM_80211_MODE_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
