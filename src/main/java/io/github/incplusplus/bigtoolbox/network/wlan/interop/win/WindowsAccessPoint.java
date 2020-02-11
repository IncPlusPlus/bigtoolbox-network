package io.github.incplusplus.bigtoolbox.network.wlan.interop.win;

import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.simplewifijava.generated.WiFiApi.JAccessPointPrx;

public class WindowsAccessPoint implements AccessPoint {
	private JAccessPointPrx remoteAccessPointInstance;
	
	public WindowsAccessPoint(JAccessPointPrx accessPoint) {
		this.remoteAccessPointInstance = accessPoint;
	}
	
	@Override
	public boolean connect() {
		return remoteAccessPointInstance.connect();
	}
	
	@Override
	public boolean connect(AuthRequestImpl authRequest) {
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
