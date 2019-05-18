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
			InputStream is = getClass().getClassLoader().getResource("JavaInterop.exe").openStream();
			OutputStream os = new FileOutputStream("JavaInterop.exe");
			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();

			dotNetApp = Runtime.getRuntime().exec("JavaInterop.exe", null, new File("."));

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
	}
}
