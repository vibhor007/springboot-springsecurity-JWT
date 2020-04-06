package com.shop.product.exception;
/**
 * 
 * @author VibhorJaisawal
 *
 */
public class TokenIdNotMatchedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TokenIdNotMatchedException() {
		super();
	}
	
	public TokenIdNotMatchedException(String message) {
		super(message);
	}

}
