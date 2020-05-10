package org.freedesktop.networkmanager;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

@DBusInterfaceName("org.freedesktop.NetworkManager.AccessPoint")
public interface AccessPoint extends DBusInterface, Properties {
  class PropertyNames {
    /**
     * Flags describing the capabilities of the access point.
     *
     * <p>Returns {@link org.freedesktop.networkmanager.types.NM80211ApFlags}
     */
    public static final String Flags = "Flags";
    /**
     * Flags describing the access point's capabilities according to WPA (Wifi Protected Access).
     *
     * <p>Returns {@link org.freedesktop.networkmanager.types.NM80211ApSecurityFlags}
     */
    public static final String WpaFlags = "WpaFlags";
    /**
     * Flags describing the access point's capabilities according to the RSN (Robust Secure Network)
     * protocol.
     *
     * <p>Returns {@link org.freedesktop.networkmanager.types.NM80211ApSecurityFlags}
     */
    public static final String RsnFlags = "RsnFlags";
    /** The Service Set Identifier identifying the access point. */
    public static final String Ssid = "Ssid";
    /** The radio channel frequency in use by the access point, in MHz. */
    public static final String Frequency = "Frequency";
    /** The hardware address (BSSID) of the access point. */
    public static final String HwAddress = "HwAddress";
    /**
     * Describes the operating mode of the access point.
     *
     * <p>Returns {@link org.freedesktop.networkmanager.types.NM80211Mode}
     */
    public static final String Mode = "Mode";
    /** The maximum bitrate this access point is capable of, in kilobits/second (Kb/s). */
    public static final String MaxBitrate = "MaxBitrate";
    /** The current signal quality of the access point, in percent. */
    public static final String Strength = "Strength";
    /**
     * The timestamp (in CLOCK_BOOTTIME seconds) for the last time the access point was found in
     * scan results. A value of -1 means the access point has never been found in scan results.
     */
    public static final String LastSeen = "LastSeen";
  }
}
