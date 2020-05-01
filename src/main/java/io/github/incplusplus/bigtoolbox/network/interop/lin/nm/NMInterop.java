package io.github.incplusplus.bigtoolbox.network.interop.lin.nm;

import io.github.incplusplus.bigtoolbox.network.Interface;
import io.github.incplusplus.bigtoolbox.network.NetworkController;

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
