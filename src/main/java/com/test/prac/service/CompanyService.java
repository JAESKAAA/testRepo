package com.test.prac.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.prac.domain.company.Company;
import com.test.prac.domain.company.CompanyRepository;
import com.test.prac.domain.supply.Supply;
import com.test.prac.handler.exception.CustomException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompanyService {

	private final CompanyRepository companyRepository;

	//계약 업체 조회 서비스
	public List<Company> getCompanyList(){
		return companyRepository.findAllCompanyList();
	}
	
	//특정 계약업체의 계약 정보 조회 서비스
	public List<Company> getCCList(String companyName){
		return companyRepository.findByCompanyName(companyName);
	}
	
	//계약 리스트 조회하는 서비스
	public List<Company> getContractList(){
		return companyRepository.findAll();
	}
	
	//특정 계약 정보 조회 서비스
	public Company getContract(long companyId) {
		return companyRepository.findById(companyId).orElseThrow(
				()-> new CustomException("해당 계약번호를 찾을 수 없습니다."));
	}

	//계약 등록 서비스 (Supply 객체를 cacade 옵션으로 자동으로 insert되게도록 구현)
	@Transactional
	public Company insertContract(Company company) {
		//1. 공급 갹체 생성 
		Supply supply = new Supply();
		supply.setSupplyDate(LocalDate.now());
		
		//2. 공급 객체를 company 객체에 주입 (cascade 옵션으로 같이 저장됨)
		company.addSupply(supply);
		
		Company companyEntity = companyRepository.save(company);
		return companyEntity;
	}
	
	//계약 정보 수정 서비스
	@Transactional
	public Company updateContract(long companyId, Company company) {
		Company companyEntity = getContract(companyId);
		
		companyEntity.setLowestRate(company.getLowestRate());
		companyEntity.setCompanyName(company.getCompanyName());
		companyEntity.setContractStatusCode(company.getContractStatusCode());
		
		return companyEntity;
	}
}
