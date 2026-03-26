package org.example.springmoviesproject.controller;

import jakarta.validation.Valid;
import org.example.springmoviesproject.dto.BookDTO;
import org.example.springmoviesproject.dto.CreateBookDTO;
import org.example.springmoviesproject.dto.UpdateBookDTO;
import org.example.springmoviesproject.service.BookService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateBookForm(@PathVariable("id") Long id, Model model) {
        UpdateBookDTO dto = bookService.findUpdateById(id);
        model.addAttribute("book", dto);
        return "book-update";
    }

    @PostMapping("/update")
    public String updateBook(@Valid @ModelAttribute("book") UpdateBookDTO dto, BindingResult result) {

        System.out.println("Trying to update book with ID: " + dto.getId());

        if (result.hasErrors()) {
            System.out.println("Validation errors occurred");
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "book-update";
        }
        bookService.updateBook(dto);
        System.out.println("Book updated successfully, redirecting to home page");
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showBookDetails(@PathVariable("id") Long id, Model model) {
        BookDTO book = bookService.findDtoById(id);
        model.addAttribute("book", book);
        return "book-details";
    }
}

//new Book("The Modern Prometheus", "Mary Shelley", "Psychological", "Romance", "1818", "9780199537167"),
//new Book("Crime and Punishment", "Fyodor Dostoevsky", "Psychological", "Realism", "1866", "9781857150353"),
//new Book("Down and Out of Paris and London", "George Orwell", "Psychological", "Realism", "1956", "9781529032703")
