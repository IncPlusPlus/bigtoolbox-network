package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMSecretAgentCapabilities;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.types.UInt32;

/** Secret Agent Manager */
@SuppressWarnings("UnnecessaryInterfaceModifier")
@DBusInterfaceName("org.freedesktop.NetworkManager.AgentManager")
public interface AgentManager extends DBusInterface {

  /**
   * Called by secret Agents to register their ability to provide and save network secrets.
   *
   * @param identifier Identifies this agent; only one agent in each user session may use the same
   *     identifier. Identifier formatting follows the same rules as D-Bus bus names with the
   *     exception that the ':' character is not allowed. The valid set of characters is
   *     "[A-Z][a-z][0-9]_-." and the identifier is limited in length to 255 characters with a
   *     minimum of 3 characters. An example valid identifier is 'org.gnome.nm-applet' (without
   *     quotes).
   */
  public void Register(String identifier);

  /**
   * Like Register() but indicates agent capabilities to NetworkManager.
   *
   * @param identifier See the Register() method's identifier argument.
   * @param capabilities Indicates various agent capabilities to NetworkManager. Represents a value
   *     from {@link NMSecretAgentCapabilities}
   */
  public void RegisterWithCapabilities(String identifier, UInt32 capabilities);

  /**
   * Called by secret Agents to notify NetworkManager that they will no longer handle requests for
   * network secrets. Agents are automatically unregistered when they disconnect from D-Bus.
   */
  public void Unregister();
}
