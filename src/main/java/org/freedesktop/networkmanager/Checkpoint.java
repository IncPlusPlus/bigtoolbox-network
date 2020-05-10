package org.freedesktop.networkmanager;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

/** A snapshot of NetworkManager state for a given device list */
@DBusInterfaceName("org.freedesktop.NetworkManager.Checkpoint")
public interface Checkpoint extends DBusInterface, Properties {
  class PropertyNames {
    /** Array of object paths for devices which are part of this checkpoint. */
    public static final String Devices = "Devices";
    /** The timestamp (in CLOCK_BOOTTIME milliseconds) of checkpoint creation. */
    public static final String Created = "Created";
    /** Timeout in seconds for automatic rollback, or zero. */
    public static final String RollbackTimeout = "RollbackTimeout";
  }
}
