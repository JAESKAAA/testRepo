package com.test.prac.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.prac.domain.book.Book;
import com.test.prac.dto.CommonResponseDto;
import com.test.prac.dto.book.BookDto;
import com.test.prac.service.BookService;
import com.test.prac.util.Validator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class BookApiContoller {

	private final BookService bookService;
	private final Validator validator;
	
	//도서 내역 조회
	@GetMapping("/book")
	public List<Book> showBookList(){
		return bookService.getBookList();
	}
	
	//특정 도서 조회
	@GetMapping("/book/{bookId}")
	public Book showBook(@PathVariable long bookId) {
		return bookService.getBook(bookId);
	}
	
	//도서 등록
	@PostMapping("/book")
	public CommonResponseDto<?> enrollBook(@Valid BookDto bookDto, BindingResult bindingResult) {
		
		// DTO 데이터 유효성 검사
		validator.webValidate(bindingResult);

		Book bookEntity = bookService.insertBook(bookDto.toEntity());
			return new CommonResponseDto<>("도서 등록 완료",bookEntity);
	}
	
	//도서 정보 수정
	@PutMapping("/book/{bookId}")
	public CommonResponseDto<?> modifyBook(
											@PathVariable long bookId,
											@Valid BookDto bookDto,
											BindingResult bindingResult){
		
		//DTO 데이터 유효성 검사
		validator.webValidate(bindingResult);
		
		Book bookEntity = bookService.updateBook(bookId,bookDto.toEntity());
			return new CommonResponseDto<>("도서 정보 수정 완료", bookEntity);
	}
	
}
