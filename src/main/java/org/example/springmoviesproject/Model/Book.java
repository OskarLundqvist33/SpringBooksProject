package org.example.springmoviesproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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
    @Column(name = "\"year\"")
    private int year;
    private String isbn;

    public Book(){}

    public Book(String title, String author, String genre, String era, int year, String isbn) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.era = era;
        this.year = year;
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
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year = year;
    }
    public String getIsbn(){
        return isbn;
    }
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }
}
