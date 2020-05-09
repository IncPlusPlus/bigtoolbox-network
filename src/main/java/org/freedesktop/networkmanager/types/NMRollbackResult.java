package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/** The result of a checkpoint Rollback() operation for a specific device. */
public enum NMRollbackResult {
  /** the rollback succeeded. */
  NM_ROLLBACK_RESULT_OK(0),
  /** the device no longer exists. */
  NM_ROLLBACK_RESULT_ERR_NO_DEVICE(1),
  /** the device is now unmanaged. */
  NM_ROLLBACK_RESULT_ERR_DEVICE_UNMANAGED(2),
  /** other errors during rollback. */
  NM_ROLLBACK_RESULT_ERR_FAILED(3);

  private static final Map<UInt32, NMRollbackResult> NM_ROLLBACK_RESULT_MAP;

  static {
    NM_ROLLBACK_RESULT_MAP =
        Arrays.stream(NMRollbackResult.values())
            .collect(Collectors.toMap(NMRollbackResult::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMRollbackResult(int i) {
    this.value = new UInt32(i);
  }

  public static NMRollbackResult getNMRollbackResult(UInt32 uInt32) {
    return NM_ROLLBACK_RESULT_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
