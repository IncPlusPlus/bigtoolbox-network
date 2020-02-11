package io.github.incplusplus.bigtoolbox.network.wlan.interop.win;

import io.github.incplusplus.bigtoolbox.io.filesys.TempFile;
import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.bigtoolbox.network.wlan.interop.WLanController;
import io.github.incplusplus.simplewifijava.SimpleWifiJavaEntryPoint;
import io.github.incplusplus.simplewifijava.generated.WiFiApi.ApiHandlePrx;
import io.github.incplusplus.simplewifijava.generated.WiFiApi.WlanInterfacePrx;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * See {@link WLanController}'s JavaDoc for important information
 * @see io.github.incplusplus.bigtoolbox.network.wlan.interop.WLanController
 */
public class WindowsInterop extends WLanController
{
	private Process dotNetApp;
	private BufferedReader stdInput;
	private BufferedWriter stdOutput;
	private BufferedReader stdError;
	private TempFile interopExe;
	private String lastStdInput;
	private String lastStdError;
	com.zeroc.Ice.Communicator communicator;
	private ApiHandlePrx wifi;
	private com.zeroc.Ice.ObjectPrx apiBase;

	public WindowsInterop() throws IOException {
		try {
			interopExe = new TempFile("JavaInterop", "exe", SimpleWifiJavaEntryPoint.class);
		}
		catch (URISyntaxException e) {
			throw new IOException(e);
		}
		dotNetApp = Runtime.getRuntime().exec(interopExe.getAsFile().getPath());
		communicator = com.zeroc.Ice.Util.initialize();
		apiBase = communicator.stringToProxy("SimpleWiFi:default -p 10001");
		wifi = ApiHandlePrx.checkedCast(apiBase);
		
		//These three aren't necessary. However, it could be useful to have them for the future.
		stdInput = new BufferedReader(new InputStreamReader(dotNetApp.getInputStream()), 8 * 1024);
		stdOutput = new BufferedWriter(new OutputStreamWriter(dotNetApp.getOutputStream()), 8 * 1024);
		stdError = new BufferedReader(new InputStreamReader(dotNetApp.getErrorStream()));
	}
	
	public boolean scan() throws IOException
	{
		ensureOpen();
		Arrays.stream(wifi.getWlanInterfaces()).forEach(WlanInterfacePrx::scan);
		return true;
	}

	@Override
	public AccessPoint[] getAccessPoints() throws IOException
	{
		ensureOpen();
		return Arrays.stream(wifi.ListAPsDetail()).map(WindowsAccessPoint::new).toArray(WindowsAccessPoint[]::new);
	}

	protected void conclude() throws IOException
	{
		try
		{
			wifi.terminateApi();
			communicator.close();
			stdInput.close();
			stdError.close();
			stdOutput.close();
			dotNetApp.waitFor();
		}
		catch(InterruptedException e)
		{
			throw new IOException("There was an error running conclude() on this instance. ", e);
		}
	}
}
