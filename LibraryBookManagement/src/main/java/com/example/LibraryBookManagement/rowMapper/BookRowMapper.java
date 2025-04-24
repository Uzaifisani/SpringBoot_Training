package com.example.LibraryBookManagement.rowMapper;

import com.example.LibraryBookManagement.entity.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book= new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("titile"));
        book.setAuthor(rs.getString("author"));
        book.setGenre(rs.getString("genre"));
        book.setAvailable_copies(rs.getInt("available_copies"));
        book.setPublished_year(rs.getInt("published_year"));
        book.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
        book.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
        return book;
    }
}
