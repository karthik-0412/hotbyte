package com.hexaware.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.dto.BookDTO;
import com.hexaware.entity.Book;

@Component
public class BookMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Book bookDtoToBook(BookDTO dto) {
        return modelMapper.map(dto, Book.class);
    }

    public BookDTO bookToBookDto(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }
}
