package io.github.incplusplus.bigtoolbox.network.interfaces.lin.nm;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;

public class GenericAdapter extends GenericDevice implements
    io.github.incplusplus.bigtoolbox.network.interfaces.GenericAdapter {

  public GenericAdapter(
      Device device) {
    super(device, Device.class);
  }
}
