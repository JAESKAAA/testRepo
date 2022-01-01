package com.test.prac.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.test.prac.handler.exception.CustomValidationException;

@Component
public class Validator {

	//반복되는 코드를 줄이기 위한 유효성 검증 메소드
	public void webValidate(BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			//에러를 리스트로 뽑아 하나씩 error 변수에 담아줌
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			//실패시 서버 터지지 않게 예외 처리 및 예외 메시지 전송
				throw new CustomValidationException("유효성 검사 실패",errorMap);
		}
	}
}