package io.github.incplusplus.bigtoolbox.network.wlan;

public final class AccessPoint
{
	public final String interfaceName;
	public final String name;
	public final int signalStrength;
	public final boolean connectable;
	public final String wlanNotConnectableReason;
	public final String authAlgorithm;
	public final String cipherAlgorithm;
	public final String bssType;

	protected AccessPoint(String interfaceName, String name, int signalStrength, boolean connectable, String wlanNotConnectableReason, String authAlgorithm, String cipherAlgorithm, String bssType)
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

	public String toString()
	{
		return this.name;
	}
}
