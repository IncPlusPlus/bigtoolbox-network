package io.github.incplusplus.bigtoolbox.network.interfaces.lin.nm;

import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.Device;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.device.Wired;

public class EthernetAdapter extends GenericDevice implements
    io.github.incplusplus.bigtoolbox.network.interfaces.EthernetAdapter {

  public EthernetAdapter(
      Device device) {
    super(device, Wired.class);
  }
}
