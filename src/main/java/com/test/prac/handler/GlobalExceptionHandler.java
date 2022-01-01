package com.test.prac.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.test.prac.handler.exception.CustomException;
import com.test.prac.handler.exception.CustomValidationException;

/*
 * [글로벌 예외 처리 핸들러]
 * 
 * */

@ControllerAdvice
public class GlobalExceptionHandler {

	
	//대부분의 예외 발생시 호출됨
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> apiException(CustomException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	//유효성검증 실패시 호출
	@ExceptionHandler(CustomValidationException.class)
	public ResponseEntity<?> validationException(CustomValidationException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
