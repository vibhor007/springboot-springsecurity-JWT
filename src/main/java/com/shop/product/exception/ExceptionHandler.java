package com.shop.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ExceptionResponse> dataNotFound(final DataNotFoundException exception){
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		return new ResponseEntity<ExceptionResponse>(error,HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(TokenIdNotMatchedException.class)
	public ResponseEntity<ExceptionResponse> tokenIdNotFound(final TokenIdNotMatchedException exception){
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		return new ResponseEntity<ExceptionResponse>(error,HttpStatus.UNAUTHORIZED); 
	}
}
