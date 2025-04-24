package com.example.LibraryBookManagement.service;

import com.example.LibraryBookManagement.entity.Book;
import com.example.LibraryBookManagement.respository.BookRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepositoryImpl bookRepository;

    public BookServiceImpl(BookRepositoryImpl bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addNewBook(Book book) {
        if(Objects.nonNull(book)){
            return bookRepository.addNewBook(book);
        }else{
            return null;
        }
    }

    @Override
    public Optional<List<Book>> getAllBooks() {
        Optional<List<Book>> allBooks= Optional.ofNullable(bookRepository.getAllBooks());
        return allBooks;
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(bookRepository.getBookById(id));
    }

    @Override
    public Optional<Book> updateBookById(Long id, Book updateBook) {
        return Optional.ofNullable(bookRepository.updateBookById(id,updateBook));
    }

    @Override
    public Boolean deleteBookById(Long id) {
        return bookRepository.deleteBookById(id);
    }
}
