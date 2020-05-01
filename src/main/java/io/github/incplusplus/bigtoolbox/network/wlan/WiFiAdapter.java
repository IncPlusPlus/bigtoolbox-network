package io.github.incplusplus.bigtoolbox.network.wlan;

import io.github.incplusplus.bigtoolbox.network.Interface;

import java.io.IOException;

public abstract class WiFiAdapter implements Interface {
	public abstract AccessPoint[] getAccessPoints() throws IOException;
	
	public abstract void requestScan() throws IOException;
}
