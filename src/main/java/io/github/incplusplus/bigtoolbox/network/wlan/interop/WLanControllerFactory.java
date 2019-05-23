package io.github.incplusplus.bigtoolbox.network.wlan.interop;

public final class WLanControllerFactory
{
	private WLanController primaryController;
	private int numberInstantiated = 0;

	public static WLanController createWLanController()
	{
		return null;
		//if(SystemUtils.IS_OS_WINDOWS)
		//{
		//	return Windows.getInstance();
		//}
		//else if(SystemUtils.IS_OS_LINUX)
		//{
		//	return Linux.getInstance();
		//}
		//else if(SystemUtils.IS_OS_MAC)
		//{
		//	return Mac.getInstance();
		//}
		//throw new UnsupportedOSException();
	}
}
