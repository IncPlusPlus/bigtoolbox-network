package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

public enum NMDeviceState {
  /** the device's state is unknown */
  NM_DEVICE_STATE_UNKNOWN(0),
  /** the device is recognized, but not managed by NetworkManager */
  NM_DEVICE_STATE_UNMANAGED(10),
  /**
   * the device is managed by NetworkManager, but is not available for use. Reasons may include the
   * wireless switched off, missing firmware, no ethernet carrier, missing supplicant or modem
   * manager, etc.
   */
  NM_DEVICE_STATE_UNAVAILABLE(20),
  /** the device can be activated, but is currently idle and not connected to a network. */
  NM_DEVICE_STATE_DISCONNECTED(30),
  /**
   * the device is preparing the connection to the network. This may include operations like
   * changing the MAC address, setting physical link properties, and anything else required to
   * connect to the requested network.
   */
  NM_DEVICE_STATE_PREPARE(40),
  /**
   * the device is connecting to the requested network. This may include operations like associating
   * with the Wi-Fi AP, dialing the modem, connecting to the remote Bluetooth device, etc.
   */
  NM_DEVICE_STATE_CONFIG(50),
  /**
   * the device requires more information to continue connecting to the requested network. This
   * includes secrets like WiFi passphrases, login passwords, PIN codes, etc.
   */
  NM_DEVICE_STATE_NEED_AUTH(60),
  /**
   * the device is requesting IPv4 and/or IPv6 addresses and routing information from the network.
   */
  NM_DEVICE_STATE_IP_CONFIG(70),
  /**
   * the device is checking whether further action is required for the requested network connection.
   * This may include checking whether only local network access is available, whether a captive
   * portal is blocking access to the Internet, etc.
   */
  NM_DEVICE_STATE_IP_CHECK(80),
  /**
   * the device is waiting for a secondary connection (like a VPN) which must activated before the
   * device can be activated
   */
  NM_DEVICE_STATE_SECONDARIES(90),
  /** the device has a network connection, either local or global. */
  NM_DEVICE_STATE_ACTIVATED(100),
  /**
   * a disconnection from the current network connection was requested, and the device is cleaning
   * up resources used for that connection. The network connection may still be valid.
   */
  NM_DEVICE_STATE_DEACTIVATING(110),
  /**
   * the device failed to connect to the requested network and is cleaning up the connection request
   */
  NM_DEVICE_STATE_FAILED(120);

  private static final Map<UInt32, NMDeviceState> NM_DEVICE_STATE_MAP;

  static {
    NM_DEVICE_STATE_MAP =
        Arrays.stream(NMDeviceState.values())
            .collect(Collectors.toMap(NMDeviceState::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMDeviceState(int i) {
    this.value = new UInt32(i);
  }

  public UInt32 getValue() {
    return value;
  }

  public NMDeviceState getNMDeviceState(UInt32 uInt32) {
    return NM_DEVICE_STATE_MAP.get(uInt32);
  }
}
