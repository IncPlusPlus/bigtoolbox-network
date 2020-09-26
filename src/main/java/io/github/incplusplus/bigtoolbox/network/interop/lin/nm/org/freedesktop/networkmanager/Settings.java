package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.Pair;
import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

/**
 * The Settings interface allows clients to view and administrate the connections stored and used by
 * NetworkManager.
 */
@SuppressWarnings("UnnecessaryInterfaceModifier")
@DBusInterfaceName("org.freedesktop.NetworkManager.Settings")
public interface Settings extends DBusInterface, Properties {

  /**
   * List the saved network connections known to NetworkManager.
   *
   * @return List of connections.
   */
  public List<DBusInterface> ListConnections();

  /**
   * Retrieve the object path of a connection, given that connection's UUID.
   *
   * @param uuid The UUID to find the connection object path for.
   * @return The connection's object path.
   */
  public DBusInterface GetConnectionByUuid(String uuid);

  /**
   * Add new connection and save it to disk. This operation does not start the network connection
   * unless (1) device is idle and able to connect to the network described by the new connection,
   * and (2) the connection is allowed to be started automatically.
   *
   * @param connection Connection settings and properties.
   * @return Object path of the new connection that was just added.
   */
  public DBusInterface AddConnection(Map<String, Map<String, Variant<?>>> connection);

  /**
   * Add new connection but do not save it to disk immediately. This operation does not start the
   * network connection unless (1) device is idle and able to connect to the network described by
   * the new connection, and (2) the connection is allowed to be started automatically. Use the
   * 'Save' method on the connection to save these changes to disk.
   *
   * @param connection Connection settings and properties.
   * @return Object path of the new connection that was just added.
   */
  public DBusInterface AddConnectionUnsaved(Map<String, Map<String, Variant<?>>> connection);

  /**
   * Add a new connection profile.
   *
   * <p>Either the flags 0x1 (to-disk) or 0x2 (in-memory) must be specified. The effect is whether
   * to behave like AddConnection or AddConnectionUnsaved. If 0x20 (block-autoconnect) is specified,
   * autoconnect for the new profile is blocked from the beginnin. Otherwise, the profile might
   * automatically connect if a suitable device is around.
   *
   * <p>AddConnection2 is a extensible alternative to AddConnection, and AddConnectionUnsaved. The
   * new variant can do everything that the older variants could, and more.
   *
   * @param settings New connection settings, properties, and (optionally) secrets.
   * @param flags optional flags argument. Currently the following flags are supported: "0x1"
   *     (to-disk), "0x2" (in-memory), "0x20" (block-autoconnect). Unknown flags cause the call to
   *     fail.
   * @param args optional arguments dictionary, for extensibility. Currently no arguments are
   *     accepted. Specifying unknown keys causes the call to fail.
   * @return a two-tuple of the object path of the new connection that was added and the output
   *     argument. Currently, no additional results are returned.
   */
  public Pair<DBusInterface, Map<String, Variant<?>>> AddConnection2(
      Map<String, Map<String, Variant<?>>> settings, UInt32 flags, Map<String, Variant<?>> args);

  /**
   * @param filenames Array of paths to on-disk connection profiles in directories monitored by
   *     NetworkManager.
   * @return a two-tuple containing a boolean and a list of strings. The boolean represents success
   *     or failure of the operation as a whole. True if NetworkManager at least tried to load the
   *     indicated connections, even if it did not succeed. False if an error occurred before trying
   *     to load the connections (eg, permission denied). Note that before 1.20, NetworkManager had
   *     a bug and this @status value was wrong. It is better to assume success if the method does
   *     not return with a D-Bus error. On top of that, you can look at the list of strings
   *     (failures) to know whether any of the requested files failed. The list of strings contains
   *     the paths of connection files that could not be loaded.
   */
  public Pair<Boolean, List<String>> LoadConnections(List<String> filenames);

  /**
   * Tells NetworkManager to reload all connection files from disk, including noticing any added or
   * deleted connection files.
   *
   * @return This always returns TRUE.
   */
  public boolean ReloadConnections();

  /**
   * Save the hostname to persistent configuration.
   *
   * @param hostname The hostname to save to persistent configuration. If blank, the persistent
   *     hostname is cleared.
   */
  public void SaveHostname(String hostname);

  /**
   * Emitted when a new connection has been added after NetworkManager has started up and
   * initialized. This signal is not emitted for connections read while starting up, because
   * NetworkManager's D-Bus service is only available after all connections have been read, and to
   * prevent spamming listeners with too many signals at one time. To retrieve the initial
   * connection list, call the ListConnections() method once, and then listen for individual
   * Settings.NewConnection and Settings.Connection.Deleted signals for further updates.
   */
  public static class NewConnection extends DBusSignal {

    /** Object path of the new connection. */
    private final DBusInterface connection;

    NewConnection(String _path, DBusInterface _connection) throws DBusException {
      super(_path, _connection);
      this.connection = _connection;
    }

    public DBusInterface getConnection() {
      return connection;
    }
  }
  /**
   * Emitted when a connection is no longer available. This happens when the connection is deleted
   * or if it is no longer accessible by any of the system's logged-in users. After receipt of this
   * signal, the connection no longer exists and cannot be used. Also see the
   * Settings.Connection.Removed signal.
   */
  public static class ConnectionRemoved extends DBusSignal {
    /** Object path of the removed connection. */
    private final DBusInterface connection;

    ConnectionRemoved(String _path, DBusInterface _connection) throws DBusException {
      super(_path, _connection);
      this.connection = _connection;
    }

    public DBusInterface getConnection() {
      return connection;
    }
  }

  class PropertyNames {
    /** List of object paths of available network connection profiles. */
    public static final String Connections = "Connections";
    /** The machine hostname stored in persistent configuration. */
    public static final String Hostname = "Hostname";
    /** If true, adding and modifying connections is supported. */
    public static final String CanModify = "CanModify";
  }
}
