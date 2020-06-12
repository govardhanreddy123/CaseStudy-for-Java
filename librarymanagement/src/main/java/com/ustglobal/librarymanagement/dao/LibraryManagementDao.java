package com.ustglobal.librarymanagement.dao;

import java.util.List;


import com.ustglobal.librarymanagement.dto.BookInventary;
import com.ustglobal.librarymanagement.dto.BookRegistration;
import com.ustglobal.librarymanagement.dto.BookTransaction;
import com.ustglobal.librarymanagement.dto.UserBean;



public interface LibraryManagementDao {

	public boolean registerUser(UserBean userInfo);
	public UserBean loginUser(String userEmail,String password);

	public boolean addBook(BookInventary bookDto);
	public boolean deleteBook(int id);
	public List<BookInventary> getAllBooks();

	public boolean requestBook(BookInventary book,int id);
	public List<BookRegistration> getAllBook();
	public boolean removeBook(int bId);

	public boolean allocateBook(BookRegistration bookAction);
	public List<BookTransaction> getAlluserBooks();
	public boolean addBookAgain1(BookRegistration book);




}
