package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * General device capability flags.
 */
public enum NMDeviceCapabilities {
  /**
   * device has no special capabilities
   */
  NM_DEVICE_CAP_NONE(0),
  /**
   * NetworkManager supports this device
   */
  NM_DEVICE_CAP_NM_SUPPORTED(1),
  /**
   * this device can indicate carrier status
   */
  NM_DEVICE_CAP_CARRIER_DETECT(2),
  /**
   * this device is a software device
   */
  NM_DEVICE_CAP_IS_SOFTWARE(4),
  /**
   * this device supports single-root I/O virtualization
   */
  NM_DEVICE_CAP_SRIOV(8);

  private static final Map<UInt32, NMDeviceCapabilities> NM_DEVICE_CAPABILITIES_MAP;

  static {
    NM_DEVICE_CAPABILITIES_MAP =
        Arrays.stream(NMDeviceCapabilities.values())
            .collect(Collectors.toMap(NMDeviceCapabilities::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMDeviceCapabilities(int i) {
    this.value = new UInt32(i);
  }

  public static NMDeviceCapabilities getNmDeviceCapability(UInt32 uInt32) {
    return NM_DEVICE_CAPABILITIES_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
