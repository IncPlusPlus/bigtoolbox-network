package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/** Flags describing the current activation state. */
public enum NMSettingsConnectionFlags {
  /** an alias for numeric zero, no flags set. */
  NM_SETTINGS_CONNECTION_FLAG_NONE(0),
  /**
   * the connection is not saved to disk. That either means, that the connection is in-memory only
   * and currently is not backed by a file. Or, that the connection is backed by a file, but has
   * modifications in-memory that were not persisted to disk.
   */
  NM_SETTINGS_CONNECTION_FLAG_UNSAVED(1),
  /**
   * A connection is "nm-generated" if it was generated by NetworkManger. If the connection gets
   * modified or saved by the user, the flag gets cleared. A nm-generated is also unsaved and has no
   * backing file as it is in-memory only.
   */
  NM_SETTINGS_CONNECTION_FLAG_NM_GENERATED(2),
  /**
   * The connection will be deleted when it disconnects. That is for in-memory connections
   * (unsaved), which are currently active but deleted on disconnect. Volatile connections are
   * always unsaved, but they are also no backing file on disk and are entirely in-memory only.
   */
  NM_SETTINGS_CONNECTION_FLAG_VOLATILE(4);

  private static final Map<UInt32, NMSettingsConnectionFlags> NM_SETTINGS_CONNECTION_FLAGS_MAP;

  static {
    NM_SETTINGS_CONNECTION_FLAGS_MAP =
        Arrays.stream(NMSettingsConnectionFlags.values())
            .collect(Collectors.toMap(NMSettingsConnectionFlags::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMSettingsConnectionFlags(int i) {
    this.value = new UInt32(i);
  }

  public static NMSettingsConnectionFlags getNMSettingsConnectionFlags(UInt32 uInt32) {
    return NM_SETTINGS_CONNECTION_FLAGS_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
