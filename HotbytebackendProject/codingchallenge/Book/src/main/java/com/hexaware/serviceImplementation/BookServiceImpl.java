package com.hexaware.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.BookDTO;
import com.hexaware.entity.Book;
import com.hexaware.exception.BookNotFoundException;
import com.hexaware.mapper.BookMapper;
import com.hexaware.repository.BookRepository;
import com.hexaware.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::bookToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookByIsbn(String isbn){
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException("Book not found with ISBN: " + isbn));

        return bookMapper.bookToBookDto(book);
    }

    @Override
    public BookDTO addBook(BookDTO dto) {
        Book book = bookMapper.bookDtoToBook(dto);
        return bookMapper.bookToBookDto(bookRepository.save(book));
    }

    @Override
    public BookDTO updateBook(String isbn, BookDTO dto) {
        Book existingBook = bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ISBN: " + isbn));

        existingBook.setTitle(dto.getTitle());
        existingBook.setAuthor(dto.getAuthor());
        existingBook.setPublicationYear(dto.getPublicationYear());

        return bookMapper.bookToBookDto(bookRepository.save(existingBook));
    }

    @Override
    public void deleteBook(String isbn) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ISBN: " + isbn));
        bookRepository.delete(book);
    }
}
