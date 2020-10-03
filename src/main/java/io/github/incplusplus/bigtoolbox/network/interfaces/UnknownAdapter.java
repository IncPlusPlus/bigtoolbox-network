package io.github.incplusplus.bigtoolbox.network.interfaces;

import io.github.incplusplus.bigtoolbox.network.Interface;
import java.io.IOException;

public class UnknownAdapter implements Interface {

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public String getVendor() throws IOException {
    return null;
  }

  @Override
  public String getMacAddress() {
    return null;
  }

  @Override
  public String[] getIpAddresses() throws IOException {
    return new String[0];
  }
}
