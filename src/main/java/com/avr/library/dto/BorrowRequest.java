package com.avr.library.dto;

import jakarta.validation.constraints.NotBlank;

public class BorrowRequest {
	@NotBlank
	private String userId;

	@NotBlank
	private String bookId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	@Override
	public String toString() {
		return "BorrowRequest [userId=" + userId + ", bookId=" + bookId + "]";
	}

	
}