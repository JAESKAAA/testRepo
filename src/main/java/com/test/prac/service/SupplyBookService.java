package com.test.prac.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.prac.domain.book.Book;
import com.test.prac.domain.book.BookRepository;
import com.test.prac.domain.supply.Supply;
import com.test.prac.domain.supply.SupplyRepository;
import com.test.prac.domain.supplyBook.SupplyBook;
import com.test.prac.domain.supplyBook.SupplyBookRepository;
import com.test.prac.handler.exception.CustomException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SupplyBookService {

	private final SupplyBookRepository supplyBookRepository;
	private final BookRepository bookRepository;
	private final SupplyRepository supplyRepository;
	
	//공급 도서 목록 조회 서비스
	public List<SupplyBook> getSBList(){
		return supplyBookRepository.findAll();
	}
	
	//공급번호 별 공급도서 조회 서비스
	public List<SupplyBook> getSBSupplyList(long supplyId){
		return supplyBookRepository.findBySupplyId(supplyId);
	}
	
	//도서번호 별 공급도서 조회 서비스
	public List<SupplyBook> getSBBookList(long bookId){
		return supplyBookRepository.findByBookId(bookId);
	}
	
	//특정 공급 도서 조회 서비스
	public SupplyBook getSB(long supplyBookId) {
		return supplyBookRepository.findById(supplyBookId).orElseThrow(
				() -> new CustomException("해당 공급 도서 번호가 존재하지 않습니다."));
	}
	
	//공급 도서 등록 서비스
	@Transactional
	public SupplyBook insertSupplyBook(SupplyBook supplyBook, long supplyId, long bookId) {
		//1. 책정보 호출
		Book book = bookRepository.findById(bookId).orElseThrow(()-> new CustomException("해당 도서 정보를 찾을 수 없습니다."));
		
		//2. 공급정보 호출
		Supply supply = supplyRepository.findById(supplyId).orElseThrow(()-> new CustomException("해당 공급 정보를 찾을 수 없습니다."));
		
		supplyBook.setBook(book);
		supplyBook.setSupply(supply);
		
		SupplyBook supplyBookEntity = supplyBookRepository.save(supplyBook);
		return supplyBookEntity;
	}
	
	//공급 도서 삭제 서비스
	@Transactional
	public void deleteSupplyBook(long supplyBookId) {
		SupplyBook entity = getSB(supplyBookId);
		supplyBookRepository.delete(entity);
	}
}
