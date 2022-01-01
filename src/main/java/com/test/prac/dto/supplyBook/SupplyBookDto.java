package com.test.prac.dto.supplyBook;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplyBookDto {

	@NotNull
	private long bookId;
	@NotNull
	private long supplyId;
	
}
