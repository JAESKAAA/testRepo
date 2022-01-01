package com.test.prac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * [공통 응답용 DTO]
 * 
 * Response시 전달될 데이터 통일성과 Entity 노출 최소화를 위한 DTO.
 * Controller 통해 응답 되는 Data는 모두 해당 클래스의 타입으로 전송 됨.
 * 
 * */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponseDto<T> {

	//메시지부분
	private String message;
	
	//Service 로직을 통과한 데이터가 들어가는 변수
	private T data;
}
