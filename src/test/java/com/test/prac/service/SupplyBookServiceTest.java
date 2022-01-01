package com.test.prac.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.test.prac.domain.book.Book;
import com.test.prac.domain.supply.Supply;
import com.test.prac.domain.supplyBook.SupplyBook;
import com.test.prac.domain.supplyBook.SupplyBookRepository;

@SpringBootTest
class SupplyBookServiceTest {

	@Autowired
	private SupplyBookRepository supplyBookRepository;
	
	//공급 도서 리스트 조회 테스트
	@Test
	void getSBListTest() {
		List<SupplyBook> list = supplyBookRepository.findAll();
		System.out.println("조회된 곱급 도서 건수 ==> "+list.size());
	}
	
	//공급번호 별 공급도서 조회 테스트
	@Test
	void getSBSupplyListTest() {
		long supplyId = 1;
		List<SupplyBook> list = supplyBookRepository.findBySupplyId(supplyId);
		System.out.println("조회된 공급번호의 공급도서 건수 ==> "+list.size());
	}
	
	//도서번호 별 공급도서 조회 테스트
	@Test
	void getSBBookListTest() {
		long bookId = 1;
		List<SupplyBook> list = supplyBookRepository.findByBookId(bookId);
		System.out.println("조회된 도서번호의 공급도서 건수 ==>"+list.size());
	}
	
	//특정 공급 도서 조회 테스트
	@Test
	void getSBTest() {
		long supplyBookId = 2;
		SupplyBook result = supplyBookRepository.findById(supplyBookId).get();
		assertThat(result.getId()).isEqualTo(supplyBookId);
	}
	
	//공급 도서 등록 테스트
	@Test
	@Transactional
	void insertSupplyBookTest() {
		long bookId = 1;
		long supplyId = 1;
		Book book = Book.builder().id(bookId).build();
		Supply supply = Supply.builder().id(supplyId).build();
		SupplyBook supplyBook = SupplyBook.builder().book(book).supply(supply).build();
		
		SupplyBook result = supplyBookRepository.save(supplyBook);
		
		assertThat(result.getId()).isEqualTo(supplyBook.getId());
	}
	
	//공급 도서 삭제 서비스
	@Test
	@Transactional
	void deleteSupplyBookTest() {
		long supplyBookId = 2;
		SupplyBook entity = supplyBookRepository.findById(supplyBookId).get();
		
		supplyBookRepository.delete(entity);
		SupplyBook result = supplyBookRepository.findById(supplyBookId).orElse(null);

		assertThat(result).isNull();
	}
	
	

}
