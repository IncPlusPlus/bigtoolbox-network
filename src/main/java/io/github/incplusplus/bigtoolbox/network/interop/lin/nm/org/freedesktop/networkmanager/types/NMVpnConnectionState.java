package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/** VPN connection states */
public enum NMVpnConnectionState {
  /** The state of the VPN connection is unknown. */
  NM_VPN_CONNECTION_STATE_UNKNOWN(0),
  /** The VPN connection is preparing to connect. */
  NM_VPN_CONNECTION_STATE_PREPARE(1),
  /** The VPN connection needs authorization credentials. */
  NM_VPN_CONNECTION_STATE_NEED_AUTH(2),
  /** The VPN connection is being established. */
  NM_VPN_CONNECTION_STATE_CONNECT(3),
  /** The VPN connection is getting an IP address. */
  NM_VPN_CONNECTION_STATE_IP_CONFIG_GET(4),
  /** The VPN connection is active. */
  NM_VPN_CONNECTION_STATE_ACTIVATED(5),
  /** The VPN connection failed. */
  NM_VPN_CONNECTION_STATE_FAILED(6),
  /** The VPN connection is disconnected. */
  NM_VPN_CONNECTION_STATE_DISCONNECTED(7);

  private static final Map<UInt32, NMVpnConnectionState> NM_VPN_CONNECTION_STATE_MAP;

  static {
    NM_VPN_CONNECTION_STATE_MAP =
        Arrays.stream(NMVpnConnectionState.values())
            .collect(Collectors.toMap(NMVpnConnectionState::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMVpnConnectionState(int i) {
    this.value = new UInt32(i);
  }

  public static NMVpnConnectionState getNMVpnConnectionState(UInt32 uInt32) {
    return NM_VPN_CONNECTION_STATE_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
