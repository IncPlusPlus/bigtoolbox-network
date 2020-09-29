package io.github.incplusplus.bigtoolbox.network.interfaces.lin.nm;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device.Wireless;
import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.bigtoolbox.network.wlan.AuthRequest;
import java.io.IOException;

public class WiFiAdapter extends GenericDevice implements
    io.github.incplusplus.bigtoolbox.network.interfaces.WiFiAdapter {

  public WiFiAdapter(Device device) {
    super(device, Wireless.class);
  }

  @Override
  public AccessPoint[] getAccessPoints() throws IOException {
    return new AccessPoint[0];
  }

  @Override
  public void requestScan() throws IOException {}

  @Override
  public void connect(AccessPoint accessPoint, AuthRequest request) throws IOException {}
}
