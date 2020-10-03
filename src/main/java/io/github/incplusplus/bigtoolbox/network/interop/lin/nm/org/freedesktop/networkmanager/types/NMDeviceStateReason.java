package io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

public enum NMDeviceStateReason {
  /** No reason given */
  NM_DEVICE_STATE_REASON_NONE(0),
  /** Unknown error */
  NM_DEVICE_STATE_REASON_UNKNOWN(1),
  /** Device is now managed */
  NM_DEVICE_STATE_REASON_NOW_MANAGED(2),
  /** Device is now unmanaged */
  NM_DEVICE_STATE_REASON_NOW_UNMANAGED(3),
  /** The device could not be readied for configuration */
  NM_DEVICE_STATE_REASON_CONFIG_FAILED(4),
  /** IP configuration could not be reserved (no available address, timeout, etc) */
  NM_DEVICE_STATE_REASON_IP_CONFIG_UNAVAILABLE(5),
  /** The IP config is no longer valid */
  NM_DEVICE_STATE_REASON_IP_CONFIG_EXPIRED(6),
  /** Secrets were required, but not provided */
  NM_DEVICE_STATE_REASON_NO_SECRETS(7),
  /** 802.1x supplicant disconnected */
  NM_DEVICE_STATE_REASON_SUPPLICANT_DISCONNECT(8),
  /** 802.1x supplicant configuration failed */
  NM_DEVICE_STATE_REASON_SUPPLICANT_CONFIG_FAILED(9),
  /** 802.1x supplicant failed */
  NM_DEVICE_STATE_REASON_SUPPLICANT_FAILED(10),
  /** 802.1x supplicant took too long to authenticate */
  NM_DEVICE_STATE_REASON_SUPPLICANT_TIMEOUT(11),
  /** PPP service failed to start */
  NM_DEVICE_STATE_REASON_PPP_START_FAILED(12),
  /** PPP service disconnected */
  NM_DEVICE_STATE_REASON_PPP_DISCONNECT(13),
  /** PPP failed */
  NM_DEVICE_STATE_REASON_PPP_FAILED(14),
  /** DHCP client failed to start */
  NM_DEVICE_STATE_REASON_DHCP_START_FAILED(15),
  /** DHCP client error */
  NM_DEVICE_STATE_REASON_DHCP_ERROR(16),
  /** DHCP client failed */
  NM_DEVICE_STATE_REASON_DHCP_FAILED(17),
  /** Shared connection service failed to start */
  NM_DEVICE_STATE_REASON_SHARED_START_FAILED(18),
  /** Shared connection service failed */
  NM_DEVICE_STATE_REASON_SHARED_FAILED(19),
  /** AutoIP service failed to start */
  NM_DEVICE_STATE_REASON_AUTOIP_START_FAILED(20),
  /** AutoIP service error */
  NM_DEVICE_STATE_REASON_AUTOIP_ERROR(21),
  /** AutoIP service failed */
  NM_DEVICE_STATE_REASON_AUTOIP_FAILED(22),
  /** The line is busy */
  NM_DEVICE_STATE_REASON_MODEM_BUSY(23),
  /** No dial tone */
  NM_DEVICE_STATE_REASON_MODEM_NO_DIAL_TONE(24),
  /** No carrier could be established */
  NM_DEVICE_STATE_REASON_MODEM_NO_CARRIER(25),
  /** The dialing request timed out */
  NM_DEVICE_STATE_REASON_MODEM_DIAL_TIMEOUT(26),
  /** The dialing attempt failed */
  NM_DEVICE_STATE_REASON_MODEM_DIAL_FAILED(27),
  /** Modem initialization failed */
  NM_DEVICE_STATE_REASON_MODEM_INIT_FAILED(28),
  /** Failed to select the specified APN */
  NM_DEVICE_STATE_REASON_GSM_APN_FAILED(29),
  /** Not searching for networks */
  NM_DEVICE_STATE_REASON_GSM_REGISTRATION_NOT_SEARCHING(30),
  /** Network registration denied */
  NM_DEVICE_STATE_REASON_GSM_REGISTRATION_DENIED(31),
  /** Network registration timed out */
  NM_DEVICE_STATE_REASON_GSM_REGISTRATION_TIMEOUT(32),
  /** Failed to register with the requested network */
  NM_DEVICE_STATE_REASON_GSM_REGISTRATION_FAILED(33),
  /** PIN check failed */
  NM_DEVICE_STATE_REASON_GSM_PIN_CHECK_FAILED(34),
  /** Necessary firmware for the device may be missing */
  NM_DEVICE_STATE_REASON_FIRMWARE_MISSING(35),
  /** The device was removed */
  NM_DEVICE_STATE_REASON_REMOVED(36),
  /** NetworkManager went to sleep */
  NM_DEVICE_STATE_REASON_SLEEPING(37),
  /** The device's active connection disappeared */
  NM_DEVICE_STATE_REASON_CONNECTION_REMOVED(38),
  /** Device disconnected by user or client */
  NM_DEVICE_STATE_REASON_USER_REQUESTED(39),
  /** Carrier/link changed */
  NM_DEVICE_STATE_REASON_CARRIER(40),
  /** The device's existing connection was assumed */
  NM_DEVICE_STATE_REASON_CONNECTION_ASSUMED(41),
  /** The supplicant is now available */
  NM_DEVICE_STATE_REASON_SUPPLICANT_AVAILABLE(42),
  /** The modem could not be found */
  NM_DEVICE_STATE_REASON_MODEM_NOT_FOUND(43),
  /** The Bluetooth connection failed or timed out */
  NM_DEVICE_STATE_REASON_BT_FAILED(44),
  /** GSM Modem's SIM Card not inserted */
  NM_DEVICE_STATE_REASON_GSM_SIM_NOT_INSERTED(45),
  /** GSM Modem's SIM Pin required */
  NM_DEVICE_STATE_REASON_GSM_SIM_PIN_REQUIRED(46),
  /** GSM Modem's SIM Puk required */
  NM_DEVICE_STATE_REASON_GSM_SIM_PUK_REQUIRED(47),
  /** GSM Modem's SIM wrong */
  NM_DEVICE_STATE_REASON_GSM_SIM_WRONG(48),
  /** InfiniBand device does not support connected mode */
  NM_DEVICE_STATE_REASON_INFINIBAND_MODE(49),
  /** A dependency of the connection failed */
  NM_DEVICE_STATE_REASON_DEPENDENCY_FAILED(50),
  /** Problem with the RFC 2684 Ethernet over ADSL bridge */
  NM_DEVICE_STATE_REASON_BR2684_FAILED(51),
  /** ModemManager not running */
  NM_DEVICE_STATE_REASON_MODEM_MANAGER_UNAVAILABLE(52),
  /** The Wi-Fi network could not be found */
  NM_DEVICE_STATE_REASON_SSID_NOT_FOUND(53),
  /** A secondary connection of the base connection failed */
  NM_DEVICE_STATE_REASON_SECONDARY_CONNECTION_FAILED(54),
  /** DCB or FCoE setup failed */
  NM_DEVICE_STATE_REASON_DCB_FCOE_FAILED(55),
  /** teamd control failed */
  NM_DEVICE_STATE_REASON_TEAMD_CONTROL_FAILED(56),
  /** Modem failed or no longer available */
  NM_DEVICE_STATE_REASON_MODEM_FAILED(57),
  /** Modem now ready and available */
  NM_DEVICE_STATE_REASON_MODEM_AVAILABLE(58),
  /** SIM PIN was incorrect */
  NM_DEVICE_STATE_REASON_SIM_PIN_INCORRECT(59),
  /** New connection activation was enqueued */
  NM_DEVICE_STATE_REASON_NEW_ACTIVATION(60),
  /** the device's parent changed */
  NM_DEVICE_STATE_REASON_PARENT_CHANGED(61),
  /** the device parent's management changed */
  NM_DEVICE_STATE_REASON_PARENT_MANAGED_CHANGED(62),
  /** problem communicating with Open vSwitch database */
  NM_DEVICE_STATE_REASON_OVSDB_FAILED(63),
  /** a duplicate IP address was detected */
  NM_DEVICE_STATE_REASON_IP_ADDRESS_DUPLICATE(64),
  /** The selected IP method is not supported */
  NM_DEVICE_STATE_REASON_IP_METHOD_UNSUPPORTED(65),
  /** configuration of SR-IOV parameters failed */
  NM_DEVICE_STATE_REASON_SRIOV_CONFIGURATION_FAILED(66),
  /** The Wi-Fi P2P peer could not be found */
  NM_DEVICE_STATE_REASON_PEER_NOT_FOUND(67);

  private static final Map<UInt32, NMDeviceStateReason> NM_DEVICE_STATE_REASON_MAP;

  static {
    NM_DEVICE_STATE_REASON_MAP =
        Arrays.stream(NMDeviceStateReason.values())
            .collect(Collectors.toMap(NMDeviceStateReason::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMDeviceStateReason(int i) {
    this.value = new UInt32(i);
  }

  public UInt32 getValue() {
    return value;
  }

  public NMDeviceStateReason getNMDeviceStateReason(UInt32 uInt32) {
    return NM_DEVICE_STATE_REASON_MAP.get(uInt32);
  }
}
