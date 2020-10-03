package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.settings;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMSettingsConnectionFlags;
import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

/** Represents a single network connection configuration. */
@SuppressWarnings("UnnecessaryInterfaceModifier")
@DBusInterfaceName("org.freedesktop.NetworkManager.Settings.Connection")
public interface Connection extends DBusInterface {

  /**
   * Update the connection with new settings and properties (replacing all previous settings and
   * properties) and save the connection to disk. Secrets may be part of the update request, and
   * will be either stored in persistent storage or sent to a Secret Agent for storage, depending on
   * the flags associated with each secret.
   *
   * @param properties New connection settings, properties, and (optionally) secrets.
   */
  void Update(Map<String, Map<String, Variant<?>>> properties);

  /**
   * Update the connection with new settings and properties (replacing all previous settings and
   * properties) but do not immediately save the connection to disk. Secrets may be part of the
   * update request and may sent to a Secret Agent for storage, depending on the flags associated
   * with each secret. Use the 'Save' method to save these changes to disk. Note that unsaved
   * changes will be lost if the connection is reloaded from disk (either automatically on file
   * change or due to an explicit ReloadConnections call).
   *
   * @param properties New connection settings, properties, and (optionally) secrets.
   */
  void UpdateUnsaved(Map<String, Map<String, Variant<?>>> properties);

  /** Delete the connection. */
  void Delete();

  /**
   * Get the settings maps describing this network configuration. This will never include any
   * secrets required for connection to the network, as those are often protected. Secrets must be
   * requested separately using the GetSecrets() call.
   *
   * @return The nested settings maps describing this object.
   */
  Map<String, Map<String, Variant<?>>> GetSettings();

  /**
   * Get the secrets belonging to this network configuration. Only secrets from persistent storage
   * or a Secret Agent running in the requestor's session will be returned. The user will never be
   * prompted for secrets as a result of this request.
   *
   * @param settingName Name of the setting to return secrets for. If empty, all all secrets will be
   *     returned.
   * @return Nested settings maps containing secrets.
   */
  Map<String, Map<String, Variant<?>>> GetSecrets(String settingName);

  /** Clear the secrets belonging to this network connection profile. */
  void ClearSecrets();

  /**
   * Saves a "dirty" connection (that had previously been updated with UpdateUnsaved) to persistent
   * storage.
   */
  void Save();

  /**
   * Update the connection with new settings and properties (replacing all previous settings and
   * properties). If the flag 0x1 is present, the connection is persisted to disk. If the flag 0x2
   * is present, the change is only made in memory (without touching an eventual profile on disk).
   * If neither 0x1 nor 0x2 is set, the change is made in memory only, if the connection is already
   * in memory only. The flags 0x4 (in-memory-detached) and 0x8 (in-memory-only) are like
   * "in-memory", but behave slightly different when migrating the profile from disk to in-memory.
   * The flag 0x20 (block-autoconnect) blocks auto-connect on the updated profile, and 0x40
   * (no-reapply) prevents "connection.zone" and "connection.metered" properties to take effect on
   * currently active devices. Secrets may be part of the update request, and will be either stored
   * in persistent storage or sent to a Secret Agent for storage, depending on the flags associated
   * with each secret.
   *
   * <p>Update2 is a extensible alternative to Update, UpdateUnsaved and Save.
   *
   * @param settings New connection settings, properties, and (optionally) secrets. Provide an empty
   *     array, to use the current settings.
   * @param flags optional flags argument. Currently supported flags are: "0x1" (to-disk), "0x2"
   *     (in-memory), "0x4" (in-memory-detached), "0x8" (in-memory-only), "0x10" (volatile), "0x20"
   *     (block-autoconnect), "0x40" (no-reapply). Unknown flags cause the call to fail.
   * @param args optional arguments dictionary, for extensibility. Currently no arguments are
   *     accepted. Specifying unknown keys causes the call to fail.
   * @return output argument, currently no results are returned.
   */
  Map<String, Variant<?>> Update2(
      Map<String, Map<String, Variant<?>>> settings, UInt32 flags, Map<String, Variant<?>> args);

  public static class Updated extends DBusSignal {
    public Updated(String path) throws DBusException {
      super(path);
    }
  }

  public static class Removed extends DBusSignal {
    public Removed(String path) throws DBusException {
      super(path);
    }
  }

  class PropertyNames {
    /**
     * If set, indicates that the in-memory state of the connection does not match the on-disk
     * state. This flag will be set when UpdateUnsaved() is called or when any connection details
     * change, and cleared when the connection is saved to disk via Save() or from internal
     * operations.
     */
    public static final String Unsaved = "Unsaved";
    /**
     * Additional flags of the connection profile.
     *
     * <p>Returns: {@link NMSettingsConnectionFlags}
     */
    public static final String Flags = "Flags";
    /** File that stores the connection in case the connection is file-backed. */
    public static final String Filename = "Filename";
  }
}
