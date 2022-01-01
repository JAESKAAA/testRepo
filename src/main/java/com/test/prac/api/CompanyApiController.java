package com.test.prac.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.prac.domain.company.Company;
import com.test.prac.dto.CommonResponseDto;
import com.test.prac.dto.company.CompanyDto;
import com.test.prac.service.CompanyService;
import com.test.prac.util.Validator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CompanyApiController {

	private final CompanyService companyService;
	private final Validator validator;

	
	//계약업체 목록 조회
	@GetMapping("/company")
	public List<Company> showCompanyList(){
		return companyService.getCompanyList();
	}
	
	//특정 계약업체의 계약 정보 목록 조회
	@GetMapping("/company/contract/{companyName}")
	public List<Company> showCompanyContractList(@PathVariable String companyName){
		return companyService.getCCList(companyName);
	}
	
	//계약 리스트 조회
	@GetMapping("/company/contract")
	public List<Company> showContractList(){
		return companyService.getContractList();
	}
	
	//특정 계약 정보 조회
	@GetMapping("/company/{companyId}")
	public Company showContract(@PathVariable long companyId) {
		return companyService.getContract(companyId);
	}
	
	//계약 등록
	@PostMapping("/company")
	public CommonResponseDto<?> enrollContract(@Valid CompanyDto companyDto,
											BindingResult bindingResult){
		
		validator.webValidate(bindingResult);
		
		Company companyEntity = companyService.insertContract(companyDto.toEntity());
		return new CommonResponseDto<>("계약 등록 완료",companyEntity);
	}
	
	//계약 정보 수정
	@PutMapping("/company/{companyId}")
	public CommonResponseDto<?> modifyContract(
												@PathVariable long companyId,
												@Valid CompanyDto companyDto,
												BindingResult bindingResult){
		
		validator.webValidate(bindingResult);
		Company companyEntity = companyService.updateContract(companyId, companyDto.toEntity());
		return new CommonResponseDto<>("계약 정보 수정 완료",companyEntity);
	}
}
