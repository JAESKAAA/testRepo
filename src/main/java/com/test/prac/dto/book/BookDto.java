package com.test.prac.dto.book;

import java.util.Date;

import com.test.prac.domain.book.Book;

import lombok.Data;

@Data
public class BookDto {

	private String bookTitle;
	private String bookType; //도서구분
	private long quantity;  //수량
	private String supplyPrice; //공급단가
	private String writer; //저자
	private Date publicationDate; //발행일
	private String originPrice; //정가
	private Double discountRate; //적용할인율

	public Book toEntity() {
		return Book.builder()
				.bookTitle(bookTitle)
				.bookType(bookType)
				.quantity(quantity)
				.supplyPrice(supplyPrice)
				.writer(writer)
				.publicationDate(publicationDate)
				.originPrice(originPrice)
				.discountRate(discountRate)
				.build();
	}
}
