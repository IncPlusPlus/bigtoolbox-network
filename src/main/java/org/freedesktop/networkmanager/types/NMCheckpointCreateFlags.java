package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/** The flags for CheckpointCreate call */
public enum NMCheckpointCreateFlags {
  /** no flags */
  NM_CHECKPOINT_CREATE_FLAG_NONE(0),
  /** when creating a new checkpoint, destroy all existing ones. */
  NM_CHECKPOINT_CREATE_FLAG_DESTROY_ALL(1),
  /** upon rollback, delete any new connection added after the checkpoint */
  NM_CHECKPOINT_CREATE_FLAG_DELETE_NEW_CONNECTIONS(2),
  /** upon rollback, disconnect any new device appeared after the checkpoint */
  NM_CHECKPOINT_CREATE_FLAG_DISCONNECT_NEW_DEVICES(4),
  /**
   * by default, creating a checkpoint fails if there are already existing checkpoints that
   * reference the same devices. With this flag, creation of such checkpoints is allowed, however,
   * if an older checkpoint that references overlapping devices gets rolled back, it will
   * automatically destroy this checkpoint during rollback. This allows to create several
   * overlapping checkpoints in parallel, and rollback to them at will. With the special case that
   * rolling back to an older checkpoint will invalidate all overlapping younger checkpoints. This
   * opts-in that the checkpoint can be automatically destroyed by the rollback of an older
   * checkpoint.
   */
  NM_CHECKPOINT_CREATE_FLAG_ALLOW_OVERLAPPING(8);

  private static final Map<UInt32, NMCheckpointCreateFlags> NM_CHECKPOINT_CREATE_FLAGS_MAP;

  static {
    NM_CHECKPOINT_CREATE_FLAGS_MAP =
        Arrays.stream(NMCheckpointCreateFlags.values())
            .collect(Collectors.toMap(NMCheckpointCreateFlags::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMCheckpointCreateFlags(int i) {
    this.value = new UInt32(i);
  }

  public static NMCheckpointCreateFlags getNMCheckpointCreateFlags(UInt32 uInt32) {
    return NM_CHECKPOINT_CREATE_FLAGS_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
