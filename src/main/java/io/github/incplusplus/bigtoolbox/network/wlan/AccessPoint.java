package io.github.incplusplus.bigtoolbox.network.wlan;

import io.github.incplusplus.bigtoolbox.network.interfaces.WiFiAdapter;
import java.io.IOException;

/** Document me for the love of god */
public interface AccessPoint {
  void connect(WiFiAdapter adapter, AuthRequest authRequest) throws IOException;

  String getName();

  int getSignalStrength();

  String getInterfaceName();

  String getAuthAlgorithm();

  String getCipherAlgorithm();

  String getBssType();

  default String toStringRepresentation() {
    return "AccessPoint{"
        + "name="
        + getName()
        + ", signalStrength="
        + getSignalStrength()
        + ", interfaceName="
        + getInterfaceName()
        + ", authAlgorithm="
        + getAuthAlgorithm()
        + ", cipherAlgorithm="
        + getCipherAlgorithm()
        + ", bssType="
        + getBssType()
        + "}";
  }
}
