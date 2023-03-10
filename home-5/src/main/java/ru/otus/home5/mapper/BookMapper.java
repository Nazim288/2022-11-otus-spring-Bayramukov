package ru.otus.home5.mapper;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import ru.otus.home5.domains.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        String name = resultSet.getString("name");
        Long authorId = (Long) resultSet.getObject("author_id");
        Long genreId = (Long) resultSet.getObject("genre_id");

        return new Book(name, authorId, genreId);
    }


}
