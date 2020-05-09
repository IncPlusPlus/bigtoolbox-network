package io.github.incplusplus.bigtoolbox.network.wlan;

import io.github.incplusplus.bigtoolbox.network.interfaces.WiFiAdapter;
import java.io.IOException;

/** Document me for the love of god */
public interface AccessPoint {
  void connect(WiFiAdapter adapter, AuthRequest authRequest) throws IOException;

  //	String getProfileXML();

  // <editor-fold desc="Getters">
  String getName();

  int getSignalStrength();

  String getInterfaceName();

  //	boolean isConnectable();

  //	String getWlanNotConnectableReason();

  String getAuthAlgorithm();

  String getCipherAlgorithm();

  String getBssType();
  // </editor-fold>
}
