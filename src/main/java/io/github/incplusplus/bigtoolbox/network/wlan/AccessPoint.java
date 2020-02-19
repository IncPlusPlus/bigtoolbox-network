package io.github.incplusplus.bigtoolbox.network.wlan;

public interface AccessPoint {
	/**
	 * Document me for the love of god
	 */
	boolean connect();
	
	boolean connect(AuthRequest authRequest);
	
	String getProfileXML();
	
	//<editor-fold desc="Getters">
	String getName();
	
	int getSignalStrength();
	
	String getInterfaceName();
	
	boolean isConnectable();
	
	String getWlanNotConnectableReason();
	
	String getAuthAlgorithm();
	
	String getCipherAlgorithm();
	
	String getBssType();
	//</editor-fold>
}
