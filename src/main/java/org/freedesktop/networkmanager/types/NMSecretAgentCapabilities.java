package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * NMSecretAgentCapabilities indicate various capabilities of the agent.
 */
public enum NMSecretAgentCapabilities {
  /**
   * the agent supports no special capabilities
   */
  NM_SECRET_AGENT_CAPABILITY_NONE(0),
  /**
   * the agent supports passing hints to VPN plugin authentication dialogs.
   */
  NM_SECRET_AGENT_CAPABILITY_VPN_HINTS(1);

  private static final Map<UInt32, NMSecretAgentCapabilities> NM_SECRET_AGENT_CAPABILITIES_MAP;

  static {
    NM_SECRET_AGENT_CAPABILITIES_MAP =
        Arrays.stream(NMSecretAgentCapabilities.values())
            .collect(Collectors.toMap(NMSecretAgentCapabilities::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMSecretAgentCapabilities(int i) {
    this.value = new UInt32(i);
  }

  public static NMSecretAgentCapabilities getNMSecretAgentCapabilities(UInt32 uInt32) {
    return NM_SECRET_AGENT_CAPABILITIES_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
