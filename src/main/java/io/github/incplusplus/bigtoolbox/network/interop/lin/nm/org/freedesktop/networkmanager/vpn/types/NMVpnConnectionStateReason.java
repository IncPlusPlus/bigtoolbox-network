package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.vpn.types;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMActiveConnectionStateReason;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

public enum NMVpnConnectionStateReason {
  /** The reason for the VPN connection state change is unknown. */
  NM_VPN_CONNECTION_STATE_REASON_UNKNOWN(
      NMActiveConnectionStateReason.NM_ACTIVE_CONNECTION_STATE_REASON_UNKNOWN),
  /** No reason was given for the VPN connection state change. */
  NM_VPN_CONNECTION_STATE_REASON_NONE(
      NMActiveConnectionStateReason.NM_ACTIVE_CONNECTION_STATE_REASON_NONE),
  /** The VPN connection changed state because the user disconnected it. */
  NM_VPN_CONNECTION_STATE_REASON_USER_DISCONNECTED(
      NMActiveConnectionStateReason.NM_ACTIVE_CONNECTION_STATE_REASON_USER_DISCONNECTED),
  /** The VPN connection changed state because the device it was using was disconnected. */
  NM_VPN_CONNECTION_STATE_REASON_DEVICE_DISCONNECTED(
      NMActiveConnectionStateReason.NM_ACTIVE_CONNECTION_STATE_REASON_DEVICE_DISCONNECTED),
  /** The service providing the VPN connection was stopped. */
  NM_VPN_CONNECTION_STATE_REASON_SERVICE_STOPPED(
      NMActiveConnectionStateReason.NM_ACTIVE_CONNECTION_STATE_REASON_SERVICE_STOPPED),
  /** The IP config of the VPN connection was invalid. */
  NM_VPN_CONNECTION_STATE_REASON_IP_CONFIG_INVALID(
      NMActiveConnectionStateReason.NM_ACTIVE_CONNECTION_STATE_REASON_IP_CONFIG_INVALID),
  /** The connection attempt to the VPN service timed out. */
  NM_VPN_CONNECTION_STATE_REASON_CONNECT_TIMEOUT(
      NMActiveConnectionStateReason.NM_ACTIVE_CONNECTION_STATE_REASON_CONNECT_TIMEOUT),
  /** A timeout occurred while starting the service providing the VPN connection. */
  NM_VPN_CONNECTION_STATE_REASON_SERVICE_START_TIMEOUT(
      NMActiveConnectionStateReason.NM_ACTIVE_CONNECTION_STATE_REASON_SERVICE_START_TIMEOUT),
  /** Starting the service starting the service providing the VPN connection failed. */
  NM_VPN_CONNECTION_STATE_REASON_SERVICE_START_FAILED(
      NMActiveConnectionStateReason.NM_ACTIVE_CONNECTION_STATE_REASON_SERVICE_START_FAILED),
  /** Necessary secrets for the VPN connection were not provided. */
  NM_VPN_CONNECTION_STATE_REASON_NO_SECRETS(
      NMActiveConnectionStateReason.NM_ACTIVE_CONNECTION_STATE_REASON_NO_SECRETS),
  /** Authentication to the VPN server failed. */
  NM_VPN_CONNECTION_STATE_REASON_LOGIN_FAILED(
      NMActiveConnectionStateReason.NM_ACTIVE_CONNECTION_STATE_REASON_LOGIN_FAILED),
  /** The connection was deleted from settings. */
  NM_VPN_CONNECTION_STATE_REASON_CONNECTION_REMOVED(
      NMActiveConnectionStateReason.NM_ACTIVE_CONNECTION_STATE_REASON_CONNECTION_REMOVED);

  private static final Map<UInt32, NMVpnConnectionStateReason> NM_VPN_CONNECTION_STATE_REASON_MAP;

  static {
    NM_VPN_CONNECTION_STATE_REASON_MAP =
        Arrays.stream(NMVpnConnectionStateReason.values())
            .collect(Collectors.toMap(NMVpnConnectionStateReason::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMVpnConnectionStateReason(
      NMActiveConnectionStateReason nmActiveConnectionStateReasonDeviceDisconnected) {
    this.value = nmActiveConnectionStateReasonDeviceDisconnected.getValue();
  }

  public static NMVpnConnectionStateReason getNMVpnConnectionStateReason(UInt32 uInt32) {
    return NM_VPN_CONNECTION_STATE_REASON_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
