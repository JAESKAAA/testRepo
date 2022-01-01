package com.test.prac.domain.book;

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
import javax.persistence.SequenceGenerator;

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
		name = "BOOK_SEQ_GENERATOR"
	    , sequenceName = "BOOK_SEQ"
	    , initialValue = 1
	    , allocationSize = 1
	)
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ_GENERATOR")
	private Long id; //도서번호
	
	@Column(nullable = false)
	private String bookTitle; //도서명
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BookType bookType; //도서구분
	
	@Column(nullable = false)
	private long quantity;  //수량
	private String supplyPrice; //공급단가
	private String writer; //저자
	private LocalDate publicationDate; //발행일
	private String originPrice; //정가
	private Double discountRate; //적용할인율
	
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private List<SupplyBook> supplyBookList;
	
	
	
}
