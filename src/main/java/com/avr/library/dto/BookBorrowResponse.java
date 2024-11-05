package com.avr.library.dto;

import java.time.LocalDateTime;

public class BookBorrowResponse {
    private String message;
    private LocalDateTime dueDate;

    public BookBorrowResponse() {
    }

    public BookBorrowResponse(String message, LocalDateTime dueDate) {
        this.message = message;
        this.dueDate = dueDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
