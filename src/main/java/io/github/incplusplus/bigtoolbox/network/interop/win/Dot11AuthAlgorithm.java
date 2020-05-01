package io.github.incplusplus.bigtoolbox.network.interop.win;

import io.github.incplusplus.bigtoolbox.network.interop.InteropFlagSet;

import java.util.Arrays;

public enum Dot11AuthAlgorithm implements InteropFlagSet<Dot11AuthAlgorithm, io.github.incplusplus.bigtoolbox.network.wlan.Dot11AuthAlgorithm> {
	OPEN(io.github.incplusplus.bigtoolbox.network.wlan.Dot11AuthAlgorithm.OPEN, 1),
	SHARED_KEY(io.github.incplusplus.bigtoolbox.network.wlan.Dot11AuthAlgorithm.SHARED_KEY, 2),
	WPA(io.github.incplusplus.bigtoolbox.network.wlan.Dot11AuthAlgorithm.WPA, 3),
	WPA_PSK(io.github.incplusplus.bigtoolbox.network.wlan.Dot11AuthAlgorithm.WPA_PSK, 4),
	WPA_NONE(io.github.incplusplus.bigtoolbox.network.wlan.Dot11AuthAlgorithm.WPA_NONE, 5),
	RSNA(io.github.incplusplus.bigtoolbox.network.wlan.Dot11AuthAlgorithm.RSNA, 6),
	RSNA_PSK(io.github.incplusplus.bigtoolbox.network.wlan.Dot11AuthAlgorithm.RSNA_PSK, 7);
	
	private final io.github.incplusplus.bigtoolbox.network.wlan.Dot11AuthAlgorithm parent;
	private final int hexVal;
	
	Dot11AuthAlgorithm(
			io.github.incplusplus.bigtoolbox.network.wlan.Dot11AuthAlgorithm parent, int hexVal) {
		this.parent = parent;
		this.hexVal = hexVal;
	}
	
	@Override
	public int getHexVal() {
		return hexVal;
	}
	
	@Override
	public Dot11AuthAlgorithm from(int hexVal) {
		return Arrays.stream(Dot11AuthAlgorithm.values()).filter(
				dot11CipherAlgorithm -> dot11CipherAlgorithm.hexVal == hexVal).findFirst().orElseThrow();
	}
	
	public io.github.incplusplus.bigtoolbox.network.wlan.Dot11AuthAlgorithm getParent() {
		return parent;
	}
}
