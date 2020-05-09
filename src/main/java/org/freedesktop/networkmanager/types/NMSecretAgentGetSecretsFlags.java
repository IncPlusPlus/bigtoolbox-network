package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * NMSecretAgentGetSecretsFlags values modify the behavior of a GetSecrets request.
 */
public enum NMSecretAgentGetSecretsFlags {
  /**
   * no special behavior; by default no user interaction is allowed and requests for secrets are
   * fulfilled from persistent storage, or if no secrets are available an error is returned.
   */
  NM_SECRET_AGENT_GET_SECRETS_FLAG_NONE(0),
  /**
   * allows the request to interact with the user, possibly prompting via UI for secrets if any are
   * required, or if none are found in persistent storage.
   */
  NM_SECRET_AGENT_GET_SECRETS_FLAG_ALLOW_INTERACTION(1),
  /**
   * explicitly prompt for new secrets from the user. This flag signals that NetworkManager thinks
   * any existing secrets are invalid or wrong. This flag implies that interaction is allowed.
   */
  NM_SECRET_AGENT_GET_SECRETS_FLAG_REQUEST_NEW(2),
  /**
   * set if the request was initiated by user-requested action via the D-Bus interface, as opposed
   * to automatically initiated by NetworkManager in response to (for example) scan results or
   * carrier changes.
   */
  NM_SECRET_AGENT_GET_SECRETS_FLAG_USER_REQUESTED(4),
  /**
   * indicates that WPS enrollment is active with PBC method. The agent may suggest that the user
   * pushes a button on the router instead of supplying a PSK.
   */
  NM_SECRET_AGENT_GET_SECRETS_FLAG_WPS_PBC_ACTIVE(8),
  /**
   * Internal flag, not part of the D-Bus API.
   */
  NM_SECRET_AGENT_GET_SECRETS_FLAG_ONLY_SYSTEM(80000000),
  /**
   * Internal flag, not part of the D-Bus API.
   */
  NM_SECRET_AGENT_GET_SECRETS_FLAG_NO_ERRORS(40000000);

  private static final Map<UInt32, NMSecretAgentGetSecretsFlags>
      NM_SECRET_AGENT_GET_SECRETS_FLAGS_MAP;

  static {
    NM_SECRET_AGENT_GET_SECRETS_FLAGS_MAP =
        Arrays.stream(NMSecretAgentGetSecretsFlags.values())
            .collect(Collectors.toMap(NMSecretAgentGetSecretsFlags::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMSecretAgentGetSecretsFlags(int i) {
    this.value = new UInt32(i);
  }

  public static NMSecretAgentGetSecretsFlags getNMSecretAgentGetSecretsFlags(UInt32 uInt32) {
    return NM_SECRET_AGENT_GET_SECRETS_FLAGS_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
