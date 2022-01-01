package com.test.prac.dto.supplyBook;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplyBookDto {

    @ApiParam(required = true)
	@NotNull
	private long bookId;

    @ApiParam(required = true)
    @NotNull
	private long supplyId;
	
}
