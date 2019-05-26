package io.github.incplusplus.bigtoolbox.network.wlan.interop;

public class SingletonUnavailableException extends RuntimeException
{
	/**
	 * This is thrown when proceeding with the desired method call would violate the
	 * singleton pattern of this factory
	 */
	public SingletonUnavailableException()
	{
		super();
	}
}
