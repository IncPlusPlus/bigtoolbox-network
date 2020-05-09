package io.github.incplusplus.bigtoolbox.network.wlan.win;

import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.bigtoolbox.network.wlan.AuthRequest;
import io.github.incplusplus.simplewifijava.generated.ConnectionRequest;
import io.github.incplusplus.simplewifijava.generated.JAccessPoint;
import io.github.incplusplus.simplewifijava.generated.WiFiApiGrpc;

public abstract class WindowsAccessPoint implements AccessPoint {
  private final JAccessPoint remoteAccessPointInstance;
  private final WiFiApiGrpc.WiFiApiBlockingStub api;

  public WindowsAccessPoint(JAccessPoint accessPoint, WiFiApiGrpc.WiFiApiBlockingStub api) {
    this.remoteAccessPointInstance = accessPoint;
    this.api = api;
  }

  //	@Override
  public boolean connect() {
    return api.connectWithAuth(
            ConnectionRequest.newBuilder()
                .setAuthRequest(
                    io.github.incplusplus.simplewifijava.generated.AuthRequest.newBuilder().build())
                .build())
        .getResult();
  }

  //	@Override
  public boolean connect(AuthRequest authRequest) {
    return api.connectWithAuth(
            ConnectionRequest.newBuilder()
                .setAuthRequest(
                    io.github.incplusplus.simplewifijava.generated.AuthRequest.newBuilder()
                        .setPassword(authRequest.getPassword())
                        .setDomain(authRequest.getDomain())
                        .setUsername(authRequest.getUsername())
                        .build())
                .build())
        .getResult();
  }

  //	@Override
  public String getProfileXML() {
    return remoteAccessPointInstance.getProfileXML();
  }

  @Override
  public String getName() {
    return remoteAccessPointInstance.getName();
  }

  @Override
  public int getSignalStrength() {
    return remoteAccessPointInstance.getSignalStrength();
  }

  @Override
  public String getInterfaceName() {
    return remoteAccessPointInstance.getInterfaceName();
  }

  //	@Override
  public boolean isConnectable() {
    return remoteAccessPointInstance.getConnectable();
  }

  //	@Override
  public String getWlanNotConnectableReason() {
    return remoteAccessPointInstance.getWlanNotConnectableReason();
  }

  @Override
  public String getAuthAlgorithm() {
    return remoteAccessPointInstance.getAuthAlgorithm();
  }

  @Override
  public String getCipherAlgorithm() {
    return remoteAccessPointInstance.getCipherAlgorithm();
  }

  @Override
  public String getBssType() {
    return remoteAccessPointInstance.getBssType();
  }

  @Override
  public String toString() {
    return "[SSID:" + getName() + ", Signal:" + getSignalStrength() + "]";
  }
}
