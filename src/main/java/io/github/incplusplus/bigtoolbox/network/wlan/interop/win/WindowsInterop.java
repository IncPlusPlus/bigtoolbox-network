package io.github.incplusplus.bigtoolbox.network.wlan.interop.win;

import com.google.protobuf.Empty;
import io.github.incplusplus.bigtoolbox.io.filesys.TempFile;
import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.bigtoolbox.network.wlan.interop.WLanController;
import io.github.incplusplus.simplewifijava.SimpleWifiJavaEntryPoint;
import io.github.incplusplus.simplewifijava.generated.WiFiApiGrpc;
import io.github.incplusplus.simplewifijava.generated.WlanInterfaceApiGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.*;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

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
	private final ManagedChannel channel;
	private final WiFiApiGrpc.WiFiApiBlockingStub wifiApi;
	private final WlanInterfaceApiGrpc.WlanInterfaceApiBlockingStub wlanInterfaceApi;

	public WindowsInterop() throws IOException {
		TempFile interopExe;
		try {
			interopExe = new TempFile("JavaInterop", "exe", SimpleWifiJavaEntryPoint.class);
		}
		catch (URISyntaxException e) {
			throw new IOException(e);
		}
		dotNetApp = Runtime.getRuntime().exec(interopExe.getAsFile().getPath());
		this.channel = ManagedChannelBuilder.forAddress("localhost", 50051)
				// Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
				// needing certificates.
				.usePlaintext()
				.build();
		wifiApi = WiFiApiGrpc.newBlockingStub(channel);
		wlanInterfaceApi = WlanInterfaceApiGrpc.newBlockingStub(channel);
		
		//These three aren't necessary. However, it could be useful to have them for the future.
		stdInput = new BufferedReader(new InputStreamReader(dotNetApp.getInputStream()), 8 * 1024);
		stdOutput = new BufferedWriter(new OutputStreamWriter(dotNetApp.getOutputStream()), 8 * 1024);
		stdError = new BufferedReader(new InputStreamReader(dotNetApp.getErrorStream()));
		
		//Make sure the API is actually accessible
		try {
			//noinspection ResultOfMethodCallIgnored
			wifiApi.ensureApiAlive(Empty.getDefaultInstance());
		}
		catch (Exception e) {
			//If the app is dead or there's something of note in stdErr
			if (!dotNetApp.isAlive() || stdError.ready()) {
				//We make an exception encapsulating the content of stdErr
				IOException ioException = new IOException(stdError.lines().collect(Collectors.joining()));
				//And keep the originating error around in case it matters
				ioException.addSuppressed(e);
				throw ioException;
			}
			else {
				//Something else went wrong. Send an exception up the call chain
				throw new IOException(e);
			}
		}
		if (!dotNetApp.isAlive()) {
			throw new IOException();
		}
	}
	
	public boolean scanAll() throws IOException {
		ensureOpen();
		wlanInterfaceApi.getWlanInterfaces(Empty.getDefaultInstance()).getInterfacesList().forEach(
				wlanInterfaceApi::scan);
		return true;
	}
	
	@Override
	public AccessPoint[] getAllAccessPoints() throws IOException {
		ensureOpen();
		return wifiApi.listAll(Empty.getDefaultInstance()).getAccessPointsList().stream().map(
				ap -> new WindowsAccessPoint(ap,
						wifiApi)).toArray(WindowsAccessPoint[]::new);
	}

	protected void conclude() throws IOException
	{
		try
		{
			channel.shutdown();
			stdOutput.write("\n");
			stdOutput.flush();
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
