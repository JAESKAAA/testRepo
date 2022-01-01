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
	@Transactional
	public void getBookTest() {
		long bookId = 1;
		Book book = bookRepository.findById(bookId).get();
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
		book.setPublicationDate("2001-01-30");
		book.setOriginPrice("40000");
		book.setDiscountRate(0.1);
		
		bookRepository.save(book);
		
		Book result = bookRepository.findById(book.getId()).get();
		assertThat(result.getId()).isEqualTo(book.getId());
	}
	
	//도서 정보 수정 테스트
	@Test
	@Transactional
	public void updateBookTest() {
		
		//1. 데이터 입력
		Book book = new Book();
		book.setBookTitle("개미");
		book.setBookType(BookType.NOVEL);
		book.setQuantity(100);
		book.setSupplyPrice("35000");
		book.setWriter("베르나르베르베르");
		book.setPublicationDate("2001-01-30");
		book.setOriginPrice("40000");
		book.setDiscountRate(0.1);
		
		bookRepository.save(book);
		
		//2. 비교 대상을 위한 새로운 데이터 생성
		Book originEntity = new Book();
		
		//3. 최초 데이터의 정보를 해당 변수에 저장
		originEntity.setBookTitle(book.getBookTitle());
		
		//4. 최초 데이터 정보를 수정 후 더티 체킹하여 DB 입력될 수 있도록 기대
		book.setBookTitle("데이터수정");
		
		//5. 최초 등록했던 데이터의 id를 매개로 다시 엔티티를 받아옴
		Book result = bookRepository.findById(book.getId()).get();
		
		//6. 최초 데이터와 다를 것을 기대
		assertThat(result.getBookTitle()).isNotEqualTo(originEntity.getBookTitle());
	}
	
}
