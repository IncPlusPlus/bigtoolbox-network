package io.github.incplusplus.bigtoolbox.network;

import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a network interface. This could be a physical network adapter but there are no
 * guarantees that an Interface doesn't represent a virtual interface.
 */
public interface Interface {
  String getName() throws IOException;

  String getDescription() throws IOException;

  String getVendor() throws IOException;

  String getMacAddress() throws IOException;

  String[] getIpAddresses() throws IOException;

  default String toStringRepresentation() throws IOException {
    return "Interface{"
        + "name="
        + getName()
        + ", description="
        + getDescription()
        + ", vendor="
        + getVendor()
        + ", MAC address="
        + getMacAddress()
        + ", IP address="
        + Arrays.toString(getIpAddresses())
        + "}";
  }
}
