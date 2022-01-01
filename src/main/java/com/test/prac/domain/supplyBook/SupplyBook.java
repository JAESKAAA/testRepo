package com.test.prac.domain.supplyBook;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.prac.domain.book.Book;
import com.test.prac.domain.supply.Supply;

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
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name="supplyBook_uk",
						columnNames = {"supplyId", "bookId"}
				)
		}
)
@SequenceGenerator(
		name = "SB_SEQ_GENERATOR"
	    , sequenceName = "SB_SEQ"
	    , initialValue = 1
	    , allocationSize = 1
	)
public class SupplyBook {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SB_SEQ_GENERATOR")
	private Long id;
	
	@JsonIgnoreProperties({"supplyBookList"})
	@JoinColumn(name = "supplyId")
	@ManyToOne
	private Supply supply;
	
	@JsonIgnoreProperties({"supplyBookList"})
	@JoinColumn(name = "bookId")
	@ManyToOne(fetch = FetchType.EAGER)
	private Book book;

	
	private LocalDate contractDate; //공급 도서 등록날짜

	@PrePersist 
	public void createDate() {
		
		this.contractDate = LocalDate.now();
	}
	
	@Override
	public String toString() {
		return "SupplyBook [id=" + id + "]";
	}
	
	
}
