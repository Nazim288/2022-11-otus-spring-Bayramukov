package ru.otus.home5.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.otus.home5.dao.AuthorDao;
import ru.otus.home5.domains.Author;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long insert(Author author) {
        Map<String, Object> parameterSource = new HashMap<>();
        parameterSource.put("name", author.getName());
        parameterSource.put("last_name", author.getLastName());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update("insert into author ( name, last_name) values (:name, :last_name)",
                new MapSqlParameterSource(parameterSource), keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Author getById(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
