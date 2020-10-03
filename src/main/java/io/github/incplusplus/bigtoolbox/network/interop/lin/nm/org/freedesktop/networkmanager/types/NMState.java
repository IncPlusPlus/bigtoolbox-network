package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/** NMState values indicate the current overall networking state. */
public enum NMState {
  /**
   * Networking state is unknown. This indicates a daemon error that makes it unable to reasonably
   * assess the state. In such event the applications are expected to assume Internet connectivity
   * might be present and not disable controls that require network access. The graphical shells may
   * hide the network accessibility indicator altogether since no meaningful status indication can
   * be provided.
   */
  NM_STATE_UNKNOWN(0),
  /** Networking is not enabled, the system is being suspended or resumed from suspend. */
  NM_STATE_ASLEEP(10),
  /**
   * There is no active network connection. The graphical shell should indicate no network
   * connectivity and the applications should not attempt to access the network.
   */
  NM_STATE_DISCONNECTED(20),
  /**
   * Network connections are being cleaned up. The applications should tear down their network
   * sessions.
   */
  NM_STATE_DISCONNECTING(30),
  /**
   * A network connection is being started The graphical shell should indicate the network is being
   * connected while the applications should still make no attempts to connect the network.
   */
  NM_STATE_CONNECTING(40),
  /**
   * There is only local IPv4 and/or IPv6 connectivity, but no default route to access the Internet.
   * The graphical shell should indicate no network connectivity.
   */
  NM_STATE_CONNECTED_LOCAL(50),
  /**
   * There is only site-wide IPv4 and/or IPv6 connectivity. This means a default route is available,
   * but the Internet connectivity check (see "Connectivity" property) did not succeed. The
   * graphical shell should indicate limited network connectivity.
   */
  NM_STATE_CONNECTED_SITE(60),
  /**
   * There is global IPv4 and/or IPv6 Internet connectivity This means the Internet connectivity
   * check succeeded, the graphical shell should indicate full network connectivity.
   */
  NM_STATE_CONNECTED_GLOBAL(70);

  private static final Map<UInt32, NMState> NM_STATE_MAP;

  static {
    NM_STATE_MAP =
        Arrays.stream(NMState.values())
            .collect(Collectors.toMap(NMState::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMState(int i) {
    this.value = new UInt32(i);
  }

  public static NMState getNMState(UInt32 uInt32) {
    return NM_STATE_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
