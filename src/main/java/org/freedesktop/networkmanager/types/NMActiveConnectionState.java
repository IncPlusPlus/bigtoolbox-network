package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * NMActiveConnectionState values indicate the state of a connection to a specific network while it
 * is starting, connected, or disconnecting from that network.
 */
public enum NMActiveConnectionState {
  /** the state of the connection is unknown */
  NM_ACTIVE_CONNECTION_STATE_UNKNOWN(0),
  /** a network connection is being prepared */
  NM_ACTIVE_CONNECTION_STATE_ACTIVATING(1),
  /** there is a connection to the network */
  NM_ACTIVE_CONNECTION_STATE_ACTIVATED(2),
  /** the network connection is being torn down and cleaned up */
  NM_ACTIVE_CONNECTION_STATE_DEACTIVATING(3),
  /** the network connection is disconnected and will be removed */
  NM_ACTIVE_CONNECTION_STATE_DEACTIVATED(4);

  private static final Map<UInt32, NMActiveConnectionState> NM_ACTIVE_CONNECTION_STATE_MAP;

  static {
    NM_ACTIVE_CONNECTION_STATE_MAP =
        Arrays.stream(NMActiveConnectionState.values())
            .collect(Collectors.toMap(NMActiveConnectionState::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMActiveConnectionState(int i) {
    this.value = new UInt32(i);
  }

  public static NMActiveConnectionState getNMActiveConnectionState(UInt32 uInt32) {
    return NM_ACTIVE_CONNECTION_STATE_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
