package com.avr.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avr.library.dto.BookBorrowResponse;
import com.avr.library.entity.Book;
import com.avr.library.entity.BookStatus;
import com.avr.library.entity.BorrowingHistory;
import com.avr.library.entity.User;
import com.avr.library.exception.LibraryException;
import com.avr.library.repository.BookRepository;
import com.avr.library.repository.BorrowingHistoryRepository;
import com.avr.library.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BorrowingHistoryRepository borrowingHistoryRepository;

	/**
	 * Search for books by title, author, and category. Returns books sorted by
	 * title.
	 */
	public List<Book> searchBooks(String title, String author, String category) {
		List<Book> books = bookRepository.findAll();

		if (title != null && !title.isEmpty()) {
			books = books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
		}
		if (author != null && !author.isEmpty()) {
			books = books.stream().filter(book -> book.getAuthor().equalsIgnoreCase(author))
					.collect(Collectors.toList());
		}
		if (category != null && !category.isEmpty()) {
			books = books.stream().filter(book -> book.getGenre().equalsIgnoreCase(category))
					.collect(Collectors.toList());
		}

		// Sort books by title
		books.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
		return books;
	}

	/**
	 * Borrow a book for a user. Checks book availability, updates its status, and
	 * records in the borrowing history.
	 */
	@Transactional
	public BookBorrowResponse borrowBook(String userId, String bookId) {
		Optional<User> userOpt = userRepository.findById(Long.parseLong(userId));
		Optional<Book> bookOpt = bookRepository.findById(Long.parseLong(bookId));

		if (userOpt.isEmpty()) {
			throw new LibraryException("User not found");
		}
		if (bookOpt.isEmpty()) {
			throw new LibraryException("Book not found");
		}

		Book book = bookOpt.get();
		
		
		 // Check if the user has already borrowed this book
	    Optional<BorrowingHistory> existingBorrowing = borrowingHistoryRepository.findByUserAndBook(userOpt.get(), book);
	    if (existingBorrowing.isPresent() && existingBorrowing.get().getReturnDate()==null) {
	        throw new LibraryException("You have already borrowed this book.");
	    }
	    
	    if(book.getAvailableCopies()==0) {
	    	throw new LibraryException("All books are borrowed, wait till book till someone return");
	    }

		/*
		 * if (book.getStatus() != BookStatus.AVAILABLE) { throw new
		 * RuntimeException("Book is not available for borrowing"); }
		 */

		// Set book status to BORROWED and decrease available copies
		book.setStatus(BookStatus.BORROWED);
		book.setAvailableCopies(book.getAvailableCopies() - 1);
		bookRepository.save(book);

		// Calculate due date (e.g., 14 days from now)
		LocalDateTime dueDate = LocalDateTime.now().plusDays(14);

		// Create a borrowing history record
		BorrowingHistory history = new BorrowingHistory();
		history.setUser(userOpt.get());
		history.setBook(book);
		history.setBorrowDate(LocalDateTime.now());
		history.setDueDate(dueDate);
		borrowingHistoryRepository.save(history);

		return new BookBorrowResponse("Book borrowed successfully", dueDate);
	}

	/**
	 * Return a borrowed book. Updates book status and borrowing history record with
	 * return date.
	 */
	@Transactional
	public void returnBook(String userId, String bookId) {
		Optional<User> userOpt = userRepository.findById(Long.parseLong(userId));
		Optional<Book> bookOpt = bookRepository.findById(Long.parseLong(bookId));

		if (userOpt.isEmpty()) {
			throw new LibraryException("User not found");
		}
		if (bookOpt.isEmpty()) {
			throw new LibraryException("Book not found");
		}

		Book book = bookOpt.get();

		if (book.getStatus() == BookStatus.AVAILABLE) {
			throw new LibraryException("This book is not currently borrowed");
		}

		// Update book status and increment available copies
		book.setStatus(BookStatus.AVAILABLE);
		book.setAvailableCopies(book.getAvailableCopies() + 1);
		bookRepository.save(book);

		// Update borrowing history record with return date
		BorrowingHistory history = borrowingHistoryRepository
				.findTopByUserAndBookAndReturnDateIsNull(userOpt.get(), book)
				.orElseThrow(() -> new LibraryException("No borrowing record found"));
		history.setReturnDate(LocalDateTime.now());
		borrowingHistoryRepository.save(history);
	}

	public Book addBook(Book book) {

		return bookRepository.save(book); // This will save the book to the database
	}
}
