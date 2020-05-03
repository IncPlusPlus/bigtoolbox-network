package io.github.incplusplus.bigtoolbox.network.interfaces.win;

import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.bigtoolbox.network.wlan.AuthRequest;

import java.io.IOException;

public class WiFiAdapter extends io.github.incplusplus.bigtoolbox.network.interfaces.WiFiAdapter{
	@Override
	public AccessPoint[] getAccessPoints() throws IOException {
		return new AccessPoint[0];
	}
	
	@Override
	public void requestScan() throws IOException {
	
	}
	
	@Override
	public void connect(AccessPoint accessPoint, AuthRequest request) throws IOException {
	
	}
	
	@Override
	public String getInterfaceId() {
		return null;
	}
	
	@Override
	public String getInterfaceDescription() {
		return null;
	}
}
