package com.avr.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avr.library.dto.BookBorrowRequest;
import com.avr.library.dto.BookBorrowResponse;
import com.avr.library.dto.BookReturnRequest;
import com.avr.library.dto.BorrowingHistoryResponse;
import com.avr.library.dto.ReturnResponse;
import com.avr.library.entity.Book;
import com.avr.library.service.BookService;
import com.avr.library.service.UserService;

@RestController
@RequestMapping("/api")
public class LibraryController {

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	/**
	 * Endpoint to search for books based on title, author, or category.
	 * 
	 * @param title    (optional) title of the book.
	 * @param author   (optional) author of the book.
	 * @param category (optional) category/genre of the book.
	 * @return list of books that match the search criteria.
	 */
	@GetMapping("/books/search")
	public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String title,
			@RequestParam(required = false) String author, @RequestParam(required = false) String category) {

		List<Book> books = bookService.searchBooks(title, author, category);
		return ResponseEntity.ok(books);
	}

	@PostMapping("/books/add")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book addedBook = bookService.addBook(book);
		return ResponseEntity.ok(addedBook);
	}

	/**
	 * Endpoint to borrow a book for a user.
	 * 
	 * @param borrowRequest contains userId and bookId.
	 * @return success message and due date of the book.
	 */
	@PutMapping("/borrow")
	public ResponseEntity<BookBorrowResponse> borrowBook(@RequestBody BookBorrowRequest borrowRequest) {
		BookBorrowResponse response = bookService.borrowBook(borrowRequest.getUserId(), borrowRequest.getBookId());
		return ResponseEntity.ok(response);
	}

	/**
	 * Endpoint to return a borrowed book for a user.
	 * 
	 * @param returnRequest contains userId and bookId.
	 * @return success message.
	 */
	@PostMapping("/return")
	public ResponseEntity<ReturnResponse> returnBook(@RequestBody BookReturnRequest returnRequest) {
		try {
			bookService.returnBook(returnRequest.getUserId(), returnRequest.getBookId());
			return ResponseEntity.ok(new ReturnResponse("Book returned successfully."));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(new ReturnResponse(e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(new ReturnResponse("An error occurred."));
		}
	}

	/**
	 * Endpoint to get the borrowing history of a user.
	 * 
	 * @param userId the ID of the user.
	 * @return list of borrowed history records for the user.
	 */
	@GetMapping("/users/{userId}/borrowing-history")
	public ResponseEntity<List<BorrowingHistoryResponse>> getBorrowingHistory(@PathVariable String userId) {
		List<BorrowingHistoryResponse> history = userService.getBorrowingHistory(Long.parseLong(userId));
		return ResponseEntity.ok(history);
	}

}
