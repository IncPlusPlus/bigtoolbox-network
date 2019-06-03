package io.github.incplusplus.bigtoolbox.network.wlan.interop;

import com.google.gson.Gson;
import io.github.incplusplus.bigtoolbox.network.wlan.AvailableAccessPointPack;
import io.github.incplusplus.bigtoolbox.network.wlan.WiFiAdapterPoweredDownException;

import java.io.*;

import static io.github.incplusplus.bigtoolbox.network.wlan.interop.WindowsInterop.JavaRequest.*;
import static io.github.incplusplus.bigtoolbox.network.wlan.interop.WindowsInterop.ResponseToJava.SESSION_CLOSED;

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
	private final boolean DEBUG = false;

	public WindowsInterop()
	{
		try
		{
			interopExe = new TempFile("JavaInterop", "exe");
			dotNetApp = Runtime.getRuntime().exec(interopExe.getAsFile().getPath());


			stdInput = new BufferedReader(new InputStreamReader(dotNetApp.getInputStream()), 8 * 1024);
			stdOutput = new BufferedWriter(new OutputStreamWriter(dotNetApp.getOutputStream()), 8 * 1024);
			stdError = new BufferedReader(new InputStreamReader(dotNetApp.getErrorStream()));

			if(! readln().equals(Integer.toString(ResponseToJava.SESSION_OPENED.getValue())))
			{
				throw new RuntimeException("Started session but response was: " + "\"" + lastStdInput + "\"");
			}
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
		catch(IOException e)
		{
			e.printStackTrace();
		}
		//catch(InterruptedException e)
		//{
		//	e.printStackTrace();
		//}
	}

	/**
	 * Close the communication session with the dotnet app
	 * Warning: the dotnet app won't be able to be relaunched without using the WLanControllerFactory again
	 */
	private void closeSession()
	{
		debugMsg("Closing session!");
		writeln(CLOSE_SESSION);
		debugMsg("Close command sent.");
		debugMsg("Reading response.");
		if(readln().equals(Integer.toString(SESSION_CLOSED.getValue())))
		{
			debugMsg("Successfully closed session.");
		}
		else
		{
			throw new RuntimeException("Failed to close the session. Instead, received: " + lastStdInput);
		}
	}

	public boolean scan() throws IOException
	{
		ensureOpen();
		debugMsg("Running scan!");
		writeln(SCAN);
		debugMsg("Scan executed.");
		debugMsg("Reading and deserializing.");
		MessageBool response = new Gson().fromJson(readln(), MessageBool.class);
		debugMsg("response: success = " + response.successful());
		debugMsg("response message: " + response.getMessage());
		if(! response.successful())
		{
			if(response.getMessage().equals(WiFiAdapterPoweredDownException.message))
			{
				throw new WiFiAdapterPoweredDownException();
			}
			else
			{
				throw new RuntimeException(response.getMessage());
			}
		}
		return response.successful();
	}

	@Override
	public AvailableAccessPointPack getAccessPoints() throws IOException
	{
		ensureOpen();
		writeln(LIST_APS_DETAIL);
		return new Gson().fromJson(readln(), AvailableAccessPointPack.class);
	}

	@Override
	void conclude() throws IOException
	{
		//ensureOpen();
		try
		{
			closeSession();
			stdInput.close();
			stdError.close();
			stdOutput.close();
			dotNetApp.waitFor();
		}
		catch(InterruptedException e)
		{
			debugMsg("There was an error running conclude() on this instance. " +
					"Message follows: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void writeln(JavaRequest jr)
	{
		debugMsg("Writing " + jr.name());

		try
		{
			//For some odd reason, writing gets messed up when
			//using the write method that takes integers :/
			stdOutput.write(jr.getValue() + "");
			stdOutput.newLine();
			stdOutput.flush();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		debugMsg("Finished writing " + jr.name());
	}

	public String readln()
	{
		lastStdInput = "";
		debugMsg("Reading stdIn...");
		try
		{
			lastStdInput = stdInput.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		debugMsg("Read from stdIn: " + lastStdInput);
		return lastStdInput;
	}



	private void debugMsg(String message)
	{
		if(DEBUG)
		{
			StringBuilder out = new StringBuilder(message.length() + 8);
			out.append("[DEBUG]");
			out.append(message);
			System.out.println(out);
		}
	}

	public enum JavaRequest
	{
		CLOSE_SESSION(0),
		SCAN(1),
		RESERVED(2),
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

	enum ResponseToJava
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
}
