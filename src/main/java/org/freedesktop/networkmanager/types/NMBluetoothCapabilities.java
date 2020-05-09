package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/** NMBluetoothCapabilities values indicate the usable capabilities of a Bluetooth device. */
public enum NMBluetoothCapabilities {
  /** device has no usable capabilities */
  NM_BT_CAPABILITY_NONE(0),
  /** device provides Dial-Up Networking capability */
  NM_BT_CAPABILITY_DUN(1),
  /** device provides Network Access Point capability */
  NM_BT_CAPABILITY_NAP(2);

  private static final Map<UInt32, NMBluetoothCapabilities> NM_BLUETOOTH_CAPABILITIES_MAP;

  static {
    NM_BLUETOOTH_CAPABILITIES_MAP =
        Arrays.stream(NMBluetoothCapabilities.values())
            .collect(Collectors.toMap(NMBluetoothCapabilities::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMBluetoothCapabilities(int i) {
    this.value = new UInt32(i);
  }

  public static NMBluetoothCapabilities getNMBluetoothCapabilities(UInt32 uInt32) {
    return NM_BLUETOOTH_CAPABILITIES_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
