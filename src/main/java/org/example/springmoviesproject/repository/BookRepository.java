package org.example.springmoviesproject.repository;

import org.example.springmoviesproject.Model.Book;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ListCrudRepository<Book, Long> {
}
