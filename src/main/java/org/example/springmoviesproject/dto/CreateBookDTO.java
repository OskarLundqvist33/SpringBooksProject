package org.example.springmoviesproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateBookDTO {
    @NotBlank(message = "Title is not allowed to be empty")
    private String title;

    @NotBlank(message = "Author is not allowed to be empty")
    private String author;

    @NotBlank(message = "Genre is not allowed to be empty")
    private String genre;

    @NotBlank(message = "Era is not allowed to be empty")
    private String era;

    @NotNull(message = "Year is not allowed to be empty")
    private int year;

    @Pattern(regexp = "^(?:ISBN(?:-13)?:?\\s*)?(?=[0-9X]{10}$|(?=(?:[0-9]+[-\\s]){3})[0-9[-\\s]]+X?$|97[89][0-9]{10}$|(?=(?:[0-9]+[-\\s]){4})[0-9[-\\s]]+$)[0-9]{1,5}[-\\s]?[0-9]+[-\\s]?[0-9]+[-\\s]?[0-9]*[0-9X]$")
    private String isbn;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
