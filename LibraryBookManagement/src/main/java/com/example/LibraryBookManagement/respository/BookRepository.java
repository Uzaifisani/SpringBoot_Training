package com.example.LibraryBookManagement.respository;

import com.example.LibraryBookManagement.entity.Book;

import java.util.List;

public interface BookRepository {
    Book addNewBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book updateBookById(Long id, Book updateBook);
    Boolean deleteBookById(Long id);
}
