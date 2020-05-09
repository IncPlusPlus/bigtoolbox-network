package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * The NMMetered enum has two different purposes: one is to configure "connection.metered" setting
 * of a connection profile in NMSettingConnection, and the other is to express the actual metered
 * state of the NMDevice at a given moment.
 *
 * <p>
 *
 * <p>
 *
 * <p>For the connection profile only NM_METERED_UNKNOWN, NM_METERED_NO and NM_METERED_YES are
 * allowed.
 *
 * <p>
 *
 * <p>
 *
 * <p>The device's metered state at runtime is determined by the profile which is currently active.
 * If the profile explicitly specifies NM_METERED_NO or NM_METERED_YES, then the device's metered
 * state is as such. If the connection profile leaves it undecided at NM_METERED_UNKNOWN (the
 * default), then NetworkManager tries to guess the metered state, for example based on the device
 * type or on DHCP options (like Android devices exposing a "ANDROID_METERED" DHCP vendor option).
 * This then leads to either NM_METERED_GUESS_NO or NM_METERED_GUESS_YES.
 *
 * <p>
 *
 * <p>
 *
 * <p>Most applications probably should treat the runtime state NM_METERED_GUESS_YES like
 * NM_METERED_YES, and all other states as not metered.
 *
 * <p>
 *
 * <p>
 *
 * <p>Note that the per-device metered states are then combined to a global metered state. This is
 * basically the metered state of the device with the best default route. However, that
 * generalization of a global metered state may not be correct if the default routes for IPv4 and
 * IPv6 are on different devices, or if policy routing is configured. In general, the global metered
 * state tries to express whether the traffic is likely metered, but since that depends on the
 * traffic itself, there is not one answer in all cases. Hence, an application may want to consider
 * the per-device's metered states.
 */
public enum NMMetered {
  /** The metered status is unknown */
  NM_METERED_UNKNOWN(0),
  /** Metered, the value was explicitly configured */
  NM_METERED_YES(1),
  /** Not metered, the value was explicitly configured */
  NM_METERED_NO(2),
  /** Not metered, the value was explicitly configured */
  NM_METERED_GUESS_YES(3),
  /** Not metered, the value was guessed */
  NM_METERED_GUESS_NO(4);

  private static final Map<UInt32, NMMetered> NM_METERED_MAP;

  static {
    NM_METERED_MAP =
        Arrays.stream(NMMetered.values())
            .collect(Collectors.toMap(NMMetered::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMMetered(int i) {
    this.value = new UInt32(i);
  }

  public static NMMetered getNMMetered(UInt32 uInt32) {
    return NM_METERED_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
