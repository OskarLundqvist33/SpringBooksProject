package org.example.springmoviesproject;

import org.example.springmoviesproject.Model.Book;
import org.example.springmoviesproject.dto.BookDTO;
import org.example.springmoviesproject.dto.CreateBookDTO;
import org.example.springmoviesproject.dto.UpdateBookDTO;
import org.example.springmoviesproject.mapper.BookMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    private final BookMapper bookMapper = new BookMapper();

    @Test
    void toDTO_shouldMapEntityToDto() {
        Book book = new Book();
        book.setTitle("1984");
        book.setAuthor("George Orwell");
        book.setGenre("Dystopian");
        book.setEra("Modern");
        book.setPublishDate(LocalDate.of(1949, 6, 8));
        book.setIsbn("1234567890");
        book.setDescription("A classic novel.");

        setBookId(book, 1L);

        BookDTO dto = bookMapper.toDTO(book);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("1984", dto.getTitle()),
                () -> assertEquals("George Orwell", dto.getAuthor()),
                () -> assertEquals("Dystopian", dto.getGenre()),
                () -> assertEquals("Modern", dto.getEra()),
                () -> assertEquals(LocalDate.of(1949, 6, 8), dto.getPublishDate()),
                () -> assertEquals("1234567890", dto.getIsbn()),
                () -> assertEquals("A classic novel.", dto.getDescription())
        );
    }

    @Test
    void toEntity_shouldMapCreateDtoToEntity() {
        CreateBookDTO dto = new CreateBookDTO();
        dto.setTitle("Dune");
        dto.setAuthor("Frank Herbert");
        dto.setGenre("Science Fiction");
        dto.setEra("Modern");
        dto.setPublishDate(LocalDate.of(1965, 8, 1));
        dto.setIsbn("9780441013593");
        dto.setDescription("Epic sci-fi novel.");

        Book book = bookMapper.toEntity(dto);

        assertAll(
                () -> assertNull(book.getId()),
                () -> assertEquals("Dune", book.getTitle()),
                () -> assertEquals("Frank Herbert", book.getAuthor()),
                () -> assertEquals("Science Fiction", book.getGenre()),
                () -> assertEquals("Modern", book.getEra()),
                () -> assertEquals(LocalDate.of(1965, 8, 1), book.getPublishDate()),
                () -> assertEquals("9780441013593", book.getIsbn()),
                () -> assertEquals("Epic sci-fi novel.", book.getDescription())
        );
    }

    @Test
    void toUpdateDTO_shouldMapEntityToUpdateDto() {
        Book book = new Book();
        book.setTitle("Brave New World");
        book.setAuthor("Aldous Huxley");
        book.setGenre("Dystopian");
        book.setEra("Modern");
        book.setPublishDate(LocalDate.of(1932, 1, 1));
        book.setIsbn("1234567890");
        book.setDescription("Another classic.");

        setBookId(book, 7L);

        UpdateBookDTO dto = bookMapper.toUpdateDTO(book);

        assertAll(
                () -> assertEquals(7L, dto.getId()),
                () -> assertEquals("Brave New World", dto.getTitle()),
                () -> assertEquals("Aldous Huxley", dto.getAuthor()),
                () -> assertEquals("Dystopian", dto.getGenre()),
                () -> assertEquals("Modern", dto.getEra()),
                () -> assertEquals(LocalDate.of(1932, 1, 1), dto.getPublishDate()),
                () -> assertEquals("1234567890", dto.getIsbn()),
                () -> assertEquals("Another classic.", dto.getDescription())
        );
    }

    @Test
    void updateEntity_shouldCopyFieldsIntoExistingBook() {
        UpdateBookDTO dto = new UpdateBookDTO();
        dto.setTitle("Updated Title");
        dto.setAuthor("Updated Author");
        dto.setGenre("Updated Genre");
        dto.setEra("Updated Era");
        dto.setPublishDate(LocalDate.of(2020, 5, 20));
        dto.setIsbn("9999999999");
        dto.setDescription("Updated description.");

        Book book = new Book();
        book.setTitle("Old Title");
        book.setAuthor("Old Author");

        bookMapper.updateEntity(dto, book);

        assertAll(
                () -> assertEquals("Updated Title", book.getTitle()),
                () -> assertEquals("Updated Author", book.getAuthor()),
                () -> assertEquals("Updated Genre", book.getGenre()),
                () -> assertEquals("Updated Era", book.getEra()),
                () -> assertEquals(LocalDate.of(2020, 5, 20), book.getPublishDate()),
                () -> assertEquals("9999999999", book.getIsbn()),
                () -> assertEquals("Updated description.", book.getDescription())
        );
    }

    private void setBookId(Book book, Long id) {
        try {
            var field = Book.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(book, id);
        } catch (Exception e) {
            fail("Failed to set book id for test: " + e.getMessage());
        }
    }
}
