package io.github.incplusplus.bigtoolbox.network.wlan.interop.lin.nm;

import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.bigtoolbox.network.wlan.Interface;
import io.github.incplusplus.bigtoolbox.network.wlan.interop.NetworkController;

import java.io.IOException;

public class NMInterop extends NetworkController {
	
	@Override
	public Interface[] getInterfaces() throws IOException {
		return new Interface[0];
	}
	
	@Override
	protected void conclude() throws IOException {
	
	}
}
