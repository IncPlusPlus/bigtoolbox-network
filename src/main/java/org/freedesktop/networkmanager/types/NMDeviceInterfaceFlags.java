package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/** Flags for a network interface. */
public enum NMDeviceInterfaceFlags {
  /** an alias for numeric zero, no flags set. */
  NM_DEVICE_INTERFACE_FLAG_NONE(0),
  /**
   * the interface is enabled from the administrative point of view. Corresponds to kernel IFF_UP.
   */
  NM_DEVICE_INTERFACE_FLAG_UP(1),
  /** the physical link is up. Corresponds to kernel IFF_LOWER_UP. */
  NM_DEVICE_INTERFACE_FLAG_LOWER_UP(2),
  /**
   * the interface has carrier. In most cases this is equal to the value of {@link
   * NMDeviceInterfaceFlags#NM_DEVICE_INTERFACE_FLAG_LOWER_UP}. However some devices have a
   * non-standard carrier detection mechanism.
   */
  NM_DEVICE_INTERFACE_FLAG_CARRIER(10000);

  private static final Map<UInt32, NMDeviceInterfaceFlags> NM_DEVICE_INTERFACE_FLAGS_MAP;

  static {
    NM_DEVICE_INTERFACE_FLAGS_MAP =
        Arrays.stream(NMDeviceInterfaceFlags.values())
            .collect(Collectors.toMap(NMDeviceInterfaceFlags::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMDeviceInterfaceFlags(int i) {
    this.value = new UInt32(i);
  }

  public static NMDeviceInterfaceFlags getNMDeviceInterfaceFlags(UInt32 uInt32) {
    return NM_DEVICE_INTERFACE_FLAGS_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
