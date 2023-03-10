package ru.otus.home5.dao.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.otus.home5.dao.AuthorDao;
import ru.otus.home5.domains.Author;
import ru.otus.home5.mapper.AuthorMapper;

import java.util.*;

@Component
@AllArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

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
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, last_name from author where id = :id", params, new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select id, name, last_name from author", new AuthorMapper());
    }

}
