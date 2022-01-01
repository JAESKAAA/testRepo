package com.test.prac.domain.company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long>{

	@Modifying
	@Query(value="SELECT company_name, contract_date, contract_status_code, lowest_rate, min(company_id) AS company_id "
			+ "FROM company "
			+ "GROUP BY company_name", nativeQuery = true)
	List<Company> findAllCompanyList();
	
	List<Company> findByCompanyName(String companyName);
}
