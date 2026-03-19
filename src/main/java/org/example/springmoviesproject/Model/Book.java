package org.example.springmoviesproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String author;
    private String genre;
    private String era;
    @NotNull(message = "Date has to be in format yyyy-mm-dd")
    private LocalDate publishDate;
    private String isbn;

    public Book(){}

    public Book(String title, String author, String genre, String era, LocalDate publishDate, String isbn) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.era = era;
        this.publishDate = publishDate;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public String getEra(){
        return era;
    }
    public void setEra(String era){
        this.era = era;
    }
    public LocalDate getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
    public String getIsbn(){
        return isbn;
    }
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }
}
