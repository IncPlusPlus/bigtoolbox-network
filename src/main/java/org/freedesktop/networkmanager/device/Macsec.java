package org.freedesktop.networkmanager.device;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.networkmanager.Device;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Macsec")
public interface Macsec extends DBusInterface, Properties, Device {
  class PropertyNames {
    /** The object path of the parent device. */
    public static final String Parent = "Parent";
    /** The Secure Channel Identifier in use. */
    public static final String Sci = "Sci";
    /** The length of ICV (Integrity Check Value). */
    public static final String IcvLength = "IcvLength";
    /** The set of cryptographic algorithms in use (e.g. 0x0080020001000001 for GCM-AES-128). */
    public static final String CipherSuite = "CipherSuite";
    /** The size of the replay window. */
    public static final String Window = "Window";
    /** The value of the Association Number (0..3) for the Security Association in use. */
    public static final String EncodingSa = "EncodingSa";
    /** The validation mode for incoming packets (strict, check, disabled). */
    public static final String Validation = "Validation";
    /** Whether encryption of transmitted frames is enabled. */
    public static final String Encrypt = "Encrypt";
    /** Whether protection of transmitted frames is enabled. */
    public static final String Protect = "Protect";
    /** Whether the SCI is always included in SecTAG for transmitted frames. */
    public static final String IncludeSci = "IncludeSci";
    /** Whether the ES (End station) bit is enabled in SecTAG for transmitted frames. */
    public static final String Es = "Es";
    /** Whether the SCB (Single Copy Broadcast) bit is enabled in SecTAG for transmitted frames. */
    public static final String Scb = "Scb";
    /** Whether replay protection is enabled. */
    public static final String ReplayProtect = "ReplayProtect";
  }
}
