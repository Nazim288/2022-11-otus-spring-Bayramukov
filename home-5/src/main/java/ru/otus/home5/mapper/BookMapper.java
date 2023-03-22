package ru.otus.home5.mapper;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import ru.otus.home5.domains.Author;
import ru.otus.home5.domains.Book;
import ru.otus.home5.domains.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Long bookId = resultSet.getLong("id");
        String bookName = resultSet.getString("name");

        Long authorId = resultSet.getLong("author.id");
        String authorName = (String) resultSet.getObject("author.name");
        String authorLastName = (String) resultSet.getObject("last_name");

        Long genreId = resultSet.getLong("genre.id");
        String genreName = (String) resultSet.getObject("genre.name");

        return new Book(bookId,bookName, new Author(authorId, authorName, authorLastName), new Genre(genreId,genreName));
    }


}
