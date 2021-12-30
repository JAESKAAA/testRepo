package com.test.prac.service;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.test.prac.domain.book.Book;
import com.test.prac.domain.book.BookRepository;
import com.test.prac.handler.exception.CustomException;

@SpringBootTest
class BookServiceTest {

	@Autowired
	private BookRepository bookRepository;

	
	//도서 리스트 조회 테스트
	@Test
	public void showBookListTest() {
		List<Book> bookList = bookRepository.findAll();
		System.out.println("조회된 도서 건수 ==> "+bookList.size());
	}
	
	//특정 도서 조회 테스트
	@Test
	public void showBookTest() {
		Book book = bookRepository.findById(1L).get();
		System.out.println("조회된 책==> "+book);
	}
	
	//도서 등록 테스트
	@Test
	@Transactional
	public void insertBookTest() {
		Book book = new Book();
		book.setId(1L);
		book.setBookTitle("개미");
		book.setBookType("소설");
		book.setQuantity(100);
		book.setSupplyPrice("35000");
		book.setWriter("베르나르베르베르");
		book.setPublicationDate(new Date(2001, 1,30));
		book.setOriginPrice("40000");
		book.setDiscountRate(0.1);
		
		bookRepository.save(book);
		
		Book result = bookRepository.findById(book.getId()).get();
		
		Assertions.assertEquals(book.getId(),result.getId());
	}
	
	//도서 정보 수정 테스트
	@Test
	@Transactional
	public void updateBookTest() {
		Book bookEntity = bookRepository.findById(1L).get();
		Book originBookEntity = bookEntity;
		System.out.println("오리지날 위에서 찍은거 --> "+originBookEntity.toString());

		bookEntity.setBookTitle("개미 수정");
		bookEntity.setBookType("동화");
		bookEntity.setQuantity(300);
		bookEntity.setSupplyPrice("55000");
		bookEntity.setWriter("베르나르베르베르 수정수정");
		bookEntity.setPublicationDate(new Date(2002, 3, 1));
		bookEntity.setOriginPrice("2000");
		bookEntity.setDiscountRate(0.5);
		
		Book result = bookRepository.findById(1L).get();
		System.out.println("오리지널:==> "+originBookEntity.toString());
		System.out.println("업데이트:==> "+result.toString());
		
		Assertions.assertNotEquals(originBookEntity, bookEntity);
	}
	
}
