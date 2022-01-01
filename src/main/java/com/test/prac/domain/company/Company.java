package com.test.prac.domain.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;

import com.test.prac.domain.supply.Supply;

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
		name = "COMPANY_SEQ_GENERATOR"
	    , sequenceName = "COMPANY_SEQ"
	    , initialValue = 1
	    , allocationSize = 1
	)
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_SEQ_GENERATOR")
	@Column(name = "companyId")
	private Long id; //계약번호
	
	private LocalDate contractDate; //계약일자
	private Double lowestRate; //최저가비율
	
	@Column(nullable = false)
	private String companyName;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusCode contractStatusCode; //계약상태코드
	
	@Builder.Default
	@OneToMany(mappedBy = "contractInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Supply> supplyList = new ArrayList<>();
	
	@PrePersist 
	public void createDate() {
		
		this.contractDate = LocalDate.now();
	}
	
	public void addSupply(Supply supply) {
			this.getSupplyList().add(supply);
			supply.setContractInfo(this);
	}

}
