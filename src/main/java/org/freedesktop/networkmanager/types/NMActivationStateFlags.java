package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * Flags describing the current activation state.
 */
public enum NMActivationStateFlags {
  /**
   * an alias for numeric zero, no flags set.
   */
  NM_ACTIVATION_STATE_FLAG_NONE(0),
  /**
   * the device is a master.
   */
  NM_ACTIVATION_STATE_FLAG_IS_MASTER(1),
  /**
   * the device is a slave.
   */
  NM_ACTIVATION_STATE_FLAG_IS_SLAVE(2),
  /**
   * layer2 is activated and ready.
   */
  NM_ACTIVATION_STATE_FLAG_LAYER2_READY(4),
  /**
   * IPv4 setting is completed.
   */
  NM_ACTIVATION_STATE_FLAG_IP4_READY(8),
  /**
   * IPv6 setting is completed.
   */
  NM_ACTIVATION_STATE_FLAG_IP6_READY(10),
  /**
   * The master has any slave devices attached. This only makes sense if the device is a master.
   */
  NM_ACTIVATION_STATE_FLAG_MASTER_HAS_SLAVES(20),
  /**
   * the lifetime of the activation is bound to the visibility of the connection profile, which in
   * turn depends on "connection.permissions" and whether a session for the user exists.
   */
  NM_ACTIVATION_STATE_FLAG_LIFETIME_BOUND_TO_PROFILE_VISIBILITY(40);

  private static final Map<UInt32, NMActivationStateFlags> NM_ACTIVATION_STATE_FLAGS_MAP;

  static {
    NM_ACTIVATION_STATE_FLAGS_MAP =
        Arrays.stream(NMActivationStateFlags.values())
            .collect(Collectors.toMap(NMActivationStateFlags::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMActivationStateFlags(int i) {
    this.value = new UInt32(i);
  }

  public static NMActivationStateFlags getNMActivationStateFlags(UInt32 uInt32) {
    return NM_ACTIVATION_STATE_FLAGS_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
