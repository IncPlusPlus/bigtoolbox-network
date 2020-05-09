package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * Active connection state reasons.
 */
public enum NMActiveConnectionStateReason {
  /**
   * The reason for the active connection state change is unknown.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_UNKNOWN(0),
  /**
   * No reason was given for the active connection state change.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_NONE(1),
  /**
   * The active connection changed state because the user disconnected it.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_USER_DISCONNECTED(2),
  /**
   * The active connection changed state because the device it was using was disconnected.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_DEVICE_DISCONNECTED(3),
  /**
   * The service providing the VPN connection was stopped.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_SERVICE_STOPPED(4),
  /**
   * The IP config of the active connection was invalid.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_IP_CONFIG_INVALID(5),
  /**
   * The connection attempt to the VPN service timed out.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_CONNECT_TIMEOUT(6),
  /**
   * A timeout occurred while starting the service providing the VPN connection.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_SERVICE_START_TIMEOUT(7),
  /**
   * Starting the service providing the VPN connection failed.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_SERVICE_START_FAILED(8),
  /**
   * Necessary secrets for the connection were not provided.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_NO_SECRETS(9),
  /**
   * Authentication to the server failed.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_LOGIN_FAILED(10),
  /**
   * The connection was deleted from settings.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_CONNECTION_REMOVED(11),
  /**
   * Master connection of this connection failed to activate.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_DEPENDENCY_FAILED(12),
  /**
   * Could not create the software device link.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_DEVICE_REALIZE_FAILED(13),
  /**
   * The device this connection depended on disappeared.
   */
  NM_ACTIVE_CONNECTION_STATE_REASON_DEVICE_REMOVED(14);

  private static final Map<UInt32, NMActiveConnectionStateReason>
      NM_ACTIVE_CONNECTION_STATE_REASON_MAP;

  static {
    NM_ACTIVE_CONNECTION_STATE_REASON_MAP =
        Arrays.stream(NMActiveConnectionStateReason.values())
            .collect(
                Collectors.toMap(NMActiveConnectionStateReason::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMActiveConnectionStateReason(int i) {
    this.value = new UInt32(i);
  }

  public static NMActiveConnectionStateReason getNMActiveConnectionStateReason(UInt32 uInt32) {
    return NM_ACTIVE_CONNECTION_STATE_REASON_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
