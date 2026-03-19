package org.example.springmoviesproject.service;

import jakarta.transaction.Transactional;
import org.example.springmoviesproject.Model.Book;
import org.example.springmoviesproject.dto.BookDTO;
import org.example.springmoviesproject.dto.CreateBookDTO;
import org.example.springmoviesproject.dto.UpdateBookDTO;
import org.example.springmoviesproject.exception.ResourceNotFoundException;
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

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book with id " + id + " could not be found");
        }
        bookRepository.deleteById(id);
    }

    public UpdateBookDTO findUpdateById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " could not be found"));
    return bookMapper.toUpdateDTO(book);
    }

    @Transactional
    public void updateBook(UpdateBookDTO dto) {
        Book book = bookRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + dto.getId() + " could not be found"));
        bookMapper.updateEntity(dto, book);
        bookRepository.save(book);
    }
}
