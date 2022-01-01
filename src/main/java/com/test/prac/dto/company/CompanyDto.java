package com.test.prac.dto.company;

import javax.validation.constraints.NotNull;

import com.test.prac.domain.company.Company;
import com.test.prac.domain.company.StatusCode;

import lombok.Data;

@Data
public class CompanyDto {

	private Double lowestRate;
	
	@NotNull
	private String companyName;
	
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
