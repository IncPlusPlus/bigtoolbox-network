package io.github.incplusplus.bigtoolbox.network.wlan.interop;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Arrays;

public class DotNetInteropWrapper
{
	private Process dotNetApp;
	private BufferedReader stdInput;
	private BufferedWriter stdOutput;
	private BufferedReader stdError;
	private TempFile interopExe;
	private String lastStdInput;
	private String lastStdError;

	public DotNetInteropWrapper()
	{
		try
		{
			interopExe = new TempFile("JavaInterop","exe");
			dotNetApp = Runtime.getRuntime().exec("cmd /c " + interopExe.getAsFile().getPath());


			stdInput = new BufferedReader(new InputStreamReader(dotNetApp.getInputStream()), 8 * 1024);
			stdOutput = new BufferedWriter(new OutputStreamWriter(dotNetApp.getOutputStream()), 8 * 1024);
			stdError = new BufferedReader(new InputStreamReader(dotNetApp.getErrorStream()));

			lastStdInput = "";
			System.out.println("OUTPUT");
			while ((lastStdInput = stdInput.readLine()) != null)
			{
				//if(lastStdInput.equals())
			}
			stdInput.close();

			System.out.println("ERROR");
			while((lastStdError = stdError.readLine()) != null)
			{
				System.out.println(lastStdError);
			}
			stdError.close();
			dotNetApp.waitFor();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Scan for wireless networks
	 * @return true if successful, false if not
	 */
	public boolean scan()
	{
		return false;
	}

	private enum JavaRequest
	{
		CLOSE_SESSION(0),
		SCAN(1),
		LIST_APS(2),
		LIST_APS_DETAIL(3),
		CONNECT_TO_AP(4),
		DISCONNECT(5);

		private int value;

		JavaRequest(int val)
		{
			this.value = val;
		}

		public int getValue()
		{
			return this.value;
		}
	}

	private enum ResponseToJava
	{
		SESSION_CLOSED(0),
		SESSION_OPENED(1),
		NO_ADAPTERS_FOUND(2),
		NO_WIFI_NETWORKS_FOUND(3),
		SCAN_COMPLETED(4),
		SCAN_FAILED(5),
		CONNECTION_CONFIG_AWAITING_SSID(6),
		CONNECTION_CONFIG_AWAITING_USERNAME(7),
		CONNECTION_CONFIG_AWAITING_PASSWORD(8),
		CONNECTION_CONFIG_AWAITING_DOMAIN(9),
		CONNECTION_CONFIG_COMPLETED(10),
		CONNECTION_COMPLETED(11),
		CONNECTION_FAILED(12),
		DISCONNECT_COMPLETED(13),
		DISCONNECT_FAILED(14),
		WARN_RECEIVED_GARBAGE_INPUT(15),
		WARN_RECEIVED_INCORRECT_COMMAND(16);

		private int value;

		ResponseToJava(int val)
		{
			this.value = val;
		}

		public int getValue()
		{
			return this.value;
		}
	}

	public static void main(String[] args) {
		System.out.println((JavaRequest.CLOSE_SESSION));
	}
}
