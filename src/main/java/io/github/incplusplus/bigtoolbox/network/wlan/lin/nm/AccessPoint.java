package io.github.incplusplus.bigtoolbox.network.wlan.lin.nm;

import static io.github.incplusplus.bigtoolbox.network.interop.lin.dbushelpers.DbusHelpers.getProperty;

import io.github.incplusplus.bigtoolbox.network.interfaces.WiFiAdapter;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.AccessPoint.PropertyNames;
import io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager.types.NM80211ApSecurityFlags;
import io.github.incplusplus.bigtoolbox.network.wlan.AuthRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AccessPoint implements io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint {
  private final String name;
  private final int signalStrength;
  private final String interfaceName;
  private final String authAlgorithm;
  private final String cipherAlgorithm;
  private final String bssType;

  public AccessPoint(
      io.github.incplusplus.bigtoolbox.network.interop.lin.nm.org.freedesktop.networkmanager
              .AccessPoint
          ap)
      throws IOException {
    name = new String(getProperty(ap, PropertyNames.Ssid), StandardCharsets.UTF_8);
    signalStrength = ((((Byte) getProperty(ap, PropertyNames.Strength)).intValue()) / 2) - 100;
    // It's not trivial to go backwards from AP to NIC because this AP may have been picked up by
    // multiple NICs
    interfaceName = "Unknown";
    authAlgorithm =
        NM80211ApSecurityFlags.getNM80211ApSecurityFlags(getProperty(ap, PropertyNames.RsnFlags))
            .toString();
    cipherAlgorithm = "i dunno, m8";
    // TODO
    bssType = "NOT IMPLEMENTED";
  }

  @Override
  public void connect(WiFiAdapter adapter, AuthRequest authRequest) throws IOException {}

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getSignalStrength() {
    return signalStrength;
  }

  @Override
  public String getInterfaceName() {
    return interfaceName;
  }

  @Override
  public String getAuthAlgorithm() {
    return authAlgorithm;
  }

  @Override
  public String getCipherAlgorithm() {
    return cipherAlgorithm;
  }

  @Override
  public String getBssType() {
    return bssType;
  }

  @Override
  public String toString() {
    return toStringRepresentation();
  }
}
