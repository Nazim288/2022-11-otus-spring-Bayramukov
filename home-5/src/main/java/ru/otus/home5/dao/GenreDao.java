package ru.otus.home5.dao;

import ru.otus.home5.domains.Genre;

public interface GenreDao {
    long insert(Genre genre);

    Genre getById(long id);

    void deleteById(long id);


}
