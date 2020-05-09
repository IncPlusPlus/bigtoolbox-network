package io.github.incplusplus.bigtoolbox.network.interop.lin.nm;

import io.github.incplusplus.bigtoolbox.network.Interface;
import io.github.incplusplus.bigtoolbox.network.NetworkController;
import java.io.IOException;
import org.freedesktop.NetworkManager;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.networkmanager.types.NMDeviceType;

public class NMInterop extends NetworkController {
  private final DBusConnection dbusConn;
  private static NMInterop activeInstance;

  public NMInterop() throws IOException {
    try {
      NMInterop.activeInstance = this;
      dbusConn = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
    } catch (DBusException e) {
      throw new IOException(e);
    }
  }

  public static NMInterop getActiveInstance() {
    return NMInterop.activeInstance;
  }

  public synchronized DBusConnection getDbusConn() throws IOException {
    if (super.isClosed())
      throw new IOException("NetworkController closed. Create a new instance to use the WiFi API.");
    return this.dbusConn;
  }

  @Override
  public Interface[] getInterfaces() throws IOException {
    try {
      NetworkManager nm =
          getDbusConn()
              .getRemoteObject(
                  "org.freedesktop.NetworkManager",
                  "/org/freedesktop/NetworkManager",
                  NetworkManager.class);
      return nm.GetDevices().stream()
          .map(NMDeviceType::dbusInterfaceToNMDevice)
          .map(NMDeviceType::encapsulateNMDeviceInInterface)
          .toArray(Interface[]::new);
    } catch (DBusException e) {
      throw new IOException(e);
    }
  }

  @Override
  protected void conclude() throws IOException {
    dbusConn.close();
    NMInterop.activeInstance = null;
  }
}
