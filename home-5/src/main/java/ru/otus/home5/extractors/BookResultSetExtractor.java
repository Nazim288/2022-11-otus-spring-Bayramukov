package ru.otus.home5.extractors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.home5.domains.Author;
import ru.otus.home5.domains.Book;
import ru.otus.home5.domains.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BookResultSetExtractor implements
        ResultSetExtractor<Map<Long, Book>> {
    @Override
    public Map<Long, Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Book> bookMap = new HashMap<>();

        while (rs.next()) {
            long id = rs.getLong("id");
            Book book = bookMap.get(id);
            if (book == null) {
                book = new Book(id, rs.getString("name"),
                        new Author(rs.getLong("author_id"), rs.getString("author.name"), rs.getString("last_name")),
                        new Genre(rs.getLong("genre_id"), rs.getString("genre.name")));
                bookMap.put(book.getId(), book);
            }

        }
        return bookMap;
    }
}
