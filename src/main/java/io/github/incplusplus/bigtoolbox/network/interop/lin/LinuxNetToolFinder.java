package io.github.incplusplus.bigtoolbox.network.interop.lin;

import io.github.incplusplus.bigtoolbox.network.NetworkController;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.NMInterop;
import java.io.IOException;

public class LinuxNetToolFinder {
  public static NetworkController getControllerByInstalledTool() throws IOException {
    switch (getNetToolInUse()) {
      case NetworkManager:
        return new NMInterop();
      case wpa_supplicant:
        // TODO: Throw something more sensible
        throw new RuntimeException();
      default:
        // TODO: Throw something more sensible
        throw new RuntimeException();
    }
  }

  private static NetTool getNetToolInUse() {
    if (NetTool.NetworkManager.isPresent("")) {
      return NetTool.NetworkManager;
    } else if (NetTool.wpa_supplicant.isPresent("")) {
      return NetTool.wpa_supplicant;
    } else {
      // TODO: Throw something more sensible
      throw new RuntimeException();
    }
  }
}
