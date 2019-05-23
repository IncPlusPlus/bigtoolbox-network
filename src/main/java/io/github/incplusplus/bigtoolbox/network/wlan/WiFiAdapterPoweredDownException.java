package io.github.incplusplus.bigtoolbox.network.wlan;

public class WiFiAdapterPoweredDownException extends RuntimeException
{
	public static final String message = "The wireless local area network interface is powered down and doesn't support the requested operation";
	/**
	 * This is thrown when an action is attempted on a wireless network adapter
	 * which is not powered on.
	 */
	public WiFiAdapterPoweredDownException()
	{
		super("The wireless local area network interface is powered down and doesn't support the requested operation.");
	}
}
