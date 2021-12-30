package com.test.prac.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.test.prac.handler.exception.CustomException;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> apiException(CustomException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
