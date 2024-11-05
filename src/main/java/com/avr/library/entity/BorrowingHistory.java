package com.avr.library.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
/* @Table(name = "borrowing_history") */
public class BorrowingHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "User is mandatory")
	@ManyToOne
	private User user;

	@NotNull(message = "Book is mandatory")
	@ManyToOne
	private Book book;

	@NotNull(message = "Borrow date is mandatory")
	private LocalDateTime borrowDate;

	@NotNull(message = "Due date is mandatory")
	private LocalDateTime dueDate;

	private LocalDateTime returnDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
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

	@Override
	public String toString() {
		return "BorrowingHistory [id=" + id + ", user=" + user + ", book=" + book + ", borrowDate=" + borrowDate
				+ ", dueDate=" + dueDate + ", returnDate=" + returnDate + "]";
	}

	public BorrowingHistory(Long id, @NotNull(message = "User is mandatory") User user,
			@NotNull(message = "Book is mandatory") Book book,
			@NotNull(message = "Borrow date is mandatory") LocalDateTime borrowDate,
			@NotNull(message = "Due date is mandatory") LocalDateTime dueDate, LocalDateTime returnDate) {
		super();
		this.id = id;
		this.user = user;
		this.book = book;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
	}

	public BorrowingHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
