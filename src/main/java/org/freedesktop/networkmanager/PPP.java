package org.freedesktop.networkmanager;

import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

/** Helper interface for a PPP plugin */
@DBusInterfaceName("org.freedesktop.NetworkManager.PPP")
public interface PPP extends DBusInterface {

  String NeedSecrets();

  void SetIp4Config(Map<String, Variant<?>> config);

  void SetIp6Config(Map<String, Variant<?>> config);

  void SetState(UInt32 state);

  void SetIfindex(int ifindex);
}
