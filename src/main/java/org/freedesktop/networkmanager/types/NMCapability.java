package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * NMCapability names the numbers in the Capabilities property. Capabilities are positive numbers.
 * They are part of stable API and a certain capability number is guaranteed not to change.
 *
 * <p>The range 0x7000 - 0x7FFF of capabilities is guaranteed not to be used by upstream
 * NetworkManager. It could thus be used for downstream extensions.
 *
 * <p>See <a
 * href="https://developer.gnome.org/NetworkManager/stable/nm-dbus-types.html#NMCapability"
 * target="_top">NMCapability Documentation</a>
 */
public enum NMCapability {
  /**
   * Teams can be managed. This means the team device plugin is loaded.
   */
  NM_CAPABILITY_TEAM(1),
  /**
   * OpenVSwitch can be managed. This means the OVS device plugin is loaded.
   */
  NM_CAPABILITY_OVS(2);

  private static final Map<UInt32, NMCapability> NM_CAPABILITY_MAP;

  static {
    NM_CAPABILITY_MAP = Arrays.stream(NMCapability.values())
        .collect(Collectors.toMap(NMCapability::getValue,
            Function.identity()));
  }

  private final UInt32 value;

  NMCapability(int i) {
    this.value = new UInt32(i);
  }

  public static NMCapability getNMCapability(UInt32 uInt32) {
    return NM_CAPABILITY_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
