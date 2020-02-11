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

	public WindowsInterop()
	{
		try {
			interopExe = new TempFile("JavaInterop", "exe", SimpleWifiJavaEntryPoint.class);
			dotNetApp = Runtime.getRuntime().exec(interopExe.getAsFile().getPath());
			communicator = com.zeroc.Ice.Util.initialize();
			apiBase = communicator.stringToProxy("SimpleWiFi:default -p 10001");
			wifi = ApiHandlePrx.checkedCast(apiBase);
			
			//These three aren't necessary. However, it could be useful to have them for the future.
			stdInput = new BufferedReader(new InputStreamReader(dotNetApp.getInputStream()), 8 * 1024);
			stdOutput = new BufferedWriter(new OutputStreamWriter(dotNetApp.getOutputStream()), 8 * 1024);
			stdError = new BufferedReader(new InputStreamReader(dotNetApp.getErrorStream()));
			
			//			if(! readln().equals(Integer.toString(ResponseToJava.SESSION_OPENED.getValue())))
			//			{
			//				throw new RuntimeException("Started session but response was: " + "\"" + lastStdInput + "\"");
			//			}
			//	System.out.println("OUTPUT:");
			//	while((lastStdInput = stdInput.readLine()) != null)
			//	{
			//		debugMsg("Received '" + lastStdInput + "'");
			//		if(lastStdInput.equals(Integer.toString(ResponseToJava.SESSION_OPENED.getValue())))
			//		{
			//			debugMsg("Writing '" + CLOSE_SESSION.getValue() + "'");
			//			writeln(CLOSE_SESSION);
			//		}
			//		try
			//		{
			//			debugMsg("Exit value " + dotNetApp.exitValue());
			//		}
			//		catch(IllegalThreadStateException e)
			//		{
			//			System.out.println(e.getMessage());
			//		}
			//	}
			//	stdInput.close();
			//
			//	System.out.println("ERRORS:");
			//	while((lastStdError = stdError.readLine()) != null)
			//	{
			//		System.out.println(lastStdError);
			//	}
			//	stdError.close();
			//	dotNetApp.waitFor();
		}
		catch (URISyntaxException | IOException e) {
			//TODO: Make a proper `throws` chain
			e.printStackTrace();
		}
	}
	
	public boolean scan() throws IOException
	{
		ensureOpen();
		Arrays.stream(wifi.getWlanInterfaces()).forEach(WlanInterfacePrx::scan);
		return true;
//		MessageBool response = new Gson().fromJson(readln(), MessageBool.class);
//		debugMsg("response: success = " + response.successful());
//		debugMsg("response message: " + response.getMessage());
//		if(! response.successful())
//		{
//			if(response.getMessage().equals(WiFiAdapterPoweredDownException.message))
//			{
//				throw new WiFiAdapterPoweredDownException();
//			}
//			else
//			{
//				throw new RuntimeException(response.getMessage());
//			}
//		}
//		return response.successful();
	}

	@Override
	public AccessPoint[] getAccessPoints() throws IOException
	{
		ensureOpen();
		return Arrays.stream(wifi.ListAPsDetail()).map(WindowsAccessPoint::new).toArray(WindowsAccessPoint[]::new);
	}

	protected void conclude() throws IOException
	{
		//ensureOpen();
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
