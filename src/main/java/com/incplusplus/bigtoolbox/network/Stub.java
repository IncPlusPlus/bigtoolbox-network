package com.incplusplus.bigtoolbox.network;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stub
{
	public static void main(String[] args)
	{
		final File f = new File(Stub.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		System.out.println(f);

		System.out.println(System.getProperty("java.class.path"));
		//String path = "../"
		String output = "";
		try
		{
			Process p = Runtime.getRuntime().exec("cmd /c ManagedNativeWifi.Demo.exe");
			p.waitFor();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(p.getInputStream())
			);
			String line;
			while((line = reader.readLine()) != null)
			{
				output += line;
			}
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		catch(InterruptedException e2)
		{
			e2.printStackTrace();
		}
		System.out.println(output);
	}
}