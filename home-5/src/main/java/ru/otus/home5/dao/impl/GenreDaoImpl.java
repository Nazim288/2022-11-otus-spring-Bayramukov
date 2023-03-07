package ru.otus.home5.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.otus.home5.dao.GenreDao;
import ru.otus.home5.domains.Genre;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class GenreDaoImpl implements GenreDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long insert(Genre genre) {
        Map<String, Object> parameterSource = new HashMap<>();
        parameterSource.put("name", genre.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update("insert into genre (name) values (:name)",
                new MapSqlParameterSource(parameterSource), keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();

    }

    @Override
    public Genre getById(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

}
