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

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class SupplyBookApiController {

	private final SupplyBookService supplyBookService;
	
	
	@ApiOperation(value="공급 도서 리스트", notes ="공급 도서 리스트 출력")
	@GetMapping("/supplybook")
	public CommonResponseDto<?> showSBList(){
		List<SupplyBook> list = supplyBookService.getSBList();
		return new CommonResponseDto<>("공급 도서 목록 조회 결과", list);
	}
	
	@ApiOperation(value="공급 번호별 공급 도서 리스트", notes ="공급번호 별 공급 도서 리스트 조회")
	@GetMapping("/supplybook/supply/{supplyId}")
	public CommonResponseDto<?> showSbListForSupply(@PathVariable long supplyId){
		List<SupplyBook> list = supplyBookService.getSBSupplyList(supplyId);
		return new CommonResponseDto<>("공급 번호 별 공급 도서 조회 결과", list);
	}
	
	@ApiOperation(value="도서 번호별 공급 도서 리스트", notes ="도서 번호별 공급 도서 리스트 조회")
	@GetMapping("/supplybook/book/{bookId}")
	public CommonResponseDto<?> showSBListForBook(@PathVariable long bookId){
		List<SupplyBook> list = supplyBookService.getSBBookList(bookId);
		return new CommonResponseDto<>("도서 번호 별 공급 도서 조회 결과", list); 
	}
	
	@ApiOperation(value="공급 도서 조회", notes ="특정 공급 도서 조회")
	@GetMapping("/supplybook/{supplyBookId}")
	public CommonResponseDto<?> showSB(@PathVariable long supplyBookId) {
		SupplyBook SBook = supplyBookService.getSB(supplyBookId);
		return new CommonResponseDto<>("요청하신 공급 도서 조회 결과", SBook); 
	}
	
	@ApiOperation(value="공급 도서 등록", notes ="공급 도서 등록")
	@PostMapping("/supplybook")
	public CommonResponseDto<?> enrollSupplyBook(SupplyBookDto sbBookDto){
		SupplyBook entity = supplyBookService.insertSupplyBook(new SupplyBook(), sbBookDto.getSupplyId(), sbBookDto.getBookId());
		return new CommonResponseDto<>("공급 도서 등록 완료",entity);
	}
	
	@ApiOperation(value="공급 도서 삭제", notes ="공급 도서 정보 삭제")
	@DeleteMapping("/supplybook/{supplyBookId}")
	public CommonResponseDto<?> removeSupplyBook(@PathVariable long supplyBookId){
		supplyBookService.deleteSupplyBook(supplyBookId);
		return new CommonResponseDto<>("공급 도서 삭제 완료", null);
	}
}
