package org.freedesktop.networkmanager;

import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

/**
 * Private D-Bus interface used by secret agents that store and provide secrets to NetworkManager.
 * If an agent provides secrets to NetworkManager as part of connection creation, and the some of
 * those secrets are "agent owned" the agent should store those secrets itself and should not expect
 * its SaveSecrets() method to be called. SaveSecrets() will be called eg if some program other than
 * the agent itself (like a connection editor) changes the secrets out of band. The agent should
 * implement this D-Bus interface on an object with the path
 * /org/freedesktop/NetworkManager/SecretAgent.
 */
@DBusInterfaceName("org.freedesktop.NetworkManager.SecretAgent")
public interface SecretAgent extends DBusInterface {

  /**
   * Retrieve and return stored secrets, if any, or request new secrets from the agent's user. If
   * user interaction is allowed and the user enters new secrets, the agent is expected to save the
   * new secrets to persistent storage (if the secret's flags include AGENT_OWNED) as NetworkManager
   * will not send these secrets back to the same agent via a SaveSecrets() call. If the user
   * canceled any interaction, the agent should return the UserCanceled error (see below).
   *
   * @param connection Nested settings maps containing the connection for which secrets are being
   *     requested. This may contain system-owned secrets if the agent has successfully
   *     authenticated to modify system network settings and the GetSecrets request flags allow user
   *     interaction.
   * @param connectionPath Object path of the connection for which secrets are being requested.
   * @param settingName Setting name for which secrets are being requested.
   * @param hints Array of strings of key names in the requested setting for which NetworkManager
   *     thinks a secrets may be required, and/or well-known identifiers and data that may be useful
   *     to the client in processing the secrets request. Note that it's not always possible to
   *     determine which secret is required, so in some cases no hints may be given. The Agent
   *     should return any secrets it has, or that it thinks are required, regardless of what hints
   *     NetworkManager sends in this request. Some hints have special prefixes that provide
   *     information to the agent; for example, VPN requests may send server-specific messages
   *     prefixed with "x-vpn-message:".
   * @param flags (NMSecretAgentGetSecretsFlags) Flags which modify the behavior of the secrets
   *     request. If true, new secrets are assumed to be invalid or incorrect, and the agent should
   *     ask the user for new secrets. If false, existing secrets should be retrieved from storage
   *     and returned without interrupting the user.
   * @return Nested settings maps containing secrets. Each setting MUST contain at least the 'name'
   *     field, containing the name of the setting, and one or more secrets.
   */
  Map<String, Map<String, Variant<?>>> GetSecrets(
      Map<String, Map<String, Variant<?>>> connection,
      DBusInterface connectionPath,
      String settingName,
      List<String> hints,
      UInt32 flags);

  /**
   * Cancel a pending GetSecrets request for secrets of the given connection. Any GetSecrets request
   * with the same 'connection_path' and 'setting_name' that are given in a CancelGetSecrets request
   * should be canceled.
   *
   * @param connectionPath Object path of the connection for which, if secrets for the given
   *     'setting_name' are being requested, the request should be canceled.
   * @param settingName Setting name for which secrets for this connection were originally being
   *     requested.
   */
  void CancelGetSecrets(DBusInterface connectionPath, String settingName);

  /**
   * Save given secrets to backing storage.
   *
   * @param connection Nested settings maps containing the entire connection (including secrets),
   *     for which the agent should save the secrets to backing storage. This method will not be
   *     called when the agent itself is the process creating or updating a connection; in that case
   *     the agent is assumed to have already saved those secrets since it had them already.
   * @param connectionPath Object path of the connection for which the agent should save secrets to
   *     backing storage.
   */
  void SaveSecrets(Map<String, Map<String, Variant<?>>> connection, DBusInterface connectionPath);

  /**
   * Delete secrets from backing storage.
   *
   * @param connection Nested settings maps containing the connection properties (sans secrets), for
   *     which the agent should delete the secrets from backing storage.
   * @param connectionPath Object path of the connection for which the agent should delete secrets
   *     from backing storage.
   */
  void DeleteSecrets(Map<String, Map<String, Variant<?>>> connection, DBusInterface connectionPath);
}
