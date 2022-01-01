package com.test.prac.dto.company;

import javax.validation.constraints.NotNull;

import com.test.prac.domain.company.Company;
import com.test.prac.domain.company.StatusCode;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class CompanyDto {

	private Double lowestRate;
	
    @ApiParam(required = true)
	@NotNull
	private String companyName;
	
    @ApiParam(required = true)
	@NotNull
	private StatusCode contractStatusCode;
	
	public Company toEntity() {
		return Company.builder()
				.lowestRate(lowestRate)
				.companyName(companyName)
				.contractStatusCode(contractStatusCode)
				.build();
	}
	
}
