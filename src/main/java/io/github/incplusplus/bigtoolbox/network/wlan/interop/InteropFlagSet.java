package io.github.incplusplus.bigtoolbox.network.wlan.interop;

public interface InteropFlagSet<I extends Enum<?>, S extends Enum<?>> {
	int getHexVal();
	
	I from(int hexVal);
	
	S getParent();
}
