package com.ustglobal.librarymanagement.service;

import java.util.List;



import com.ustglobal.librarymanagement.dto.BookInventary;
import com.ustglobal.librarymanagement.dto.BookRegistration;
import com.ustglobal.librarymanagement.dto.BookTransaction;
import com.ustglobal.librarymanagement.dto.UserBean;

public interface LibraryManagementService {

	public boolean registerUser(UserBean userInfo);
	public UserBean loginUser(String userEmail,String password);
	public boolean addBook(BookInventary bookDto);
	public boolean deleteBook(int id);
	public List<BookInventary> getAllBooks();
	public boolean removeBook(int bId);
	public boolean requestBook(BookInventary book,int id);
	public List<BookRegistration> getAllBook();
	public boolean allocateBook(BookRegistration request);
	public boolean addBookAgain1(BookRegistration book);
	public List<BookTransaction> getAlluserBooks();
}
