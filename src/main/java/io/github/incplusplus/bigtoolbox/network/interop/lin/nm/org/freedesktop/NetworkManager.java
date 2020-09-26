package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMCapability;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMCheckpointCreateFlags;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMConnectivityState;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMMetered;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMRollbackResult;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMState;
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
 * Connection Manager
 *
 * <p>See the <a href="https://developer.gnome.org/NetworkManager/stable/"
 * target="_top">NetworkManager Reference Manual</a> and the <a
 * href="https://developer.gnome.org/NetworkManager/stable/gdbus-org.freedesktop.NetworkManager.html"
 * target="_top">D-Bus API Reference for the NetworkManager object</a> for details.
 */
@SuppressWarnings("UnnecessaryInterfaceModifier")
@DBusInterfaceName("org.freedesktop.NetworkManager")
public interface NetworkManager extends DBusInterface, Properties {

  /**
   * Reload NetworkManager's configuration and perform certain updates, like flushing a cache or
   * rewriting external state to disk. This is similar to sending SIGHUP to NetworkManager but it
   * allows for more fine-grained control over what to reload (see flags). It also allows non-root
   * access via PolicyKit and contrary to signals it is synchronous.
   *
   * <p>No flags (0x00) means to reload everything that is supported which is identical to sending a
   * SIGHUP. (0x01) means to reload the NetworkManager.conf configuration from disk. Note that this
   * does not include connections, which can be reloaded via Setting's ReloadConnections. (0x02)
   * means to update DNS configuration, which usually involves writing /etc/resolv.conf anew. (0x04)
   * means to restart the DNS plugin. This is for example useful when using dnsmasq plugin, which
   * uses additional configuration in /etc/NetworkManager/dnsmasq.d. If you edit those files, you
   * can restart the DNS plugin. This action shortly interrupts name resolution. Note that flags may
   * affect each other. For example, restarting the DNS plugin (0x04) implicitly updates DNS too
   * (0x02). Or when reloading the configuration (0x01), changes to DNS setting also cause a DNS
   * update (0x02). However, (0x01) does not involve restarting the DNS plugin (0x04) or update
   * resolv.conf (0x02), unless the DNS related configuration changes in NetworkManager.conf.
   *
   * @param flags optional flags to specify which parts shall be reloaded.
   */
  public void Reload(UInt32 flags);

  /**
   * Get the list of realized network devices.
   *
   * @return List of object paths of network devices known to the system. This list does not include
   *     device placeholders (see GetAllDevices()).
   */
  public List<DBusInterface> GetDevices();

  /**
   * Get the list of all network devices.
   *
   * @return List of object paths of network devices and device placeholders (eg, devices that do
   *     not yet exist but which can be automatically created by NetworkManager if one of their
   *     AvailableConnections was activated).
   */
  public List<DBusInterface> GetAllDevices();

  /**
   * Return the object path of the network device referenced by its IP interface name. Note that
   * some devices (usually modems) only have an IP interface name when they are connected.
   *
   * @param iface Interface name of the device to find.
   * @return Object path of the network device.
   */
  public DBusInterface GetDeviceByIpIface(String iface);

  /**
   * Activate a connection using the supplied device.
   *
   * @param connection The connection to activate. If "/" is given, a valid device path must be
   *     given, and NetworkManager picks the best connection to activate for the given device. VPN
   *     connections must always pass a valid connection path.
   * @param device The object path of device to be activated for physical connections. This
   *     parameter is ignored for VPN connections, because the specific_object (if provided)
   *     specifies the device to use.
   * @param specificObject The path of a connection-type-specific object this activation should use.
   *     This parameter is currently ignored for wired and mobile broadband connections, and the
   *     value of "/" should be used (ie, no specific object). For Wi-Fi connections, pass the
   *     object path of a specific AP from the card's scan list, or "/" to pick an AP automatically.
   *     For VPN connections, pass the object path of an ActiveConnection object that should serve
   *     as the "base" connection (to which the VPN connections lifetime will be tied), or pass "/"
   *     and NM will automatically use the current default device.
   * @return The path of the active connection object representing this active connection.
   */
  public DBusInterface ActivateConnection(
      DBusInterface connection, DBusInterface device, DBusInterface specificObject);

  /**
   * Adds a new connection using the given details (if any) as a template (automatically filling in
   * missing settings with the capabilities of the given device and specific object), then activate
   * the new connection. Cannot be used for VPN connections at this time.
   *
   * @param connection Connection settings and properties; if incomplete missing settings will be
   *     automatically completed using the given device and specific object.
   * @param device The object path of device to be activated using the given connection.
   * @param specificObject The path of a connection-type-specific object this activation should use.
   *     This parameter is currently ignored for wired and mobile broadband connections, and the
   *     value of "/" should be used (ie, no specific object). For Wi-Fi connections, pass the
   *     object path of a specific AP from the card's scan list, which will be used to complete the
   *     details of the newly added connection.
   * @return a two-tuple of the object path of the new connection that was just added and the path
   *     of the active connection object representing this active connection respectively.
   * @see #AddAndActivateConnection2(Map, DBusInterface, DBusInterface, Map)
   */
  public Pair<DBusInterface, DBusInterface> AddAndActivateConnection(
      Map<String, Map<String, Variant<?>>> connection,
      DBusInterface device,
      DBusInterface specificObject);

  /**
   * Adds a new connection using the given details (if any) as a template (automatically filling in
   * missing settings with the capabilities of the given device and specific object), then activate
   * the new connection. Cannot be used for VPN connections at this time.
   *
   * <p>This method extends AddAndActivateConnection to allow passing further parameters. At this
   * time the following options are supported:
   *
   * <p>* persist: A string value of either "disk" (default), "memory" or "volatile". If "memory" is
   * passed, the connection will not be saved to disk. If "volatile" is passed, the connection will
   * not be saved to disk and will be destroyed when disconnected. * bind-activation: Bind the
   * activation lifetime. Set to "dbus-name" to automatically disconnect when the requesting process
   * disappears from the bus. The default of "none" means the connection is kept activated normally.
   *
   * @param connection Connection settings and properties; if incomplete missing settings will be
   *     automatically completed using the given device and specific object.
   * @param device The object path of device to be activated using the given connection.
   * @param specificObject The path of a connection-type-specific object this activation should use.
   *     This parameter is currently ignored for wired and mobile broadband connections, and the
   *     value of "/" should be used (ie, no specific object). For Wi-Fi connections, pass the
   *     object path of a specific AP from the card's scan list, which will be used to complete the
   *     details of the newly added connection.
   * @param options Further options for the method call.
   * @return a triplet of the object path of the new connection that was just added, the path of the
   *     active connection object representing this active connection, and a dictionary of
   *     additional output arguments for future extension. Currently not additional output arguments
   *     are supported.
   */
  public Triplet<DBusInterface, DBusInterface, Map<String, Variant<?>>> AddAndActivateConnection2(
      Map<String, Map<String, Variant<?>>> connection,
      DBusInterface device,
      DBusInterface specificObject,
      Map<String, Variant<?>> options);

  /**
   * Deactivate an active connection.
   *
   * @param activeConnection The currently active connection to deactivate.
   */
  public void DeactivateConnection(DBusInterface activeConnection);

  /**
   * Control the NetworkManager daemon's sleep state. When asleep, all interfaces that it manages
   * are deactivated. When awake, devices are available to be activated. This command should not be
   * called directly by users or clients; it is intended for system suspend/resume tracking.
   *
   * @param sleep Indicates whether the NetworkManager daemon should sleep or wake.
   */
  public void Sleep(boolean sleep);

  /**
   * Control whether overall networking is enabled or disabled. When disabled, all interfaces that
   * NM manages are deactivated. When enabled, all managed interfaces are re-enabled and available
   * to be activated. This command should be used by clients that provide to users the ability to
   * enable/disable all networking.
   *
   * @param enable If FALSE, indicates that all networking should be disabled. If TRUE, indicates
   *     that NetworkManager should begin managing network devices.
   */
  public void Enable(boolean enable);

  /**
   * Returns the permissions a caller has for various authenticated operations that NetworkManager
   * provides, like Enable/Disable networking, changing Wi-Fi, WWAN, and WiMAX state, etc.
   *
   * @return Dictionary of available permissions and results. Each permission is represented by a
   *     name (ie "org.freedesktop.NetworkManager.Foobar") and each result is one of the following
   *     values: "yes" (the permission is available), "auth" (the permission is available after a
   *     successful authentication), or "no" (the permission is denied). Clients may use these
   *     values in the UI to indicate the ability to perform certain operations.
   */
  public Map<String, String> GetPermissions();

  /**
   * Set logging verbosity and which operations are logged.
   *
   * @param level One of [ERR, WARN, INFO, DEBUG, TRACE, OFF, KEEP]. This level is applied to the
   *     domains as specified in the domains argument. Except for the special level "KEEP", all
   *     unmentioned domains are disabled entirely. "KEEP" is special and allows not to change the
   *     current setting except for the specified domains. E.g. level=KEEP and
   *     domains=PLATFORM:DEBUG will only touch the platform domain.
   * @param domains A combination of logging domains separated by commas (','), or "NONE" to disable
   *     logging. Each domain enables logging for operations related to that domain. Available
   *     domains are: [PLATFORM, RFKILL, ETHER, WIFI, BT, MB, DHCP4, DHCP6, PPP, WIFI_SCAN, IP4,
   *     IP6, AUTOIP4, DNS, VPN, SHARING, SUPPLICANT, AGENTS, SETTINGS, SUSPEND, CORE, DEVICE, OLPC,
   *     WIMAX, INFINIBAND, FIREWALL, ADSL, BOND, VLAN, BRIDGE, DBUS_PROPS, TEAM, CONCHECK, DCB,
   *     DISPATCH, AUDIT]. In addition to these domains, the following special domains can be used:
   *     [NONE, ALL, DEFAULT, DHCP, IP]. You can also specify that some domains should log at a
   *     different level from the default by appending a colon (':') and a log level (eg,
   *     'WIFI:DEBUG'). If an empty string is given, the log level is changed but the current set of
   *     log domains remains unchanged.
   */
  public void SetLogging(String level, String domains);

  /**
   * Get current logging verbosity level and operations domains.
   *
   * <p>For available domains see SetLogging() call.
   *
   * @return a two-tuple of One of [ERR, WARN, INFO, DEBUG, TRACE] and the logging domains.
   */
  public Pair<String, String> GetLogging();

  /**
   * Re-check the network connectivity state.
   *
   * @return The current connectivity state. Can be interpreted using {@link
   *     NMState}
   */
  public UInt32 CheckConnectivity();

  /**
   * The overall networking state as determined by the NetworkManager daemon, based on the state of
   * network devices under its management.
   *
   * @return the current networking state. Can be interpreted using {@link
   *     NMState}
   */
  public UInt32 state();

  /**
   * Create a checkpoint of the current networking configuration for given interfaces. If
   * rollback_timeout is not zero, a rollback is automatically performed after the given timeout.
   *
   * @param devices A list of device paths for which a checkpoint should be created. An empty list
   *     means all devices.
   * @param rollbackTimeout The time in seconds until NetworkManager will automatically rollback to
   *     the checkpoint. Set to zero for infinite.
   * @param flags Flags for the creation. (See {@link
   *     NMCheckpointCreateFlags})
   * @return On success, the path of the new checkpoint.
   */
  public DBusInterface CheckpointCreate(
      List<DBusInterface> devices, UInt32 rollbackTimeout, UInt32 flags);

  /**
   * Destroy a previously created checkpoint.
   *
   * @param checkpoint The checkpoint to be destroyed. Set to empty to cancel all pending
   *     checkpoints.
   */
  public void CheckpointDestroy(DBusInterface checkpoint);

  /**
   * Rollback a checkpoint before the timeout is reached.
   *
   * @param checkpoint The checkpoint to be rolled back.
   * @return On return, a dictionary of devices and results. Devices are represented by their
   *     original D-Bus path; each result is a {@link
   *     NMRollbackResult}.
   */
  public Map<String, UInt32> CheckpointRollback(DBusInterface checkpoint);

  /**
   * Reset the timeout for rollback for the checkpoint.
   *
   * @param checkpoint the checkpoint to adjust the timeout for
   * @param addTimeout number of seconds from ~now~ in which the timeout will expire. Set to 0 to
   *     disable the timeout. Note that the added seconds start counting from now, not "Created"
   *     timestamp or the previous expiration time. Note that the "Created" property of the
   *     checkpoint will stay unchanged by this call. However, the "RollbackTimeout" will be
   *     recalculated to give the approximate new expiration time. The new "RollbackTimeout"
   *     property will be approximate up to one second precision, which is the accuracy of the
   *     property.
   */
  public void CheckpointAdjustRollbackTimeout(DBusInterface checkpoint, UInt32 addTimeout);
  /**
   * Emitted when system authorization details change, indicating that clients may wish to recheck
   * permissions with GetPermissions.
   */
  public static class CheckPermissions extends DBusSignal {
    public CheckPermissions(String path) throws DBusException {
      super(path);
    }
  }
  /** NetworkManager's state changed. */
  public static class StateChanged extends DBusSignal {
    /**
     * The new state of NetworkManager. Can be interpreted as an {@link
     * NMState}.
     */
    public final UInt32 state;

    public StateChanged(String path, UInt32 state) throws DBusException {
      super(path, state);
      this.state = state;
    }
  }
  /** A device was added to the system */
  public static class DeviceAdded extends DBusSignal {
    /** The object path of the newly added device. */
    public final DBusInterface device_path;

    public DeviceAdded(String path, DBusInterface device_path) throws DBusException {
      super(path, device_path);
      this.device_path = device_path;
    }
  }
  /** A device was removed from the system, and is no longer available. */
  public static class DeviceRemoved extends DBusSignal {
    /** The object path of the device that was just removed. */
    public final DBusInterface device_path;

    public DeviceRemoved(String path, DBusInterface device_path) throws DBusException {
      super(path, device_path);
      this.device_path = device_path;
    }
  }

  class PropertyNames {
    /**
     * The list of realized network devices. Realized devices are those which have backing resources
     * (eg from the kernel or a management daemon like ModemManager, teamd, etc).
     */
    public static final String Devices = "Devices";
    /**
     * The list of both realized and un-realized network devices. Un-realized devices are software
     * devices which do not yet have backing resources, but for which backing resources can be
     * created if the device is activated.
     */
    public static final String AllDevices = "AllDevices";
    /** The list of active checkpoints. */
    public static final String Checkpoints = "Checkpoints";
    /** Indicates if overall networking is currently enabled or not. See the Enable() method. */
    public static final String NetworkingEnabled = "NetworkingEnabled";
    /** Indicates if wireless is currently enabled or not. */
    public static final String WirelessEnabled = "WirelessEnabled";
    /**
     * Indicates if the wireless hardware is currently enabled, i.e. the state of the RF kill
     * switch.
     */
    public static final String WirelessHardwareEnabled = "WirelessHardwareEnabled";
    /** Indicates if mobile broadband devices are currently enabled or not. */
    public static final String WwanEnabled = "WwanEnabled";
    /**
     * Indicates if the mobile broadband hardware is currently enabled, i.e. the state of the RF
     * kill switch.
     */
    public static final String WwanHardwareEnabled = "WwanHardwareEnabled";
    /**
     * Doesn't have any meaning and is around only for compatibility reasons.
     *
     * @deprecated
     */
    @Deprecated public static final String WimaxEnabled = "WimaxEnabled";
    /**
     * Doesn't have any meaning and is around only for compatibility reasons.
     *
     * @deprecated
     */
    @Deprecated public static final String WimaxHardwareEnabled = "WimaxHardwareEnabled";
    /** List of active connection object paths. */
    public static final String ActiveConnections = "ActiveConnections";
    /**
     * The object path of the "primary" active connection being used to access the network. In
     * particular, if there is no VPN active, or the VPN does not have the default route, then this
     * indicates the connection that has the default route. If there is a VPN active with the
     * default route, then this indicates the connection that contains the route to the VPN
     * endpoint.
     */
    public static final String PrimaryConnection = "PrimaryConnection";
    /**
     * The connection type of the "primary" active connection being used to access the network. This
     * is the same as the Type property on the object indicated by PrimaryConnection.
     */
    public static final String PrimaryConnectionType = "PrimaryConnectionType";
    /**
     * Indicates whether the connectivity is metered. This is equivalent to the metered property of
     * the device associated with the primary connection.
     *
     * <p>Returns {@link NMMetered}
     */
    public static final String Metered = "Metered";
    /**
     * The object path of an active connection that is currently being activated and which is
     * expected to become the new PrimaryConnection when it finishes activating.
     */
    public static final String ActivatingConnection = "ActivatingConnection";
    /**
     * Indicates whether NM is still starting up; this becomes FALSE when NM has finished attempting
     * to activate every connection that it might be able to activate at startup.
     */
    public static final String Startup = "Startup";
    /** NetworkManager version. */
    public static final String Version = "Version";
    /**
     * The current set of capabilities. See {@link
     * NMCapability} for currently defined capability numbers.
     * The array is guaranteed to be sorted in ascending order without duplicates.
     */
    public static final String Capabilities = "Capabilities";
    /**
     * The overall state of the NetworkManager daemon.
     *
     * <p>This takes state of all active connections and the connectivity state into account to
     * produce a single indicator of the network accessibility status.
     *
     * <p>The graphical shells may use this property to provide network connection status indication
     * and applications may use this to check if Internet connection is accessible. Shell that is
     * able to cope with captive portals should use the "Connectivity" property to decide whether to
     * present a captive portal authentication dialog.
     *
     * <p>Returns {@link NMState}
     */
    public static final String State = "State";
    /**
     * The result of the last connectivity check. The connectivity check is triggered automatically
     * when a default connection becomes available, periodically and by calling a
     * CheckConnectivity() method.
     *
     * <p>This property is in general useful for the graphical shell to determine whether the
     * Internet access is being hijacked by an authentication gateway (a "captive portal"). In such
     * case it would typically present a web browser window to give the user a chance to
     * authenticate and call CheckConnectivity() when the user submits a form or dismisses the
     * window.
     *
     * <p>To determine the whether the user is able to access the Internet without dealing with
     * captive portals (e.g. to provide a network connection indicator or disable controls that
     * require Internet access), the "State" property is more suitable.
     *
     * <p>Returns {@link NMConnectivityState}
     */
    public static final String Connectivity = "Connectivity";
    /**
     * Indicates whether connectivity checking service has been configured. This may return true
     * even if the service is not currently enabled.
     *
     * <p>This is primarily intended for use in a privacy control panel, as a way to determine
     * whether to show an option to enable/disable the feature.
     */
    public static final String ConnectivityCheckAvailable = "ConnectivityCheckAvailable";
    /**
     * Indicates whether connectivity checking is enabled. This property can also be written to to
     * disable connectivity checking (as a privacy control panel might want to do).
     */
    public static final String ConnectivityCheckEnabled = "ConnectivityCheckEnabled";
    /** The URI that NetworkManager will hit to check if there is internet connectivity. */
    public static final String ConnectivityCheckUri = "ConnectivityCheckUri";
    /**
     * Dictionary of global DNS settings where the key is one of "searches", "options" and
     * "domains". The values for the "searches" and "options" keys are string arrays describing the
     * list of search domains and resolver options, respectively. The value of the "domains" key is
     * a second-level dictionary, where each key is a domain name, and each key's value is a
     * third-level dictionary with the keys "servers" and "options". "servers" is a string array of
     * DNS servers, "options" is a string array of domain-specific options.
     */
    public static final String GlobalDnsConfiguration = "GlobalDnsConfiguration";
  }
}
