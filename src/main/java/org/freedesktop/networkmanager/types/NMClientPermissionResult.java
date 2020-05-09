package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * NMClientPermissionResult values indicate what authorizations and permissions the user requires to
 * obtain a given NMClientPermission
 */
public enum NMClientPermissionResult {
  /**
   * unknown or no authorization
   */
  NM_CLIENT_PERMISSION_RESULT_UNKNOWN(0),
  /**
   * the permission is available
   */
  NM_CLIENT_PERMISSION_RESULT_YES(1),
  /**
   * authorization is necessary before the permission is available
   */
  NM_CLIENT_PERMISSION_RESULT_AUTH(2),
  /**
   * permission to perform the operation is denied by system policy
   */
  NM_CLIENT_PERMISSION_RESULT_NO(3);

  private static final Map<UInt32, NMClientPermissionResult> NM_CLIENT_PERMISSION_RESULT_MAP;

  static {
    NM_CLIENT_PERMISSION_RESULT_MAP =
        Arrays.stream(NMClientPermissionResult.values())
            .collect(Collectors.toMap(NMClientPermissionResult::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMClientPermissionResult(int i) {
    this.value = new UInt32(i);
  }

  public static NMClientPermissionResult getNMClientPermissionResult(UInt32 uInt32) {
    return NM_CLIENT_PERMISSION_RESULT_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
