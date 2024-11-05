package com.avr.library.dto;

import java.time.LocalDateTime;

public class BorrowingHistoryResponse {
	private String bookId;
	private String title;
	private LocalDateTime borrowDate;
	private LocalDateTime dueDate;
	private LocalDateTime returnDate;

	public BorrowingHistoryResponse(String bookId, String title, LocalDateTime borrowDate, LocalDateTime dueDate,
			LocalDateTime returnDate) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
	}

	public BorrowingHistoryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BorrowingHistoryResponse [bookId=" + bookId + ", title=" + title + ", borrowDate=" + borrowDate
				+ ", dueDate=" + dueDate + ", returnDate=" + returnDate + "]";
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDateTime borrowDate) {
		this.borrowDate = borrowDate;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

}