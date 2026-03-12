package org.example.springmoviesproject.service;

import org.example.springmoviesproject.Model.Book;
import org.example.springmoviesproject.dto.BookDTO;
import org.example.springmoviesproject.dto.CreateBookDTO;
import org.example.springmoviesproject.mapper.BookMapper;
import org.example.springmoviesproject.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDTO> findAllBooks(){
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    public void saveBook(CreateBookDTO dto) {
        Book entity = bookMapper.toEntity(dto);
        bookRepository.save(entity);
    }
}
