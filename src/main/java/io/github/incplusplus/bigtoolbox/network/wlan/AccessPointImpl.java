package io.github.incplusplus.bigtoolbox.network.wlan;

import io.github.incplusplus.bigtoolbox.network.wlan.interop.win.AuthRequestImpl;
import org.apache.commons.lang3.NotImplementedException;

public final class AccessPointImpl implements AccessPoint {
	public final String interfaceName;
	public final String name;
	public final int signalStrength;
	public final boolean connectable;
	public final String wlanNotConnectableReason;
	public final String authAlgorithm;
	public final String cipherAlgorithm;
	public final String bssType;

	protected AccessPointImpl(String interfaceName, String name, int signalStrength, boolean connectable, String wlanNotConnectableReason, String authAlgorithm, String cipherAlgorithm, String bssType)
	{
		this.interfaceName = interfaceName;
		this.name = name;
		this.signalStrength = signalStrength;
		this.connectable = connectable;
		this.wlanNotConnectableReason = wlanNotConnectableReason;
		this.authAlgorithm = authAlgorithm;
		this.cipherAlgorithm = cipherAlgorithm;
		this.bssType = bssType;
	}

	@Override
	public String toString()
	{
		return this.name;
	}
	
	@Override
	public String getInterfaceName() {
		return interfaceName;
	}
	
	@Override
	public boolean connect() {
		throw new NotImplementedException("I didn't make this yet and probably will delete this class anyways!");
	}
	
	@Override
	public boolean connect(AuthRequest authRequest) {
		return false;
	}
	
	@Override
	public String getProfileXML() {
		return null;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getSignalStrength() {
		return signalStrength;
	}
	
	@Override
	public boolean isConnectable() {
		return connectable;
	}
	
	@Override
	public String getWlanNotConnectableReason() {
		return wlanNotConnectableReason;
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
}
