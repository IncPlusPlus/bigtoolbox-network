package io.github.incplusplus.bigtoolbox.network.interfaces;

import io.github.incplusplus.bigtoolbox.network.Interface;
import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.bigtoolbox.network.wlan.AuthRequest;
import java.io.IOException;

public abstract class WiFiAdapter implements Interface {
  public abstract AccessPoint[] getAccessPoints() throws IOException;

  public abstract void requestScan() throws IOException;

  public abstract void connect(AccessPoint accessPoint, AuthRequest request) throws IOException;
}
