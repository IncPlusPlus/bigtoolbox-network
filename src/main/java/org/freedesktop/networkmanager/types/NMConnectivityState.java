package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

public enum NMConnectivityState {
  /**
   * Network connectivity is unknown. This means the connectivity checks are disabled (e.g. on
   * server installations) or has not run yet. The graphical shell should assume the Internet
   * connection might be available and not present a captive portal window.
   */
  NM_CONNECTIVITY_UNKNOWN(0),
  /**
   * The host is not connected to any network. There's no active connection that contains a default
   * route to the internet and thus it makes no sense to even attempt a connectivity check. The
   * graphical shell should use this state to indicate the network connection is unavailable.
   */
  NM_CONNECTIVITY_NONE(1),
  /**
   * The Internet connection is hijacked by a captive portal gateway. The graphical shell may open a
   * sandboxed web browser window (because the captive portals typically attempt a man-in-the-middle
   * attacks against the https connections) for the purpose of authenticating to a gateway and
   * retrigger the connectivity check with CheckConnectivity() when the browser window is dismissed.
   */
  NM_CONNECTIVITY_PORTAL(2),
  /**
   * The host is connected to a network, does not appear to be able to reach the full Internet, but
   * a captive portal has not been detected.
   */
  NM_CONNECTIVITY_LIMITED(3),
  /** The host is connected to a network, and appears to be able to reach the full Internet. */
  NM_CONNECTIVITY_FULL(4);

  private static final Map<UInt32, NMConnectivityState> NM_CONNECTIVITY_STATE_MAP;

  static {
    NM_CONNECTIVITY_STATE_MAP =
        Arrays.stream(NMConnectivityState.values())
            .collect(Collectors.toMap(NMConnectivityState::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMConnectivityState(int i) {
    this.value = new UInt32(i);
  }

  public static NMConnectivityState getNMConnectivityState(UInt32 uInt32) {
    return NM_CONNECTIVITY_STATE_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
