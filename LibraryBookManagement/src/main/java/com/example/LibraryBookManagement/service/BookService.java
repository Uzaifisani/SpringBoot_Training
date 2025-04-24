package com.example.LibraryBookManagement.service;

import com.example.LibraryBookManagement.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book addNewBook(Book book);
    Optional<List<Book>> getAllBooks();
    Optional<Book> getBookById(Long id);
    Optional<Book> updateBookById(Long id, Book updateBook);
    Boolean deleteBookById(Long id);
}
