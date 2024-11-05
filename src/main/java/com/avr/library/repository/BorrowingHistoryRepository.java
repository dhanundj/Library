package com.avr.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avr.library.entity.Book;
import com.avr.library.entity.BorrowingHistory;
import com.avr.library.entity.User;

import java.util.List;
import java.util.Optional;

public interface BorrowingHistoryRepository extends JpaRepository<BorrowingHistory, Long> {
	List<BorrowingHistory> findByUser(User user);

	Optional<BorrowingHistory> findFirstByBookOrderByBorrowDateDesc(Book book);

	Optional<BorrowingHistory> findFirstByUserAndBookAndReturnDateIsNullOrderByBorrowDateDesc(User user, Book book);

	Optional<BorrowingHistory> findTopByUserAndBookAndReturnDateIsNull(User user, Book book);

	Optional<BorrowingHistory> findByUserAndBook(User user, Book book);
}
