package org.freedesktop.networkmanager.vpn.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

public enum NMVpnServiceState {
  /** The state of the VPN plugin is unknown. */
  NM_VPN_SERVICE_STATE_UNKNOWN(0),
  /** The VPN plugin is initialized. */
  NM_VPN_SERVICE_STATE_INIT(1),
  /** Not used. */
  NM_VPN_SERVICE_STATE_SHUTDOWN(2),
  /** The plugin is attempting to connect to a VPN server. */
  NM_VPN_SERVICE_STATE_STARTING(3),
  /** The plugin has connected to a VPN server. */
  NM_VPN_SERVICE_STATE_STARTED(4),
  /** The plugin is disconnecting from the VPN server. */
  NM_VPN_SERVICE_STATE_STOPPING(5),
  /** The plugin has disconnected from the VPN server. */
  NM_VPN_SERVICE_STATE_STOPPED(6);

  private static final Map<UInt32, NMVpnServiceState> NM_VPN_SERVICE_STATE_MAP;

  static {
    NM_VPN_SERVICE_STATE_MAP =
        Arrays.stream(NMVpnServiceState.values())
            .collect(Collectors.toMap(NMVpnServiceState::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMVpnServiceState(int i) {
    this.value = new UInt32(i);
  }

  public static NMVpnServiceState getNMVpnServiceState(UInt32 uInt32) {
    return NM_VPN_SERVICE_STATE_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
