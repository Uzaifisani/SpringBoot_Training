package com.example.LibraryBookManagement.respository;

import com.example.LibraryBookManagement.entity.Book;
import com.example.LibraryBookManagement.rowMapper.BookRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepositoryImpl implements BookRepository{

    private final JdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book addNewBook(Book book) {
        String insertQuery="insert into books (titile,author,genre,published_year,available_copies,created_at,updated_at) values (?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP )";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement(insertQuery, new String[]{"id"});
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getGenre());
            ps.setInt(4, book.getPublished_year());
            ps.setInt(5, book.getAvailable_copies());
            return ps;
        }, keyHolder);

        Long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return getBookById(generatedId);
    }

    @Override
    public List<Book> getAllBooks() {
        String getAllBookQuery="SELECT * FROM books";
        return jdbcTemplate.query(getAllBookQuery,new BookRowMapper());
    }

    @Override
    public Book getBookById(Long id) {
        String getBookByIdQuery= "SELECT * FROM books WHERE id=?";
        return jdbcTemplate.queryForObject(getBookByIdQuery,new BookRowMapper(),id);
    }

    @Override
    public Book updateBookById(Long id, Book updateBook) {
        String insertQuery="UPDATE books SET titile=?, author=?, genre=?, published_year=?, available_copies=?, updated_at=CURRENT_TIMESTAMP WHERE id=?";
        int res= jdbcTemplate.update(insertQuery,updateBook.getTitle(),updateBook.getAuthor(),updateBook.getGenre(),updateBook.getPublished_year(),updateBook.getAvailable_copies(),id);
        if(res!=0){
            return getBookById(id);
        }else{
            return null;
        }
    }

    @Override
    public Boolean deleteBookById(Long id) {
        int res= jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
        return res != 0;
    }
}
