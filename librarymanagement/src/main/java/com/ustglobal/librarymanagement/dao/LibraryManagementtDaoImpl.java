package com.ustglobal.librarymanagement.dao;

import java.util.Calendar;


import java.util.Date;
import java.util.List;

//import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

//import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import com.ustglobal.librarymanagement.dto.BookInventary;
import com.ustglobal.librarymanagement.dto.BookRegistration;
import com.ustglobal.librarymanagement.dto.BookTransaction;
import com.ustglobal.librarymanagement.dto.UserBean;
@Repository
public class LibraryManagementtDaoImpl implements LibraryManagementDao{


	@PersistenceUnit
	EntityManagerFactory factory;
	@Override
	public boolean registerUser(UserBean userInfo){
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		try {


			et.begin();
			em.persist(userInfo);
			et.commit();
			return true;
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
			return false;
		}

	}//end of registerUser

	@Override
	public UserBean loginUser(String userEmail, String password) {
		EntityManager em = factory.createEntityManager();
		String jpql="from UserBean where userEmail=:email and userPassword=:password";
		try {
			Query query=em.createQuery(jpql);
			query.setParameter("email",userEmail);
			query.setParameter("password",password);
			return (UserBean)query.getSingleResult();
		} catch (Exception e) {
			return null;
		}


	}//end of loginUser

	@Override
	public boolean addBook(BookInventary bookDto) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
			et.begin();
			em.merge(bookDto);
			et.commit();
			return true;
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
			return false;
		}

	}//End of addBook

	@Override
	public boolean deleteBook(int id) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		BookInventary bookInfo = em.find(BookInventary.class, id);

		if (bookInfo == null) {
			return false;
		} else {

			try {
				em.remove(bookInfo);
				et.commit();
			} catch (Exception e) {
				et.rollback();
				e.printStackTrace();
			}
			return true;
		}

	}//End of deleteBook

	@SuppressWarnings("unchecked")
	@Override
	public List<BookInventary> getAllBooks() {
		EntityManager em = factory.createEntityManager();
		String queryStr="from BookInventary";
		Query query=em.createQuery(queryStr);
		return query.getResultList();
	}

	@Override
	public boolean requestBook(BookInventary book,int id) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		BookRegistration bookreg = new BookRegistration();
		bookreg.setBookId(book.getBookId());
		bookreg.setRegDate(new Date());
		bookreg.setUserId(id);
		bookreg.setAuthor1(book.getAuthor1());
		bookreg.setAuthor2(book.getAuthor2());
		bookreg.setBookName(book.getBookName());
		bookreg.setPublisher(book.getPublisher());
		bookreg.setYearOfPublication(book.getYearOfPublication());
		try {
			transaction.begin();
			manager.merge(bookreg);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<BookRegistration> getAllBook() {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		String get = "from BookRegistration";
		Query query = manager.createQuery(get);
		try {
			List<BookRegistration> list = query.getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeBook(int bId) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		BookRegistration book = manager.find(BookRegistration.class, bId);
		if (book == null) {
			return false;
		}
		transaction.begin();
		manager.remove(book);
		transaction.commit();
		return true;
	}

	@Override
	public boolean allocateBook(BookRegistration bookAction) {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 14);
		Date returnDate = calendar.getTime();
		long d = returnDate.getTime() - today.getTime();
		int days =(int)(d/(24*60*60*1000));
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		BookTransaction allocateBook = new BookTransaction();
		allocateBook.setIssuedDate(today);
		allocateBook.setReturnDate(returnDate);
		allocateBook.setAuthor1(bookAction.getAuthor1());
		allocateBook.setAuthor2(bookAction.getAuthor2());
		allocateBook.setPublisher(bookAction.getPublisher());
		allocateBook.setYearOfPublication(bookAction.getYearOfPublication());
		allocateBook.setBookId(bookAction.getBookId());
		allocateBook.setBookName(bookAction.getBookName());
		allocateBook.setUserId(bookAction.getUserId());
		try {
			transaction.begin();
			manager.merge(allocateBook);
			transaction.commit();
			System.out.println("book added");
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<BookTransaction> getAlluserBooks() {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
//		System.out.println();
		String get = "from BookTransaction ";
		Query query=manager.createQuery(get);
//		query.setParameter("id",id);
		List<BookTransaction> list = query.getResultList();
		System.out.println(list);
		return list;
	}

	@Override
	public boolean addBookAgain1(BookRegistration book) {
		BookInventary bk = new BookInventary();
		bk.setBookId(book.getBookId());
		bk.setBookName(book.getBookName());
		bk.setAuthor1(book.getAuthor1());
		bk.setAuthor2(book.getAuthor2());
		bk.setPublisher(book.getPublisher());
		bk.setYearOfPublication(book.getYearOfPublication());

		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
			et.begin();
			em.merge(bk);
			et.commit();
			return true;
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
			return false;
		}

	}


}
