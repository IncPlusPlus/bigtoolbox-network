package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

public enum NMConnectionMultiConnect {
  /**
   * indicates that the per-connection setting is unspecified. In this case, it will fallback to the
   * default value, which is %NM_CONNECTION_MULTI_CONNECT_SINGLE.
   */
  NM_CONNECTION_MULTI_CONNECT_DEFAULT(0),
  /**
   * the connection profile can only be active once at each moment. Activating a profile that is
   * already active, will first deactivate it.
   */
  NM_CONNECTION_MULTI_CONNECT_SINGLE(1),
  /**
   * the profile can be manually activated multiple times on different devices. However, regarding
   * autoconnect, the profile will autoconnect only if it is currently not connected otherwise.
   */
  NM_CONNECTION_MULTI_CONNECT_MANUAL_MULTIPLE(2),
  /**
   * the profile can be manually activated multiple times on different devices. However, regarding
   * autoconnect, the profile will autoconnect only if it is currently not connected otherwise.
   */
  NM_CONNECTION_MULTI_CONNECT_MULTIPLE(3);

  private static final Map<UInt32, NMConnectionMultiConnect> NM_CONNECTION_MULTI_CONNECT_MAP;

  static {
    NM_CONNECTION_MULTI_CONNECT_MAP =
        Arrays.stream(NMConnectionMultiConnect.values())
            .collect(Collectors.toMap(NMConnectionMultiConnect::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMConnectionMultiConnect(int i) {
    this.value = new UInt32(i);
  }

  public UInt32 getValue() {
    return value;
  }

  public NMConnectionMultiConnect getNMConnectionMultiConnect(UInt32 uInt32) {
    return NM_CONNECTION_MULTI_CONNECT_MAP.get(uInt32);
  }
}
