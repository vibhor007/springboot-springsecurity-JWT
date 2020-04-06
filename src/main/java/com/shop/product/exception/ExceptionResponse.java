package com.shop.product.exception;
/**
 * 
 * @author VibhorJaisawal
 *
 */
public class ExceptionResponse {
	
	private String errorMessage;
	private Integer errorCode;
	
	
	public ExceptionResponse() {
		
	}
	
	public ExceptionResponse(String errorMessage, Integer errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	



	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the errorCode
	 */
	public Integer getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
