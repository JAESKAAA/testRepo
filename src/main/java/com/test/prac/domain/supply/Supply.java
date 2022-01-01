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
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.test.prac.domain.company.Company;
import com.test.prac.domain.supplyBook.SupplyBook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(
		name = "SUPPLY_SEQ_GENERATOR"
	    , sequenceName = "SUPPLY_SEQ"
	    , initialValue = 1
	    , allocationSize = 1
	)
//json응답시 무한 루프 방지를 위한 어노테이션
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "respId")
public class Supply {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUPPLY_SEQ_GENERATOR")
	private Long id; //공급번호
	
	@JoinColumn(name="companyId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Company companyId; //계약번호
	
	@Builder.Default
	@OneToMany(mappedBy="supply", fetch = FetchType.LAZY)
	private List<SupplyBook> supplyBookList = new ArrayList<>();
	
	private LocalDate supplyDate; //공급일자

	//무한참조 문제로 companyId는 toString에서 제외함
	@Override
	public String toString() {
		return "Supply [id=" + id + ", supplyDate=" + supplyDate + "]";
	}
}
