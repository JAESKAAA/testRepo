package com.test.prac.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.test.prac.domain.company.Company;
import com.test.prac.domain.company.CompanyRepository;
import com.test.prac.domain.company.StatusCode;
import com.test.prac.domain.supply.Supply;

@SpringBootTest
class CompanyServiceTest {

	@Autowired
	private CompanyRepository companyRepository;
	
	//계약 업체 조회 테스트
	@Test
	public void getCompanyListTest() {
		List<Company> companyList = companyRepository.findAllCompanyList();
		System.out.println("조회된 고객사 건수 ==> "+companyList.size());
	}
	
	//특정 계약 업체의 계약 정보 조회 테스트
	@Test
	public void getCCListTest() {
		String companyName = "오비";
		List<Company> companyList = companyRepository.findByCompanyName(companyName);
		System.out.println("조회 된 ["+companyName+ "] 고객사 건수 ==> "+companyList.size());
	}
	
	//계약 리스트 조회 테스트
	@Test
	public void getContractList() {
		List<Company> contractList = companyRepository.findAll();
		System.out.println("조회된 계약 건수 ==> "+contractList.size());
	}
	
	//특정 계약 정보 조회 테스트
	@Test
	public void getContract() {
		Company contract = companyRepository.findById(1L).get();
		System.out.println("조회된 계약 정보 ==> "+contract);
	}
	
	//계약 등록 테스트
	@Test
	@Transactional
	public void insertContract() {
		Company contract = Company.builder()
											.lowestRate(0.5)
											.companyName("오비")
											.contractStatusCode(StatusCode.PROGRESS)
											.build();
		//공급 등록 되는부분
		Supply supply = new Supply();
		contract.addSupply(supply);
		
		companyRepository.save(contract);
		
		Company result = companyRepository.findById(contract.getId()).get();
		System.out.println(result.toString());
		assertThat(result.getId()).isEqualTo(contract.getId());
		assertThat(result.getSupplyList()).isEqualTo(contract.getSupplyList());
	}
	
	//계약 정보 수정 테스트
	@Test
	@Transactional
	public void updateContract() {
		Company contractEntity = companyRepository.findById(1L).get();
		Company originEntity = new Company();
		
		originEntity.setCompanyName(contractEntity.getCompanyName());
		
		System.out.println("최초 받아온 entity의 회사이름: "+originEntity.getCompanyName());
		
		contractEntity.setCompanyName("삼다수");
		
		Company result = companyRepository.findById(contractEntity.getId()).get();
		
		System.out.println("오리지널==> "+originEntity);
		System.out.println("업데이트==> "+result);
		
		assertThat(result.getCompanyName()).isNotEqualTo(originEntity.getCompanyName());
	}
	
}
