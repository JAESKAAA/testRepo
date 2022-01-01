package com.test.prac.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.test.prac.domain.book.Book;
import com.test.prac.domain.book.BookRepository;
import com.test.prac.domain.book.BookType;

@SpringBootTest
class BookServiceTest {

	@Autowired
	private BookRepository bookRepository;

	
	//도서 리스트 조회 테스트
	@Test
	public void getBookListTest() {
		List<Book> bookList = bookRepository.findAll();
		System.out.println("조회된 도서 건수 ==> "+bookList.size());
	}
	
	//특정 도서 조회 테스트
	@Test
	public void getBookTest() {
		Book book = bookRepository.findById(1L).get();
		System.out.println("조회된 책==> "+book);
	}
	
	//도서 등록 테스트
	@Test
	@Transactional
	public void insertBookTest() {
		
		Book book = new Book();
		book.setBookTitle("개미");
		book.setBookType(BookType.NOVEL);
		book.setQuantity(100);
		book.setSupplyPrice("35000");
		book.setWriter("베르나르베르베르");
		book.setPublicationDate(LocalDate.of(2001, 1, 3));
		book.setOriginPrice("40000");
		book.setDiscountRate(0.1);
		
		bookRepository.save(book);
		
		Book result = bookRepository.findById(book.getId()).get();
		System.out.println(result.toString());
		assertThat(result.getId()).isEqualTo(book.getId());
	}
	
	//도서 정보 수정 테스트
	@Test
	@Transactional
	public void updateBookTest() {
		Book bookEntity = bookRepository.findById(1L).get();
		Book originEntity = new Book();
		
		//타이틀 변경 되었는지 확인 하기 위해, 최초 받아온 bookEntity title 넣어줌
		originEntity.setBookTitle(bookEntity.getBookTitle());
		
		
		System.out.println("오리지날 위에서 찍은거 --> "+originEntity.toString());

		bookEntity.setBookTitle("개미 수정");
		
		Book result = bookRepository.findById(1L).get();
		System.out.println("오리지널:==> "+originEntity.toString());
		System.out.println("업데이트:==> "+result.toString());
		
		assertThat(result.getBookTitle()).isNotEqualTo(originEntity.getBookTitle());
	}
	
}
