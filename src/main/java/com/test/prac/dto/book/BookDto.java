package com.test.prac.dto.book;

import javax.validation.constraints.NotNull;

import com.test.prac.domain.book.Book;
import com.test.prac.domain.book.BookType;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class BookDto {

    @ApiParam(required = true)
	@NotNull
	private String bookTitle;
    
    @ApiParam(required = true)
    @NotNull
	private BookType bookType; //도서구분

    @ApiParam(required = true)
    @NotNull
	private Long quantity;  //수량
	private String supplyPrice; //공급단가
	private String writer; //저자
	private String publicationDate; //발행일
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
