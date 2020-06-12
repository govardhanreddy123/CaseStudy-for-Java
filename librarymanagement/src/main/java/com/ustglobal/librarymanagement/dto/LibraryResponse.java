package com.ustglobal.librarymanagement.dto;

import java.util.List;



public class LibraryResponse {

	@Override
	public String toString() {
		return "LibraryResponse [statusCode=" + statusCode + ", message=" + message + ", description=" + description
				+ ", users=" + users + ", books=" + books + ", bookReg=" + bookReg + ", bookTrans=" + bookTrans + "]";
	}
	private int statusCode;
	private String message;
	private String description;
	private List<UserBean> users;
	private List<BookInventary> books;
	private List<BookRegistration> bookReg;
	private List<BookTransaction> bookTrans;
	private int fine;
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
	public List<BookRegistration> getBookReg() {
		return bookReg;
	}
	public void setBookReg(List<BookRegistration> bookReg) {
		this.bookReg = bookReg;
	}
	public List<BookTransaction> getBookTrans() {
		return bookTrans;
	}
	public void setBookTrans(List<BookTransaction> bookTrans) {
		this.bookTrans = bookTrans;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<UserBean> getUsers() {
		return users;
	}
	public void setUsers(List<UserBean> users) {
		this.users = users;
	}
	public List<BookInventary> getBooks() {
		return books;
	}
	public void setBooks(List<BookInventary> books) {
		this.books = books;
	}
}
