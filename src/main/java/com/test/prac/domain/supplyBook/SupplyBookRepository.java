package com.test.prac.domain.supplyBook;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyBookRepository extends JpaRepository<SupplyBook, Long>{

	List<SupplyBook> findBySupplyId(long SupplyId);
	List<SupplyBook> findByBookId(long bookId);
}
