package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NMDeviceModemCapabilities;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Modem")
public interface Modem extends DBusInterface, Properties, Device {
  class PropertyNames {
    /**
     * The generic family of access technologies the modem supports. Not all capabilities are
     * available at the same time however; some modems require a firmware reload or other
     * reinitialization to switch between eg CDMA/EVDO and GSM/UMTS.
     *
     * <p>Returns {@link NMDeviceModemCapabilities}
     */
    public static final String ModemCapabilities = "ModemCapabilities";
    /**
     * The generic family of access technologies the modem currently supports without a firmware
     * reload or reinitialization.
     *
     * <p>Returns {@link NMDeviceModemCapabilities}
     */
    public static final String CurrentCapabilities = "CurrentCapabilities";
    /**
     * An identifier used by the modem backend (ModemManager) that aims to uniquely identify the a
     * device. Can be used to match a connection to a particular device.
     */
    public static final String DeviceId = "DeviceId";
    /**
     * The MCC and MNC (concatenated) of the network the modem is connected to. Blank if
     * disconnected or not a 3GPP modem.
     */
    public static final String OperatorCode = "OperatorCode";
    /** The access point name the modem is connected to. Blank if disconnected. */
    public static final String Apn = "Apn";
  }
}
