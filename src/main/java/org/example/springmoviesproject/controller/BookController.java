package org.example.springmoviesproject.controller;

import jakarta.validation.Valid;
import org.example.springmoviesproject.Model.Book;
import org.example.springmoviesproject.dto.CreateBookDTO;
import org.example.springmoviesproject.service.BookService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequestMapping("/")
@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "book-list";
    }

    @GetMapping("/create")
    public String showCreateBookForm(Model model) {
        model.addAttribute("book", new CreateBookDTO());
        return "book-form";
    }

    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute("book") CreateBookDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return "book-form";
        }
        bookService.saveBook(dto);
        System.out.println("Saving book: " + dto.getTitle());
        return "redirect:/";
    }
}

//new Book("The Modern Prometheus", "Mary Shelley", "Psychological", "Romance", "1818", "9780199537167"),
//new Book("Crime and Punishment", "Fyodor Dostoevsky", "Psychological", "Realism", "1866", "9781857150353"),
//new Book("Down and Out of Paris and London", "George Orwell", "Psychological", "Realism", "1956", "9781529032703")
