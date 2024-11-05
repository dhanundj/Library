package com.avr.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avr.library.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByTitleContainingAndAuthorContainingAndGenreContainingOrderByTitle(String title, String author,
			String genre);
}
