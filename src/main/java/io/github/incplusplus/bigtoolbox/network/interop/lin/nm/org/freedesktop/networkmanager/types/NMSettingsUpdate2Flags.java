package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

public enum NMSettingsUpdate2Flags {
  /** an alias for numeric zero, no flags set. */
  NM_SETTINGS_UPDATE2_FLAG_NONE(0),
  /** to persist the connection to disk. */
  NM_SETTINGS_UPDATE2_FLAG_TO_DISK(1),
  /**
   * makes the profile in-memory. Note that such profiles are stored in keyfile format under /run.
   * If the file is already in-memory, the file in /run is updated in-place. Otherwise, the previous
   * storage for the profile is left unchanged on disk, and the in-memory copy shadows it. Note that
   * the original filename of the previous persistent storage (if any) is remembered. That means,
   * when later persisting the profile again to disk, the file on disk will be overwritten again.
   * Likewise, when finally deleting the profile, both the storage from /run and persistent storage
   * are deleted (or if the persistent storage does not allow deletion, and nmmeta file is written
   * to mark the UUID as deleted).
   */
  NM_SETTINGS_UPDATE2_FLAG_IN_MEMORY(2),
  /**
   * this is almost the same as %NM_SETTINGS_UPDATE2_FLAG_IN_MEMORY, with one difference: when later
   * deleting the profile, the original profile will not be deleted. Instead a nmmeta file is
   * written to /run to indicate that the profile is gone. Note that if such a nmmeta tombstone file
   * exists and hides a file in persistent storage, then when re-adding the profile with the same
   * UUID, then the original storage is taken over again.
   */
  NM_SETTINGS_UPDATE2_FLAG_IN_MEMORY_DETACHED(4),
  /**
   * this is like %NM_SETTINGS_UPDATE2_FLAG_IN_MEMORY, but if the connection has a corresponding
   * file on persistent storage, the file will be deleted right away. If the profile is later again
   * persisted to disk, a new, unused filename will be chosen.
   */
  NM_SETTINGS_UPDATE2_FLAG_IN_MEMORY_ONLY(8),
  /**
   * This can be specified with either %NM_SETTINGS_UPDATE2_FLAG_IN_MEMORY,
   * %NM_SETTINGS_UPDATE2_FLAG_IN_MEMORY_DETACHED or %NM_SETTINGS_UPDATE2_FLAG_IN_MEMORY_ONLY. After
   * making the connection in-memory only, the connection is marked as volatile. That means, if the
   * connection is currently not active it will be deleted right away. Otherwise, it is marked to
   * for deletion once the connection deactivates. A volatile connection cannot autoactivate again
   * (because it's about to be deleted), but a manual activation will clear the volatile flag.
   */
  NM_SETTINGS_UPDATE2_FLAG_VOLATILE(10),
  /**
   * usually, when the connection has autoconnect enabled and is modified, it becomes eligible to
   * autoconnect right away. Setting this flag, disables autoconnect until the connection is
   * manually activated.
   */
  NM_SETTINGS_UPDATE2_FLAG_BLOCK_AUTOCONNECT(20),
  /**
   * when a profile gets modified that is currently active, then these changes don't take effect for
   * the active device unless the profile gets reactivated or the configuration reapplied. There are
   * two exceptions: by default "connection.zone" and "connection.metered" properties take effect
   * immediately. Specify this flag to prevent these properties to take effect, so that the change
   * is restricted to modify the profile.
   */
  NM_SETTINGS_UPDATE2_FLAG_NO_REAPPLY(40);

  private static final Map<UInt32, NMSettingsUpdate2Flags> NM_SETTINGS_UPDATE_2_FLAGS_MAP;

  static {
    NM_SETTINGS_UPDATE_2_FLAGS_MAP =
        Arrays.stream(NMSettingsUpdate2Flags.values())
            .collect(Collectors.toMap(NMSettingsUpdate2Flags::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMSettingsUpdate2Flags(int i) {
    this.value = new UInt32(i);
  }

  public static NMSettingsUpdate2Flags getNMSettingsUpdate2Flags(UInt32 uInt32) {
    return NM_SETTINGS_UPDATE_2_FLAGS_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
