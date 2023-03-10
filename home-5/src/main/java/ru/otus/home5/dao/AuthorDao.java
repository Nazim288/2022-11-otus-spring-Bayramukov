package ru.otus.home5.dao;

import ru.otus.home5.domains.Author;

import java.util.List;

public interface AuthorDao {
    long insert(Author author);

    Author getById(long id);

    List<Author> getAll();


}
