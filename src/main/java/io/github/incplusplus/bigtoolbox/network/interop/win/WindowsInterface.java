package io.github.incplusplus.bigtoolbox.network.interop.win;

import io.github.incplusplus.bigtoolbox.network.Interface;
import io.github.incplusplus.simplewifijava.generated.WlanInterface;
import io.github.incplusplus.simplewifijava.generated.WlanInterfaceApiGrpc;

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
  public String getInterfaceId() {
    return wlanInterface.getInterfaceGuid();
  }

  @Override
  public String getInterfaceDescription() {
    return wlanInterface.getInterfaceDescription();
  }
}
