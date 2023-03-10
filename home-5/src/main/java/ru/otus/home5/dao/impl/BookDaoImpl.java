package ru.otus.home5.dao.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.otus.home5.dao.BookDao;
import ru.otus.home5.domains.Book;
import ru.otus.home5.mapper.BookMapper;

import java.util.*;

@AllArgsConstructor
@Component
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public long insert(Book book) {
        Map<String, Object> parameterSource = new HashMap<>();
        parameterSource.put("name", book.getName());
        parameterSource.put("author", book.getAuthorId());
        parameterSource.put("genre", book.getGenreId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into book ( name, author_id, genre_id) values (:name, :author, :genre)",
                new MapSqlParameterSource(parameterSource), keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();

    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, author_id, genre_id from book where id = :id", params, new BookMapper()
        );
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select id, name, author_id, genre_id from book", new BookMapper());
    }


}
