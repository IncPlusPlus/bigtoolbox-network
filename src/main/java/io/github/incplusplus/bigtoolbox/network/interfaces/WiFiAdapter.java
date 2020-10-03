package io.github.incplusplus.bigtoolbox.network.interfaces;

import io.github.incplusplus.bigtoolbox.network.Interface;
import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.bigtoolbox.network.wlan.AuthRequest;
import java.io.IOException;

public interface WiFiAdapter extends Interface {
  AccessPoint[] getAccessPoints() throws IOException;

  void requestScan() throws IOException;

  void connect(AccessPoint accessPoint, AuthRequest request) throws IOException;
}
