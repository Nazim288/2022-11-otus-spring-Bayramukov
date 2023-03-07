package ru.otus.home5.dao;

import ru.otus.home5.domains.Author;

public interface AuthorDao {
    long insert(Author author);

    Author getById(long id);

    void deleteById(long id);

}
