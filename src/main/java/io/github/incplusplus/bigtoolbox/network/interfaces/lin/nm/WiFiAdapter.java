package io.github.incplusplus.bigtoolbox.network.interfaces.lin.nm;

import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.bigtoolbox.network.wlan.AuthRequest;
import org.freedesktop.networkmanager.Device;
import org.freedesktop.networkmanager.device.Wireless;

import java.io.IOException;

public class WiFiAdapter extends io.github.incplusplus.bigtoolbox.network.interfaces.WiFiAdapter {
	private final Wireless device;
	
	public WiFiAdapter(Device device) {
		if (device instanceof Wireless) {
			this.device = (Wireless) device;
		}
		else {
			throw new RuntimeException(
					"The class '" + WiFiAdapter.class.getName() +
							"' may only be constructed using a 'device' of type '" + Wireless.class.getName() + "'");
		}
	}
	
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
