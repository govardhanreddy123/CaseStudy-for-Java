package com.ustglobal.librarymanagement.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ustglobal.librarymanagement.dao.LibraryManagementDao;
import com.ustglobal.librarymanagement.dto.BookInventary;
import com.ustglobal.librarymanagement.dto.BookRegistration;
import com.ustglobal.librarymanagement.dto.BookTransaction;
import com.ustglobal.librarymanagement.dto.UserBean;
@Service
public class LibraryManagementServiceImpl implements LibraryManagementService {

	@Autowired
	private LibraryManagementDao dao;
	@Override
	public boolean registerUser(UserBean userInfo) {
		return dao.registerUser(userInfo);
	}

	@Override
	public UserBean loginUser(String userEmail, String password) {
		return dao.loginUser(userEmail, password);
	}

	@Override
	public boolean addBook(BookInventary bookDto) {
		return dao.addBook(bookDto);	
	}

	@Override
	public boolean deleteBook(int id) {
		return dao.deleteBook(id);
	}

	@Override
	public List<BookInventary> getAllBooks() {
		return dao.getAllBooks();
	}

	@Override
	public boolean requestBook(BookInventary book,int id) {
		return dao.requestBook(book,id);
	}

	@Override
	public List<BookRegistration> getAllBook() {
		return dao.getAllBook();
	}

	@Override
	public boolean removeBook(int bId) {
		return dao.removeBook(bId);
	}

	@Override
	public boolean allocateBook(BookRegistration bookAction) {
		return dao.allocateBook(bookAction);
	}

	@Override
	public boolean addBookAgain1(BookRegistration book) {
		return dao.addBookAgain1(book);
	}

	@Override
	public List<BookTransaction> getAlluserBooks() {
		return dao.getAlluserBooks();
	}
}
