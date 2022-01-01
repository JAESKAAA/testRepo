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

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CompanyApiController {

	private final CompanyService companyService;
	private final Validator validator;

	
	@ApiOperation(value="계약업체 리스트", notes ="계약 업체(company) 리스트 출력")
	@GetMapping("/company")
	public CommonResponseDto<?> showCompanyList(){
		List<Company> list = companyService.getCompanyList();
		return new CommonResponseDto<>("계약업체 조회 결과", list);
	}
	
	@ApiOperation(value="특정 업체의 계약 정보 리스트", notes ="업체명에 따른 계약 리스트 조회")
	@GetMapping("/company/contract/{companyName}")
	public CommonResponseDto<?> showCompanyContractList(@PathVariable String companyName){
		List<Company> list = companyService.getCCList(companyName);
		return new CommonResponseDto<>("계약업체 조회 결과", list);
	}
	
	@ApiOperation(value="계약 리스트", notes ="계약(contract) 리스트 조회")
	@GetMapping("/company/contract")
	public CommonResponseDto<?> showContractList(){
		List<Company>list = companyService.getContractList();
		return new CommonResponseDto<>("계약 리스트 조회 결과", list);
	}
	
	@ApiOperation(value="계약 조회", notes ="특정 계약 정보 조회")
	@GetMapping("/company/{companyId}")
	public CommonResponseDto<?> showContract(@PathVariable long companyId) {
		Company company = companyService.getContract(companyId);
		return new CommonResponseDto<>("계약 정보 조회 결과", company);
	}
	
	@ApiOperation(value="계약 등록", notes ="신규 계약 등록")
	@PostMapping("/company")
	public CommonResponseDto<?> enrollContract(@Valid CompanyDto companyDto,
											BindingResult bindingResult){
		
		validator.webValidate(bindingResult);
		
		Company companyEntity = companyService.insertContract(companyDto.toEntity());
		return new CommonResponseDto<>("계약 등록 완료",companyEntity);
	}
	
	@ApiOperation(value="계약 정보 수정", notes ="특정 계약 정보 수정")
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
