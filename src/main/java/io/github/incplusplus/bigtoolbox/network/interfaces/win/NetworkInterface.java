package io.github.incplusplus.bigtoolbox.network.interfaces.win;

import io.github.incplusplus.bigtoolbox.network.Interface;
import java.io.IOException;

public class NetworkInterface implements Interface {

  @Override
  public String getName() {
    // TODO
    return null;
  }

  @Override
  public String getDescription() {
    // TODO
    return null;
  }

  @Override
  public String getVendor() throws IOException {
    return null;
  }

  @Override
  public String getMacAddress() {
    // TODO
    return null;
  }

  @Override
  public String[] getIpAddresses() {
    // TODO
    return new String[0];
  }
}
