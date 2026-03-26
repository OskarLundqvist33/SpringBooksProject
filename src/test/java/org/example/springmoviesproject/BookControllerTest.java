package org.example.springmoviesproject;

import org.example.springmoviesproject.config.SecurityConfig;
import org.example.springmoviesproject.controller.BookController;
import org.example.springmoviesproject.dto.BookDTO;
import org.example.springmoviesproject.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import  org.springframework.test.context.bean.override.mockito.*;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    @WithMockUser
    public void testListBooks_ShouldReturnListViewAndBooksAttribute() throws Exception {
        BookDTO b1 = new BookDTO();
        b1.setTitle("Bok 1");
        List<BookDTO> books = Arrays.asList(b1);

        when(bookService.findAllBooks()).thenReturn(books);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("book-list"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books", hasItem(
                        hasProperty("title", is("Bok 1"))
                )));
    }

    @Test
    @WithMockUser
    public void testShowDetails_ShouldReturnDetailsView() throws Exception {
        BookDTO book = new BookDTO();
        book.setId(1L);
        book.setTitle("Harry Potter");

        when(bookService.findDtoById(1L)).thenReturn(book);

        mockMvc.perform(get("/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("book-details"))
                .andExpect(model().attribute("book", hasProperty("title", is("Harry Potter")))); // Verifiera attribut
    }
}