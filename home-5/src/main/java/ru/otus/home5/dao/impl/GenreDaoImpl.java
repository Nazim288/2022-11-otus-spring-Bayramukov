package ru.otus.home5.dao.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.otus.home5.dao.GenreDao;
import ru.otus.home5.domains.Genre;
import ru.otus.home5.mapper.GenreMapper;


import java.util.*;

@Component
@AllArgsConstructor
public class GenreDaoImpl implements GenreDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

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
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name from genre where id = :id", params, new GenreMapper()
        );
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select id, name from genre", new GenreMapper());
    }


}
