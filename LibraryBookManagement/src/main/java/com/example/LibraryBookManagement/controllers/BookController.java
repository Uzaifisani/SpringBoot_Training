package com.example.LibraryBookManagement.controllers;

import com.example.LibraryBookManagement.entity.Book;
import com.example.LibraryBookManagement.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> addNewBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addNewBook(book), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(){
        if(bookService.getAllBooks().isPresent()){
            return new ResponseEntity<>(bookService.getAllBooks().get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        if(bookService.getBookById(id).isPresent()){
            return new ResponseEntity<>(bookService.getBookById(id).get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id,@RequestBody Book updateBook){
        if(bookService.updateBookById(id,updateBook).isPresent()){
            return new ResponseEntity<>(bookService.updateBookById(id,updateBook).get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBookById(@PathVariable Long id){
        if(bookService.deleteBookById(id)){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
    }

}
