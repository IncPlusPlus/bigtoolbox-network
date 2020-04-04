package io.github.incplusplus.bigtoolbox.network.wlan.interop.lin;

import io.github.incplusplus.bigtoolbox.network.wlan.interop.WLanController;
import io.github.incplusplus.bigtoolbox.network.wlan.interop.lin.nm.NMInterop;

public class LinuxNetToolFinder {
	public static WLanController getControllerByInstalledTool() {
		switch (getNetToolInUse()) {
			case NetworkManager:
				return new NMInterop();
			case wpa_supplicant:
				//TODO: Throw something more sensible
				throw new RuntimeException();
			default:
				//TODO: Throw something more sensible
				throw new RuntimeException();
		}
	}
	
	private static NetTool getNetToolInUse() {
		if (NetTool.NetworkManager.isPresent("")) {
			return NetTool.NetworkManager;
		}
		else if (NetTool.wpa_supplicant.isPresent("")) {
			return NetTool.wpa_supplicant;
		}
		else {
			//TODO: Throw something more sensible
			throw new RuntimeException();
		}
	}
}
