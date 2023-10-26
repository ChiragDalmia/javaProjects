package ca.sheridancollege.dalmiac.repositories;

import ca.sheridancollege.dalmiac.beans.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BookRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public BookRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Book book) {
        String sql = "INSERT INTO books (isbn, name, author, role, creationDate) VALUES (:isbn, :name, :author, :role, :creationDate)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("isbn", book.getIsbn())
                .addValue("name", book.getName())
                .addValue("author", book.getAuthor())
                .addValue("role", book.getRole())
                .addValue("creationDate", LocalDateTime.now());
        jdbc.update(sql, params);
    }

    public List<Book> findAll() {
        String sql = "SELECT * FROM books";
        RowMapper<Book> mapper = (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setIsbn(rs.getString("isbn"));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setRole(rs.getString("role"));
            book.setCreationDate(rs.getTimestamp("creationDate").toLocalDateTime());
            return book;
        };
        return jdbc.query(sql, mapper);
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM books WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        jdbc.update(sql, params);
    }
}
