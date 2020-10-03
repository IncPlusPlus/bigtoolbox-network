package io.github.incplusplus.bigtoolbox.network.interop.win;

import io.github.incplusplus.bigtoolbox.network.Interface;
import io.github.incplusplus.simplewifijava.generated.WlanInterface;
import io.github.incplusplus.simplewifijava.generated.WlanInterfaceApiGrpc;
import java.io.IOException;

public class WindowsInterface implements Interface {
  private final WlanInterface wlanInterface;
  private final WlanInterfaceApiGrpc.WlanInterfaceApiBlockingStub wlanInterfaceApi;

  public WindowsInterface(
      WlanInterface wlanInterface,
      WlanInterfaceApiGrpc.WlanInterfaceApiBlockingStub wlanInterfaceApi) {
    this.wlanInterface = wlanInterface;
    this.wlanInterfaceApi = wlanInterfaceApi;
  }

  @Override
  public String getName() {
    // TODO
    return wlanInterface.getInterfaceDescription();
  }

  @Override
  public String getDescription() {
    return wlanInterface.getInterfaceDescription();
  }

  @Override
  public String getVendor() throws IOException {
    // TODO
    return wlanInterface.getInterfaceDescription();
  }

  @Override
  public String getMacAddress() {
    return wlanInterface.getInterfaceGuid();
  }

  @Override
  public String[] getIpAddresses() {
    // TODO
    return new String[0];
  }
}
