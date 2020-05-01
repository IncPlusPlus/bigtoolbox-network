package io.github.incplusplus.bigtoolbox.network.interop.win;

import io.github.incplusplus.bigtoolbox.network.wlan.AuthRequest;

public class AuthRequestImpl implements AuthRequest {
	private String password;
	private String username;
	private String domain;
	
	public AuthRequestImpl(String password, String username, String domain) {
		this.password = password;
		this.username = username;
		this.domain = domain;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public String getDomain() {
		return domain;
	}
}
