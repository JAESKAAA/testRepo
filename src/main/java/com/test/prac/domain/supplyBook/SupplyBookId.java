package com.test.prac.domain.supplyBook;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


//SupplyBook의 복합키를 @IdClass 활용하기 위해 작성
@AllArgsConstructor
@NoArgsConstructor
public class SupplyBookId implements Serializable {

	private long bookId;
	private long supplyId;
	
	
}
