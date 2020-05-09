package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * NMClientPermission values indicate various permissions that NetworkManager clients can obtain to
 * perform certain tasks on behalf of the current user.
 */
public enum NMClientPermission {
  /** unknown or no permission */
  NM_CLIENT_PERMISSION_NONE(0),
  /** controls whether networking can be globally enabled or disabled */
  NM_CLIENT_PERMISSION_ENABLE_DISABLE_NETWORK(1),
  /** controls whether Wi-Fi can be globally enabled or disabled */
  NM_CLIENT_PERMISSION_ENABLE_DISABLE_WIFI(2),
  /** controls whether WWAN (3G) can be globally enabled or disabled */
  NM_CLIENT_PERMISSION_ENABLE_DISABLE_WWAN(3),
  /** controls whether WiMAX can be globally enabled or disabled */
  NM_CLIENT_PERMISSION_ENABLE_DISABLE_WIMAX(4),
  /** controls whether the client can ask NetworkManager to sleep and wake */
  NM_CLIENT_PERMISSION_SLEEP_WAKE(4),
  /** controls whether networking connections can be started, stopped, and changed */
  NM_CLIENT_PERMISSION_NETWORK_CONTROL(6),
  /** controls whether a password protected Wi-Fi hotspot can be created */
  NM_CLIENT_PERMISSION_WIFI_SHARE_PROTECTED(7),
  /** controls whether an open Wi-Fi hotspot can be created */
  NM_CLIENT_PERMISSION_WIFI_SHARE_OPEN(8),
  /** controls whether connections that are available to all users can be modified */
  NM_CLIENT_PERMISSION_SETTINGS_MODIFY_SYSTEM(9),
  /** controls whether connections owned by the current user can be modified */
  NM_CLIENT_PERMISSION_SETTINGS_MODIFY_OWN(10),
  /** controls whether the persistent hostname can be changed */
  NM_CLIENT_PERMISSION_SETTINGS_MODIFY_HOSTNAME(11),
  /** modify persistent global DNS configuration */
  NM_CLIENT_PERMISSION_SETTINGS_MODIFY_GLOBAL_DNS(12),
  /** controls access to Reload. */
  NM_CLIENT_PERMISSION_RELOAD(13),
  /** permission to create checkpoints. */
  NM_CLIENT_PERMISSION_CHECKPOINT_ROLLBACK(14),
  /** controls whether device statistics can be globally enabled or disabled */
  NM_CLIENT_PERMISSION_ENABLE_DISABLE_STATISTICS(15),
  /** controls whether connectivity check can be enabled or disabled */
  NM_CLIENT_PERMISSION_ENABLE_DISABLE_CONNECTIVITY_CHECK(16),
  /** controls whether wifi scans can be performed */
  NM_CLIENT_PERMISSION_WIFI_SCAN(17);

  private static final Map<UInt32, NMClientPermission> NM_CLIENT_PERMISSION_MAP;

  static {
    NM_CLIENT_PERMISSION_MAP =
        Arrays.stream(NMClientPermission.values())
            .collect(Collectors.toMap(NMClientPermission::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMClientPermission(int i) {
    this.value = new UInt32(i);
  }

  public static NMClientPermission getNMClientPermission(UInt32 uInt32) {
    return NM_CLIENT_PERMISSION_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
