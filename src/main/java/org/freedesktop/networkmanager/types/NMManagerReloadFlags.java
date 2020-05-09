package org.freedesktop.networkmanager.types;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.freedesktop.dbus.types.UInt32;

/**
 * Flags for the manager Reload() call.
 */
public enum NMManagerReloadFlags {
  /**
   * an alias for numeric zero, no flags set. This reloads everything that is supported and is
   * identical to a SIGHUP.
   */
  NM_MANAGER_RELOAD_FLAG_NONE(0),
  /**
   * reload the NetworkManager.conf configuration from disk. Note that this does not include
   * connections, which can be reloaded via Setting's ReloadConnections().
   */
  NM_MANAGER_RELOAD_FLAG_CONF(1),
  /**
   * update DNS configuration, which usually involves writing /etc/resolv.conf anew.
   */
  NM_MANAGER_RELOAD_FLAG_DNS_RC(2),
  /**
   * means to restart the DNS plugin. This is for example useful when using dnsmasq plugin, which
   * uses additional configuration in /etc/NetworkManager/dnsmasq.d. If you edit those files, you
   * can restart the DNS plugin. This action shortly interrupts name resolution.
   */
  NM_MANAGER_RELOAD_FLAG_DNS_FULL(4),
  /**
   * all flags.
   */
  NM_MANAGER_RELOAD_FLAG_ALL(7);

  private static final Map<UInt32, NMManagerReloadFlags> NM_MANAGER_RELOAD_FLAGS_MAP;

  static {
    NM_MANAGER_RELOAD_FLAGS_MAP =
        Arrays.stream(NMManagerReloadFlags.values())
            .collect(Collectors.toMap(NMManagerReloadFlags::getValue, Function.identity()));
  }

  private final UInt32 value;

  NMManagerReloadFlags(int i) {
    this.value = new UInt32(i);
  }

  public static NMManagerReloadFlags getNMManagerReloadFlags(UInt32 uInt32) {
    return NM_MANAGER_RELOAD_FLAGS_MAP.get(uInt32);
  }

  public UInt32 getValue() {
    return value;
  }
}
