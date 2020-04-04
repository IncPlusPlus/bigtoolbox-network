package io.github.incplusplus.bigtoolbox.network.wlan.interop.lin.nm;

import io.github.incplusplus.bigtoolbox.network.wlan.AccessPoint;
import io.github.incplusplus.bigtoolbox.network.wlan.Interface;
import io.github.incplusplus.bigtoolbox.network.wlan.interop.WLanController;

import java.io.IOException;

public class NMInterop extends WLanController {
	@Override
	public boolean scanAll() throws IOException {
		return false;
	}
	
	@Override
	public AccessPoint[] getAllAccessPoints() throws IOException {
		return new AccessPoint[0];
	}
	
	@Override
	public Interface[] getInterfaces() throws IOException {
		return new Interface[0];
	}
	
	@Override
	protected void conclude() throws IOException {
	
	}
}
