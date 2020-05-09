package io.github.incplusplus.bigtoolbox.network.wlan.lin.nm;

import io.github.incplusplus.bigtoolbox.network.interfaces.WiFiAdapter;
import io.github.incplusplus.bigtoolbox.network.wlan.AuthRequest;
import java.io.IOException;

public class AccessPoint implements io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint {
  @Override
  public void connect(WiFiAdapter adapter, AuthRequest authRequest) throws IOException {}

  @Override
  public String getName() {
    return null;
  }

  @Override
  public int getSignalStrength() {
    return 0;
  }

  @Override
  public String getInterfaceName() {
    return null;
  }

  @Override
  public String getAuthAlgorithm() {
    return null;
  }

  @Override
  public String getCipherAlgorithm() {
    return null;
  }

  @Override
  public String getBssType() {
    return null;
  }
}
