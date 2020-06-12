package com.ustglobal.librarymanagement.controller;

import java.text.SimpleDateFormat;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ustglobal.librarymanagement.dto.BookInventary;
import com.ustglobal.librarymanagement.dto.BookRegistration;
import com.ustglobal.librarymanagement.dto.BookTransaction;
import com.ustglobal.librarymanagement.dto.LibraryResponse;
import com.ustglobal.librarymanagement.dto.UserBean;
import com.ustglobal.librarymanagement.service.LibraryManagementService;

@CrossOrigin(origins = "*",allowedHeaders = "*",allowCredentials = "true")
@RestController
public class LibraryController {

	@Autowired
	private LibraryManagementService service;

	
	 LibraryResponse response = new LibraryResponse();

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, editor);
	}



	@PostMapping(path="/register",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse registerUser(@RequestBody UserBean userInfo) {
		
		if(service.registerUser(userInfo)) {
			response.setStatusCode(200);
			response.setMessage("Success");
			response.setDescription("User is added successfully");
			return response;
		}else {
			response.setStatusCode(400);
			response.setMessage("Failure");
			response.setDescription("User is not added");
			return response;
		}
	}
	
	
	
	@PostMapping(path="/login",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public LibraryResponse loginUser(@RequestBody UserBean userInfo,HttpServletRequest request){
		
		UserBean user=service.loginUser(userInfo.getUserEmail(),userInfo.getUserPassword());
		if(user!=null) {
			System.out.println("hii");
			request.getSession(true).setAttribute("user", user);
			response.setStatusCode(200);
			response.setMessage("Success");
			response.setDescription("User Logged In");
			response.setUsers(Arrays.asList(user));
			System.out.println(response);
			return response;
		}else {
			response.setStatusCode(400);
			response.setMessage("failure");
			response.setDescription("Invalid email or password");
			return response;
		}
		}
		
	
	
	
	@PostMapping(path="/addBook",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse addBook(@RequestBody BookInventary bookDto,HttpServletRequest request) {
		if(service.addBook(bookDto)) {
			 response.setStatusCode(200);
				response.setMessage("Success");
				response.setDescription("Book Added");
				return response;
			}else {
				response.setStatusCode(400);
				response.setMessage("Failure");
				response.setDescription("Book Not Added");
				return response;
			}
		
	}
	
	
	
	@DeleteMapping(path="/deleteBook/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse deleteBook(@PathVariable("id") int id,HttpServletRequest request) {
		if(service.deleteBook(id)){
			response.setStatusCode(200);
			response.setMessage("Success");
			response.setDescription("book is deleted successfully");
			return response;   
		}else {
			response.setStatusCode(400);
			response.setMessage("Failure");
			response.setDescription("book is not deleted");
			return response;
		}
		
		}
	
	
	
	@GetMapping(path="/getAllBook",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public LibraryResponse getAllBooks(HttpServletRequest request){
		List<BookInventary> books = service.getAllBooks();
		if(books!=null) {
			response.setStatusCode(200);
			response.setMessage("Success");
			response.setDescription("All Books List");
			response.setBooks(books);
			return response;
		}else {
			response.setStatusCode(400);
			response.setMessage("Failure");
			response.setDescription("Error Occured");
			return response;
		}
		
	}//End of getAllBooks
	
	

	
	
	@PostMapping(path = "/allocatebook/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse addBookReg(@PathVariable("userId")int id,@RequestBody BookInventary book,HttpServletRequest request) {
		if (service.requestBook(book,id)) {
			System.out.println(service.deleteBook(book.getBookId()));
			response.setStatusCode(200);
			response.setMessage("success");
			response.setDescription("data  successfully stored..");
			return response;
		} else {
			response.setStatusCode(400);
			response.setMessage("failure");
			response.setDescription("data not successfully stored..");
			return response;
		}
		
		
	}

	@GetMapping(path = "/viewreqs", produces = MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse getAllBookreqs(HttpServletRequest request) {
	List<BookRegistration> books = service.getAllBook();
	if(books!=null) {
		response.setStatusCode(200);
		response.setMessage("success");
		response.setDescription("data  successfully stored..");
		response.setBookReg(books);
		return response;
	} else {
		response.setStatusCode(400);
		response.setMessage("failure");
		response.setDescription("data not successfully stored..");
		return response;
	}
		
	
	}
	
	@PostMapping(path = "/acceptreq", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse addBook(@RequestBody BookRegistration request,HttpServletRequest req) {
		if (service.allocateBook(request)) {
			
			service.removeBook(request.getBookId());
			response.setStatusCode(201);
			response.setMessage("success");
			response.setDescription("data  successfully stored..");
			return response;
		} else {
			response.setStatusCode(400);
			response.setMessage("failure");
			response.setDescription("data not successfully stored..");
			return response;
		}
		
		
	}


	
	@PostMapping(path = "/bookaction/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse searchBook2(@PathVariable("id") int bId,@RequestBody BookRegistration book,HttpServletRequest req) {
		service.addBookAgain1(book);
		if (service.removeBook(bId)) {
			
			response.setStatusCode(201);
			response.setMessage("success");
			response.setDescription("data  successfully deleted..");
			return response;
		} else {
			response.setStatusCode(400);
			response.setMessage("failure");
			response.setDescription("data not successfully deleted..");
			return response;
		}
	}
	
	
	@GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse getAlluserBook(HttpServletRequest req) {
		List<BookTransaction> books = service.getAlluserBooks();
		if(books!=null) {
			response.setStatusCode(201);
			response.setMessage("success");
			response.setDescription("data  successfully stored..");
			response.setBookTrans(books);
			System.out.println(books);
			return response;
		} else {
			response.setStatusCode(400);
			response.setMessage("failure");
			response.setDescription("data not successfully stored..");
			return response;
		}
	}

}// end of registerStudent()
