package io.github.incplusplus.bigtoolbox.network.wlan.win;

import io.github.incplusplus.bigtoolbox.network.interop.InteropFlagSet;
import java.util.Arrays;

public enum Dot11CipherAlgorithm
    implements
        InteropFlagSet<
            Dot11CipherAlgorithm,
            io.github.incplusplus.bigtoolbox.network.wlan.Dot11CipherAlgorithm> {
  NONE(io.github.incplusplus.bigtoolbox.network.wlan.Dot11CipherAlgorithm.NONE, 0x00),
  WEP40(io.github.incplusplus.bigtoolbox.network.wlan.Dot11CipherAlgorithm.WEP40, 0x01),
  TKIP(io.github.incplusplus.bigtoolbox.network.wlan.Dot11CipherAlgorithm.TKIP, 0x02),
  CCMP(io.github.incplusplus.bigtoolbox.network.wlan.Dot11CipherAlgorithm.CCMP, 0x04),
  WEP104(io.github.incplusplus.bigtoolbox.network.wlan.Dot11CipherAlgorithm.WEP104, 0x05),
  // unused
  //	WPA_USE_GROUP(io.github.incplusplus.bigtoolbox.network.wlan.Dot11CipherAlgorithm.WPA_USE_GROUP,
  // 0x100),
  RSN_USE_GROUP(
      io.github.incplusplus.bigtoolbox.network.wlan.Dot11CipherAlgorithm.RSN_USE_GROUP, 0x100),
  WEP(io.github.incplusplus.bigtoolbox.network.wlan.Dot11CipherAlgorithm.WEP, 0x101);

  private final io.github.incplusplus.bigtoolbox.network.wlan.Dot11CipherAlgorithm parent;
  private final int hexVal;

  Dot11CipherAlgorithm(
      io.github.incplusplus.bigtoolbox.network.wlan.Dot11CipherAlgorithm parent, int hexVal) {
    this.parent = parent;
    this.hexVal = hexVal;
  }

  @Override
  public int getHexVal() {
    return hexVal;
  }

  @Override
  public Dot11CipherAlgorithm from(int hexVal) {
    return Arrays.stream(Dot11CipherAlgorithm.values())
        .filter(dot11CipherAlgorithm -> dot11CipherAlgorithm.hexVal == hexVal)
        .findFirst()
        .orElseThrow();
  }

  @Override
  public io.github.incplusplus.bigtoolbox.network.wlan.Dot11CipherAlgorithm getParent() {
    return parent;
  }
}
