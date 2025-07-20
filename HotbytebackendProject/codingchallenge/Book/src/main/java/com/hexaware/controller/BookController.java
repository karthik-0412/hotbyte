package com.hexaware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.BookDTO;
import com.hexaware.exception.BookNotFoundException;
import com.hexaware.service.BookService;

@RestController
@RequestMapping("api/book")
public class BookController {
	 @Autowired
	    private BookService bookService;

	    @GetMapping
	    public List<BookDTO> getAllBooks() {
	        return bookService.getAllBooks();
	    }

	    @GetMapping("/{isbn}")
	    public BookDTO getBookByIsbn(@PathVariable String isbn){
	        return bookService.getBookByIsbn(isbn);
	    }

	    @PostMapping
	    public BookDTO addBook(@RequestBody BookDTO bookDTO) {
	        return bookService.addBook(bookDTO);
	    }

	    @PutMapping("/{isbn}")
	    public BookDTO updateBook(@PathVariable String isbn, @RequestBody BookDTO bookDTO) {
	        return bookService.updateBook(isbn, bookDTO);
	    }

	    @DeleteMapping("/{isbn}")
	    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
	        bookService.deleteBook(isbn);
	        return ResponseEntity.ok("Book with ISBN " + isbn + " deleted successfully.");
	    }

}
