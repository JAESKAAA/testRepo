package com.test.prac.domain.supply;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.test.prac.domain.company.Company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Supply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="supplyId")
	private long id; //공급번호
	
	@JoinColumn(name = "contractId")
	@ManyToOne
	private Company contractNum; //계약번호
	
	private LocalDateTime supplyDate; //공급일자
	
}
