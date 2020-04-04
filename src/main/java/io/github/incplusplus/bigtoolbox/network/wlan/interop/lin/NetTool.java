package io.github.incplusplus.bigtoolbox.network.wlan.interop.lin;

import java.util.function.Function;

public enum NetTool {
	NetworkManager(new Function<String, Boolean>() {
		@Override
		public Boolean apply(String s) {return true;}
	}),
	wpa_supplicant(new Function<String, Boolean>() {
		@Override
		public Boolean apply(String s) {return false;}
	});
	
	private final Function<String, Boolean> isPresentFunc;
	
	NetTool(Function<String, Boolean> isPresent) {
		this.isPresentFunc = isPresent;
	}
	
	/**
	 * @param s an optional parameter; unused at the moment
	 * @return whether this networking tool is present on the system
	 */
	public boolean isPresent(String s) {
		return isPresentFunc.apply(s);
	}
}
