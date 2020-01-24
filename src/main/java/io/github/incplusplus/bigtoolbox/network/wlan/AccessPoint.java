package io.github.incplusplus.bigtoolbox.network.wlan;

public interface AccessPoint {
	String getName();
	
	int getSignalStrength();
	
	String getInterfaceName();
	
	boolean isConnectable();
	
	String getWlanNotConnectableReason();
	
	String getAuthAlgorithm();
	
	String getCipherAlgorithm();
	
	String getBssType();
}
