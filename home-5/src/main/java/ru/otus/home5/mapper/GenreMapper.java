package ru.otus.home5.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.home5.domains.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        String name = resultSet.getString("name");
        long id = resultSet.getLong("id");
        Genre genre = new Genre(name);
        genre.setId(id);
        return genre;
    }

}
