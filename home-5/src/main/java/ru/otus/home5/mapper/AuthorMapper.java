package ru.otus.home5.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.home5.domains.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String lastName = resultSet.getString("last_name");
        Author author = new Author(id, name, lastName);
        author.setId(id);
        return author;
    }

}
