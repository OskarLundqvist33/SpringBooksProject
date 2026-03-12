package org.example.springmoviesproject;

import org.example.springmoviesproject.Model.Book;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequestMapping("/books")
@Controller
public class BookController {
    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = List.of(
                new Book("The Modern Prometheus", "Mary Shelley", "Psychological", "Romance", "1818", "9780199537167"),
                new Book("Crime and Punishment", "Fyodor Dostoevsky", "Psychological", "Realism", "1866", "9781857150353"),
                new Book("Down and Out of Paris and London", "George Orwell", "Psychological", "Realism", "1956", "9781529032703")
        );

        model.addAttribute("books", books);
        return "book-list";
    }

}
