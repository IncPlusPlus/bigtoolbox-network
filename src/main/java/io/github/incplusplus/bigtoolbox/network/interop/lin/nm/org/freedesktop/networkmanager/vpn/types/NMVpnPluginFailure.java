package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.vpn.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

public enum NMVpnPluginFailure {
  /** Login failed. */
  NM_VPN_PLUGIN_FAILURE_LOGIN_FAILED(0),
  /** Connect failed. */
  NM_VPN_PLUGIN_FAILURE_CONNECT_FAILED(1),
  /** Invalid IP configuration returned from the VPN plugin. */
  NM_VPN_PLUGIN_FAILURE_BAD_IP_CONFIG(2);

  private static final Map<UInt32, NMVpnPluginFailure> NM_VPN_PLUGIN_FAILURE_MAP;

  static {
    NM_VPN_PLUGIN_FAILURE_MAP =
        Arrays.stream(NMVpnPluginFailure.values())
            .collect(Collectors.toMap(NMVpnPluginFailure::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMVpnPluginFailure(int i) {
    this.value = new UInt32(i);
  }

  public static NMVpnPluginFailure getNMVpnPluginFailure(UInt32 uInt32) {
    return NM_VPN_PLUGIN_FAILURE_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
