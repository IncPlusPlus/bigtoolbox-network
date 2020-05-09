package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * Numeric flags for the "flags" argument of AddConnection2() D-Bus API.
 */
public enum NMSettingsAddConnection2Flags {
  /**
   * an alias for numeric zero, no flags set.
   */
  NM_SETTINGS_ADD_CONNECTION2_FLAG_NONE(0),
  /**
   * to persist the connection to disk.
   */
  NM_SETTINGS_ADD_CONNECTION2_FLAG_TO_DISK(1),
  /**
   * to make the connection in-memory only.
   */
  NM_SETTINGS_ADD_CONNECTION2_FLAG_IN_MEMORY(2),
  /**
   * usually, when the connection has autoconnect enabled and gets added, it becomes eligible to
   * autoconnect right away. Setting this flag, disables autoconnect until the connection is
   * manually activated.
   */
  NM_SETTINGS_ADD_CONNECTION2_FLAG_BLOCK_AUTOCONNECT(20);

  private static final Map<UInt32, NMSettingsAddConnection2Flags>
      NM_SETTINGS_ADD_CONNECTION_2_FLAGS_MAP;

  static {
    NM_SETTINGS_ADD_CONNECTION_2_FLAGS_MAP =
        Arrays.stream(NMSettingsAddConnection2Flags.values())
            .collect(
                Collectors.toMap(NMSettingsAddConnection2Flags::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMSettingsAddConnection2Flags(int i) {
    this.value = new UInt32(i);
  }

  public static NMSettingsAddConnection2Flags getNMSettingsAddConnection2Flags(UInt32 uInt32) {
    return NM_SETTINGS_ADD_CONNECTION_2_FLAGS_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
