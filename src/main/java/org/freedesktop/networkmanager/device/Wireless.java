package org.freedesktop.networkmanager.device;

import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.Variant;
import org.freedesktop.networkmanager.Device;

@DBusInterfaceName("org.freedesktop.NetworkManager.Device.Wireless")
public interface Wireless extends DBusInterface, Properties, Device {

  List<DBusPath> GetAccessPoints();

  List<DBusPath> GetAllAccessPoints();

  void RequestScan(Map<String, Variant<?>> options);

  class AccessPointAdded extends DBusSignal {

    private final DBusPath accessPoint;

    AccessPointAdded(String _path, DBusPath _accessPoint) throws DBusException {
      super(_path, _accessPoint);
      this.accessPoint = _accessPoint;
    }

    public DBusPath getAccessPoint() {
      return accessPoint;
    }

  }

  class AccessPointRemoved extends DBusSignal {

    private final DBusPath accessPoint;

    AccessPointRemoved(String _path, DBusPath _accessPoint) throws DBusException {
      super(_path, _accessPoint);
      this.accessPoint = _accessPoint;
    }

    public DBusPath getAccessPoint() {
      return accessPoint;
    }

  }
}
