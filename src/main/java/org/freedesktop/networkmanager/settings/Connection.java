package org.freedesktop.networkmanager.settings;

import java.util.Map;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

public interface Connection extends DBusInterface {

  void Update(Map<String, Map<String, Variant<?>>> properties);

  void UpdateUnsaved(Map<String, Map<String, Variant<?>>> properties);

  void Delete();

  Map<String, Map<String, Variant<?>>> GetSettings();

  Map<String, Map<String, Variant<?>>> GetSecrets(String settingName);

  void ClearSecrets();

  void Save();

  Map<String, Variant<?>> Update2(
      Map<String, Map<String, Variant<?>>> settings, UInt32 flags, Map<String, Variant<?>> args);
}
