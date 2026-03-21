package org.example.springmoviesproject;

import org.example.springmoviesproject.Model.Book;
import org.example.springmoviesproject.dto.BookDTO;
import org.example.springmoviesproject.dto.CreateBookDTO;
import org.example.springmoviesproject.dto.UpdateBookDTO;
import org.example.springmoviesproject.exception.ResourceNotFoundException;
import org.example.springmoviesproject.mapper.BookMapper;
import org.example.springmoviesproject.repository.BookRepository;
import org.example.springmoviesproject.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    private Book book;
    private BookDTO bookDTO;
    private CreateBookDTO createBookDTO;
    private UpdateBookDTO updateBookDTO;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setTitle("Dune");
        book.setAuthor("Frank Herbert");
        book.setGenre("Science Fiction");
        book.setEra("Modern");
        book.setPublishDate(LocalDate.of(1965, 8, 1));
        book.setIsbn("9780441013593");
        book.setDescription("Epic sci-fi novel.");

        bookDTO = new BookDTO();
        bookDTO.setTitle("Dune");

        createBookDTO = new CreateBookDTO();
        createBookDTO.setTitle("Dune");
        createBookDTO.setAuthor("Frank Herbert");
        createBookDTO.setGenre("Science Fiction");
        createBookDTO.setEra("Modern");
        createBookDTO.setPublishDate(LocalDate.of(1965, 8, 1));
        createBookDTO.setIsbn("9780441013593");
        createBookDTO.setDescription("Epic sci-fi novel.");

        updateBookDTO = new UpdateBookDTO();
        updateBookDTO.setId(1L);
        updateBookDTO.setTitle("Dune Messiah");
        updateBookDTO.setAuthor("Frank Herbert");
        updateBookDTO.setGenre("Science Fiction");
        updateBookDTO.setEra("Modern");
        updateBookDTO.setPublishDate(LocalDate.of(1969, 10, 15));
        updateBookDTO.setIsbn("9780441172696");
        updateBookDTO.setDescription("Sequel novel.");
    }

    @Test
    void findAllBooks_shouldReturnMappedDtos() {
        Book secondBook = new Book();
        secondBook.setTitle("1984");

        when(bookRepository.findAll()).thenReturn(List.of(book, secondBook));
        when(bookMapper.toDTO(book)).thenReturn(bookDTO);

        BookDTO secondDto = new BookDTO();
        secondDto.setTitle("1984");
        when(bookMapper.toDTO(secondBook)).thenReturn(secondDto);

        List<BookDTO> result = bookService.findAllBooks();

        assertEquals(2, result.size());
        assertEquals("Dune", result.get(0).getTitle());
        assertEquals("1984", result.get(1).getTitle());
        verify(bookRepository).findAll();
        verify(bookMapper, times(2)).toDTO(any(Book.class));
    }

    @Test
    void saveBook_shouldMapAndSaveEntity() {
        when(bookMapper.toEntity(createBookDTO)).thenReturn(book);

        bookService.saveBook(createBookDTO);

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(captor.capture());
        assertEquals("Dune", captor.getValue().getTitle());
        verify(bookMapper).toEntity(createBookDTO);
    }

    @Test
    void deleteBook_shouldDeleteWhenBookExists() {
        when(bookRepository.existsById(1L)).thenReturn(true);

        bookService.deleteBook(1L);

        verify(bookRepository).deleteById(1L);
    }

    @Test
    void deleteBook_shouldThrowWhenBookDoesNotExist() {
        when(bookRepository.existsById(1L)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> bookService.deleteBook(1L)
        );

        assertEquals("Book with id 1 could not be found", exception.getMessage());
        verify(bookRepository, never()).deleteById(anyLong());
    }

    @Test
    void findUpdateById_shouldReturnUpdateDto() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        UpdateBookDTO mapped = new UpdateBookDTO();
        mapped.setId(1L);
        when(bookMapper.toUpdateDTO(book)).thenReturn(mapped);

        UpdateBookDTO result = bookService.findUpdateById(1L);

        assertEquals(1L, result.getId());
        verify(bookMapper).toUpdateDTO(book);
    }

    @Test
    void findUpdateById_shouldThrowWhenMissing() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> bookService.findUpdateById(1L)
        );

        assertEquals("Book with id 1 could not be found", exception.getMessage());
        verify(bookMapper, never()).toUpdateDTO(any());
    }

    @Test
    void updateBook_shouldUpdateAndSaveExistingEntity() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.updateBook(updateBookDTO);

        verify(bookMapper).updateEntity(updateBookDTO, book);
        verify(bookRepository).save(book);
    }

    @Test
    void updateBook_shouldThrowWhenMissing() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> bookService.updateBook(updateBookDTO)
        );

        assertEquals("Book with id 1 could not be found", exception.getMessage());
        verify(bookMapper, never()).updateEntity(any(), any());
        verify(bookRepository, never()).save(any());
    }

    @Test
    void findDtoById_shouldReturnBookDto() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.toDTO(book)).thenReturn(bookDTO);

        BookDTO result = bookService.findDtoById(1L);

        assertEquals("Dune", result.getTitle());
        verify(bookMapper).toDTO(book);
    }

    @Test
    void findDtoById_shouldThrowWhenMissing() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> bookService.findDtoById(1L)
        );

        assertEquals("Book with id 1 could not be found", exception.getMessage());
        verify(bookMapper, never()).toDTO(any());
    }
}
