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

	public DotNetInteropWrapper()
	{
		try
		{
			dotNetApp = Runtime.getRuntime().exec("cmd /c JavaInterop.exe", null, new File(this.getClass().getClassLoader().getResource(".").toURI()));

			stdInput = new BufferedReader(new InputStreamReader(dotNetApp.getInputStream()), 8 * 1024);
			stdOutput = new BufferedWriter(new OutputStreamWriter(dotNetApp.getOutputStream()), 8 * 1024);
			stdError = new BufferedReader(new InputStreamReader(dotNetApp.getErrorStream()));

			String line = "";
			System.out.println("OUTPUT");
			String out = "";
			while ((line = stdInput.readLine()) != null)
				out += line + "\n";
			stdInput.close();
			System.out.println(out);

			System.out.println("ERROR");
			while((line = stdError.readLine()) != null)
			{
				System.out.println(line);
				if(line.equals("Q. Quit"))
				{
					stdOutput.write("Q\n");
					stdOutput.flush();
				}
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
		catch(URISyntaxException e)
		{
			e.printStackTrace();
		}


	}
}
