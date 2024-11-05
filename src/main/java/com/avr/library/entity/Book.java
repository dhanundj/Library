package com.avr.library.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
/* @Table(name = "books") */
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@NotBlank(message = "Title is mandatory")
	@Size(max = 100, message = "Title must be less than 100 characters")
	private String title;

	@NotBlank(message = "Author is mandatory")
	@Size(max = 100, message = "Author must be less than 100 characters")
	private String author;

	@NotBlank(message = "Genre is mandatory")
	@Size(max = 50, message = "Genre must be less than 50 characters")
	private String genre;

	@NotNull(message = "Status is mandatory")
	@Enumerated(EnumType.STRING)
	private BookStatus status; // AVAILABLE, BORROWED, OVERDUE

	@Min(value = 0, message = "Available copies cannot be less than 0")
	private int availableCopies;

	private LocalDate dueDate;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(Integer availableCopies) {
		this.availableCopies = availableCopies;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", genre=" + genre
				+ ", availableCopies=" + availableCopies + ", status=" + status + ", dueDate=" + dueDate + "]";
	}

	public Book(Long bookId, String title, String author, String genre, Integer availableCopies, BookStatus status,
			LocalDate dueDate) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.availableCopies = availableCopies;
		this.status = status;
		this.dueDate = dueDate;
	}

	public Book() {
		super();
	}

}
