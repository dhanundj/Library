package com.avr.library.dto;
public class BookReturnRequest {
    private String userId;
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
		return "BookReturnRequest [userId=" + userId + ", bookId=" + bookId + "]";
	}

    
}
