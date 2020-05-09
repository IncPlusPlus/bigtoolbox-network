package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * The tunneling mode.
 */
public enum NMIPTunnelMode {
  /**
   * Unknown/unset tunnel mode
   */
  NM_IP_TUNNEL_MODE_UNKNOWN(0),
  /**
   * IP in IP tunnel
   */
  NM_IP_TUNNEL_MODE_IPIP(0),
  /**
   * GRE tunnel
   */
  NM_IP_TUNNEL_MODE_GRE(0),
  /**
   * SIT tunnel
   */
  NM_IP_TUNNEL_MODE_SIT(0),
  /**
   * ISATAP tunnel
   */
  NM_IP_TUNNEL_MODE_ISATAP(0),
  /**
   * VTI tunnel
   */
  NM_IP_TUNNEL_MODE_VTI(0),
  /**
   * IPv6 in IPv6 tunnel
   */
  NM_IP_TUNNEL_MODE_IP6IP6(0),
  /**
   * IPv4 in IPv6 tunnel
   */
  NM_IP_TUNNEL_MODE_IPIP6(0),
  /**
   * IPv6 GRE tunnel
   */
  NM_IP_TUNNEL_MODE_IP6GRE(0),
  /**
   * IPv6 VTI tunnel
   */
  NM_IP_TUNNEL_MODE_VTI6(0),
  /**
   * GRETAP tunnel
   */
  NM_IP_TUNNEL_MODE_GRETAP(0),
  /**
   * IPv6 GRETAP tunnel
   */
  NM_IP_TUNNEL_MODE_IP6GRETAP(0);

  private static final Map<UInt32, NMIPTunnelMode> NMIP_TUNNEL_MODE_MAP;

  static {
    NMIP_TUNNEL_MODE_MAP =
        Arrays.stream(NMIPTunnelMode.values())
            .collect(Collectors.toMap(NMIPTunnelMode::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMIPTunnelMode(int i) {
    this.value = new UInt32(i);
  }

  public static NMIPTunnelMode getNMIPTunnelMode(UInt32 uInt32) {
    return NMIP_TUNNEL_MODE_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
