package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * NMDeviceModemCapabilities values indicate the generic radio access technology families a modem
 * device supports. For more information on the specific access technologies the device supports use
 * the ModemManager D-Bus API.
 */
public enum NMDeviceModemCapabilities {
  /**
   * modem has no usable capabilities
   */
  NM_DEVICE_MODEM_CAPABILITY_NONE(0),
  /**
   * modem uses the analog wired telephone network and is not a wireless/cellular device
   */
  NM_DEVICE_MODEM_CAPABILITY_POTS(1),
  /**
   * modem supports at least one of CDMA 1xRTT, EVDO revision 0, EVDO revision A, or EVDO revision
   * B
   */
  NM_DEVICE_MODEM_CAPABILITY_CDMA_EVDO(2),
  /**
   * modem supports at least one of GSM, GPRS, EDGE, UMTS, HSDPA, HSUPA, or HSPA+ packet switched
   * data capability
   */
  NM_DEVICE_MODEM_CAPABILITY_GSM_UMTS(4),
  /**
   * modem has LTE data capability
   */
  NM_DEVICE_MODEM_CAPABILITY_LTE(8);

  private static final Map<UInt32, NMDeviceModemCapabilities> NM_DEVICE_MODEM_CAPABILITIES_MAP;

  static {
    NM_DEVICE_MODEM_CAPABILITIES_MAP =
        Arrays.stream(NMDeviceModemCapabilities.values())
            .collect(Collectors.toMap(NMDeviceModemCapabilities::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMDeviceModemCapabilities(int i) {
    this.value = new UInt32(i);
  }

  public UInt32 getValue() {
    return value;
  }
}
