package com.test.prac.domain.supply;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.prac.domain.company.Company;
import com.test.prac.domain.supplyBook.SupplyBook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SequenceGenerator(
		name = "SUPPLY_SEQ_GENERATOR"
	    , sequenceName = "SUPPLY_SEQ"
	    , initialValue = 1
	    , allocationSize = 1
	)
//json응답시 무한 루프 방지를 위한 어노테이션
public class Supply {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUPPLY_SEQ_GENERATOR")
	private Long id; //공급번호
	
	
	@JsonIgnoreProperties({"supplyList"})
	@JoinColumn(name="companyId")
	@ManyToOne()
	private Company contractInfo; //계약번호
	
	
	@Builder.Default
	@OneToMany(mappedBy="supply", fetch = FetchType.LAZY)
	private List<SupplyBook> supplyBookList = new ArrayList<>();
	
	private LocalDate supplyDate; //공급일자

	
	@PrePersist 
	public void createDate() {
		
		this.supplyDate = LocalDate.now();
	}

	
}
