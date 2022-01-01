package com.test.prac.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.prac.domain.supplyBook.SupplyBook;
import com.test.prac.dto.CommonResponseDto;
import com.test.prac.dto.supplyBook.SupplyBookDto;
import com.test.prac.service.SupplyBookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class SupplyBookApiController {

	private final SupplyBookService supplyBookService;
	
	
	//공급 도서 목록 조회
	@GetMapping("/supplybook")
	public List<SupplyBook> showSBList(){
		return supplyBookService.getSBList();
	}
	
	//공급 번호별 공급도서 조회
	@GetMapping("/supplybook/supply/{supplyId}")
	public List<SupplyBook> showSbListForSupply(@PathVariable long supplyId){
		return supplyBookService.getSBSupplyList(supplyId);
	}
	
	//도서 번호별 공급도서 조회
	@GetMapping("/supplybook/book/{bookId}")
	public List<SupplyBook> showSBListForBook(@PathVariable long bookId){
		return supplyBookService.getSBBookList(bookId);
	}
	
	//특정 공급 도서 조회
	@GetMapping("/supplybook/{supplyBookId}")
	public SupplyBook showSB(@PathVariable long supplyBookId) {
		return supplyBookService.getSB(supplyBookId);
	}
	
	//공급 도서 등록 (bookId, supplyId 각각 long타입 으로 받기)
	@PostMapping("/supplybook")
	public CommonResponseDto<?> enrollSupplyBook(SupplyBookDto sbBookDto){
		SupplyBook entity = supplyBookService.insertSupplyBook(new SupplyBook(), sbBookDto.getSupplyId(), sbBookDto.getBookId());
		return new CommonResponseDto<>("공급 도서 등록 완료",entity);
	}
	
	//공급 도서 삭제
	@DeleteMapping("/supplybook/{supplyBookId}")
	public CommonResponseDto<?> removeSupplyBook(@PathVariable long supplyBookId){
		supplyBookService.deleteSupplyBook(supplyBookId);
		return new CommonResponseDto<>("공급 도서 삭제 완료", null);
	}
}
