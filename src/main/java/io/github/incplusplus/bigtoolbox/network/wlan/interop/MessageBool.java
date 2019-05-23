package io.github.incplusplus.bigtoolbox.network.wlan.interop;

public class MessageBool implements java.io.Serializable
{
	public String message;
	public boolean success;

	public MessageBool(boolean success, String message)
	{
		this.success = success;
		this.message = message;
	}

	public boolean successful()
	{
		return success;
	}

	public String getMessage()
	{
		return message;
	}
}
