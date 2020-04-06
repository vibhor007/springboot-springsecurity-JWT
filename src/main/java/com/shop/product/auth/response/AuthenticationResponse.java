package com.shop.product.auth.response;
/**
 * 
 * @author VibhorJaisawal
 *
 */
public class AuthenticationResponse {
	
	private String jwtToken;

	public AuthenticationResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	
	

}
