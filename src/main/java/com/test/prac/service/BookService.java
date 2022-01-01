package com.test.prac.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.prac.domain.book.Book;
import com.test.prac.domain.book.BookRepository;
import com.test.prac.handler.exception.CustomException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookRepository;
	
	//조회 서비스
	public List<Book> getBookList(){
		return bookRepository.findAll();
	}
	
	//특정 책만 조회하는 서비스
	public Book getBook(long bookId) {
		return bookRepository.findById(bookId).orElseThrow(
				()-> new CustomException("해당 책 정보를 찾을 수 없습니다."));
	}
	
	//책 등록 서비스
	@Transactional
	public Book insertBook(Book book) {
		Book bookEntity = bookRepository.save(book);
		return bookEntity;
	}
	
	//책 정보 수정 서비스
	@Transactional
	public Book updateBook(long bookId, Book book) {
		// 1. 도서 정보 조회
		Book bookEntity = bookRepository.findById(bookId).orElseThrow(()-> new CustomException("수정 원하시는 책 정보를 찾을 수 없습니다."));
		
		bookEntity.setBookTitle(book.getBookTitle());
		bookEntity.setBookType(book.getBookType());
		bookEntity.setQuantity(book.getQuantity());
		bookEntity.setSupplyPrice(book.getSupplyPrice());
		bookEntity.setWriter(book.getWriter());
		bookEntity.setPublicationDate(book.getPublicationDate());
		bookEntity.setOriginPrice(book.getOriginPrice());
		bookEntity.setDiscountRate(book.getDiscountRate());
		
		return bookEntity;
	}
}
