package ru.otus.home5.dao;

import ru.otus.home5.domains.Genre;

import java.util.List;

public interface GenreDao {
    long insert(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();


}
