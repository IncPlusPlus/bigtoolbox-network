package io.github.incplusplus.bigtoolbox.network.wlan.interop.win;

import io.github.incplusplus.simplewifijava.generated.WiFiApi.AuthRequestStruct;

public class AuthRequestImpl extends AuthRequestStruct {
	
	public AuthRequestImpl(String password, String username, String domain) {
		super(password, username, domain);
	}
}
