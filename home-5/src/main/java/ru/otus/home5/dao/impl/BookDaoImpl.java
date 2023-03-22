package ru.otus.home5.dao.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.otus.home5.dao.BookDao;
import ru.otus.home5.domains.Book;
import ru.otus.home5.extractors.BookResultSetExtractor;
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
        parameterSource.put("author_id", book.getAuthor().getId());
        parameterSource.put("genre_id", book.getGenre().getId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into book ( name, author_id, genre_id) values (:name, :author_id, :genre_id)",
                new MapSqlParameterSource(parameterSource), keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();

    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        Book book = namedParameterJdbcOperations.queryForObject("select b.id, b.name, a.id author_id, a.name, a.last_name, g.id genre_id, g.name " +
                        "from (book b left join author a on " +
                        "b.author_id = a.id) left join genre g on b.genre_id = g.id where b.id = :id", params,
                new BookMapper());
        return book;
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
        Map<Long, Book> bookMap =
                namedParameterJdbcOperations.query("select b.id, b.name, a.id author_id, a.name, a.last_name, g.id genre_id, g.name " +
                                "from (book b left join author a on " +
                                "b.author_id = a.id) left join genre g on b.genre_id = g.id",
                        new BookResultSetExtractor());

        return new ArrayList<>(Objects.requireNonNull(bookMap).values());

    }

}
