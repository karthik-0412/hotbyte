package com.hexaware.service;

import java.util.List;

import com.hexaware.dto.BookDTO;


public interface BookService {
	List<BookDTO> getAllBooks();
	BookDTO getBookByIsbn(String isbn);
	BookDTO addBook(BookDTO dto);
	BookDTO updateBook(String isbn, BookDTO dto);
	void deleteBook(String isbn);

}
