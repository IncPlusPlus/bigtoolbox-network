package io.github.incplusplus.bigtoolbox.network.interfaces.lin.nm;

import static io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.DbusHelpers.NM_BUS_PATH;
import static io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.DbusHelpers.getRemoteObject;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device.Wireless;
import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.bigtoolbox.network.wlan.AuthRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.freedesktop.dbus.DBusPath;

public class WiFiAdapter extends GenericDevice implements
    io.github.incplusplus.bigtoolbox.network.interfaces.WiFiAdapter {

  public WiFiAdapter(Device device) {
    super(device, Wireless.class);
  }

  @Override
  public AccessPoint[] getAccessPoints() throws IOException {
    List<DBusPath> dBusPaths = ((Wireless)device).GetAllAccessPoints();
    List<io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.AccessPoint> accessPointList = new ArrayList<>();
    for(DBusPath path : dBusPaths) {
      accessPointList.add(getRemoteObject(NM_BUS_PATH, path.getPath(), io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.AccessPoint.class));
    }
    List<io.github.incplusplus.bigtoolbox.network.wlan.lin.nm.AccessPoint> accessPoints = new ArrayList<>();
    for (io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.AccessPoint accessPoint : accessPointList) {
      accessPoints.add(new io.github.incplusplus.bigtoolbox.network.wlan.lin.nm.AccessPoint(accessPoint));
    }
    return accessPoints.toArray(new AccessPoint[0]);
  }

  @Override
  public void requestScan() throws IOException {

  }

  @Override
  public void connect(AccessPoint accessPoint, AuthRequest request) throws IOException {}
}
