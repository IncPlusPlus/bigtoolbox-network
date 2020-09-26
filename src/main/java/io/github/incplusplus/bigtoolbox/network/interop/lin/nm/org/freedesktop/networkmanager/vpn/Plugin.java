package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.vpn;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.vpn.types.NMVpnServiceState;
import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

/** This interface is provided by plugins providing VPN services to the NetworkManager daemon. */
@DBusInterfaceName("org.freedesktop.NetworkManager.VPN.Plugin")
public interface Plugin extends DBusInterface {

  /**
   * Tells the plugin to connect. Interactive secrets requests (eg, emitting the SecretsRequired
   * signal) are not allowed.
   *
   * @param connection Describes the connection to be established.
   */
  void Connect(Map<String, Map<String, Variant<?>>> connection);

  /**
   * Tells the plugin to connect, allowing interactive secrets requests (eg the plugin is allowed to
   * emit the SecretsRequired signal if the VPN service indicates that it needs additional secrets
   * during the connect process).
   *
   * @param connection Describes the connection to be established.
   * @param details Additional details about the Connect process.
   */
  void ConnectInteractive(
      Map<String, Map<String, Variant<?>>> connection, Map<String, Variant<?>> details);

  /**
   * Asks the plugin whether the provided connection will require secrets to connect successfully.
   *
   * @param settings Describes the connection that may need secrets.
   * @return The setting name within the provided connection that requires secrets, if any.
   */
  String NeedSecrets(Map<String, Map<String, Variant<?>>> settings);

  /** Disconnect the plugin. */
  void Disconnect();

  /**
   * Set generic connection details on the connection.
   *
   * @param config Generic configuration details for the connection.
   */
  void SetConfig(Map<String, Variant<?>> config);

  /**
   * Set IPv4 details on the connection.
   *
   * @param config Ip4Config details for the connection. You must call SetConfig() before calling
   *     this.
   */
  void SetIp4Config(Map<String, Variant<?>> config);

  /**
   * Set IPv6 details on the connection.
   *
   * @param config Ip6Config details for the connection. You must call SetConfig() before calling
   *     this.
   */
  void SetIp6Config(Map<String, Variant<?>> config);

  /**
   * Indicate a failure to the plugin.
   *
   * @param reason The reason for the failure.
   */
  void SetFailure(String reason);

  /**
   * Called in response to a SecretsRequired signal to deliver updated secrets or other information
   * to the plugin.
   *
   * @param connection Describes the connection including the new secrets.
   */
  void NewSecrets(Map<String, Map<String, Variant<?>>> connection);

  /** Emitted when the plugin state changes. */
  class StateChanged extends DBusSignal {

    /** (NMVpnServiceState) The new state of the plugin. */
    private final UInt32 state;

    StateChanged(String _path, UInt32 _state) throws DBusException {
      super(_path, _state);
      this.state = _state;
    }

    public UInt32 getState() {
      return state;
    }
  }

  /**
   * Emitted during an ongoing ConnectInteractive() request when the plugin has determined that new
   * secrets are required. NetworkManager will then call the NewSecrets() method with a connection
   * hash including the new secrets.
   */
  class SecretsRequired extends DBusSignal {

    /**
     * Informational message, if any, about the request. For example, if a second PIN is required,
     * could indicate to the user to wait for the token code to change until entering the next PIN.
     */
    private final String message;
    /**
     * Array of strings of VPN secret names which the plugin thinks secrets may be required for, or
     * other VPN-specific data to be processed by the VPN's front-end.
     */
    private final List<String> secrets;

    SecretsRequired(String _path, String _message, List<String> _secrets) throws DBusException {
      super(_path, _message, _secrets);
      this.message = _message;
      this.secrets = _secrets;
    }

    public String getMessage() {
      return message;
    }

    public List<String> getSecrets() {
      return secrets;
    }
  }

  /** The plugin obtained generic configuration information. */
  class Config extends DBusSignal {

    /** The configuration information. */
    private final Map<String, Variant<?>> config;

    Config(String _path, Map<String, Variant<?>> _config) throws DBusException {
      super(_path, _config);
      this.config = _config;
    }

    public Map<String, Variant<?>> getConfig() {
      return config;
    }
  }

  /** The plugin obtained an IPv4 configuration. */
  class Ip4Config extends DBusSignal {

    /** The IPv4 configuration. */
    private final Map<String, Variant<?>> ip4config;

    Ip4Config(String _path, Map<String, Variant<?>> _ip4config) throws DBusException {
      super(_path, _ip4config);
      this.ip4config = _ip4config;
    }

    public Map<String, Variant<?>> getIp4config() {
      return ip4config;
    }
  }

  /** The plugin obtained an IPv6 configuration. */
  class Ip6Config extends DBusSignal {

    /** The IPv6 configuration. */
    private final Map<String, Variant<?>> ip6config;

    Ip6Config(String _path, Map<String, Variant<?>> _ip6config) throws DBusException {
      super(_path, _ip6config);
      this.ip6config = _ip6config;
    }

    public Map<String, Variant<?>> getIp6config() {
      return ip6config;
    }
  }

  /** Emitted when the plugin receives a login banner from the VPN service. */
  class LoginBanner extends DBusSignal {

    /** The login banner string. */
    private final String banner;

    LoginBanner(String _path, String _banner) throws DBusException {
      super(_path, _banner);
      this.banner = _banner;
    }

    public String getBanner() {
      return banner;
    }
  }

  /** Emitted when a failure in the VPN plugin occurs. */
  class Failure extends DBusSignal {

    /** (NMVpnPluginFailure) Reason code for the failure. */
    private final UInt32 reason;

    Failure(String _path, UInt32 _reason) throws DBusException {
      super(_path, _reason);
      this.reason = _reason;
    }

    public UInt32 getReason() {
      return reason;
    }
  }

  class PropertyNames {

    /**
     * The state of the plugin.
     *
     * <p>Returns: {@link NMVpnServiceState}
     */
    public static final String State = "State";
  }
}
