package com.test.prac.domain.book;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bookId", unique = true)
	private long id; //도서번호
	
	@Column(nullable = false)
	private String bookTitle; //도서명
	@Column(nullable = false)
	private String bookType; //도서구분
	@Column(nullable = false)
	private long quantity;  //수량
	private String supplyPrice; //공급단가
	private String writer; //저자
	@Temporal(TemporalType.DATE)
	private Date publicationDate; //발행일
	private String originPrice; //정가
	private Double discountRate; //적용할인율
	
}
