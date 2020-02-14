package io.github.incplusplus.bigtoolbox.network.wlan.interop.win;

import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.bigtoolbox.network.wlan.AuthRequest;
import io.github.incplusplus.simplewifijava.generated.JAccessPoint;
import io.github.incplusplus.simplewifijava.generated.WiFiApiGrpc;

public class WindowsAccessPoint implements AccessPoint {
	private JAccessPoint remoteAccessPointInstance;
	private WiFiApiGrpc.WiFiApiBlockingStub api;
	
	public WindowsAccessPoint(JAccessPoint accessPoint,
	                          WiFiApiGrpc.WiFiApiBlockingStub api) {
		this.remoteAccessPointInstance = accessPoint;
		this.api = api;
	}
	
	@Override
	public boolean connect() {
		return remoteAccessPointInstance.connect();
	}
	
	@Override
	public boolean connect(AuthRequest authRequest) {
		return remoteAccessPointInstance.connectWithAuth(authRequest);
	}
	
	@Override
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
	
	@Override
	public boolean isConnectable() {
		return remoteAccessPointInstance.isConnectable();
	}
	
	@Override
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
}
