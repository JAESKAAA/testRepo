package com.test.prac.domain.supplyBook;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.test.prac.domain.book.Book;
import com.test.prac.domain.supply.Supply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(SupplyBookId.class)
public class SupplyBook {

	@Id
	@ManyToOne
	@JoinColumn(name = "supplyId")
	private Supply supplyId;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "bookId")
	private Book bookId;
}
