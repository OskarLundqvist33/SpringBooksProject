package org.example.springmoviesproject.mapper;

import org.example.springmoviesproject.Model.Book;
import org.example.springmoviesproject.dto.BookDTO;
import org.example.springmoviesproject.dto.CreateBookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDTO toDTO(Book book){
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setGenre(book.getGenre());
        dto.setEra(book.getEra());
        dto.setYear(book.getYear());
        dto.setIsbn(book.getIsbn());
        return dto;
    }

    public Book toEntity(CreateBookDTO dto){
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        book.setEra(dto.getEra());
        book.setYear(dto.getYear());
        book.setIsbn(dto.getIsbn());
        return book;
    }
}
