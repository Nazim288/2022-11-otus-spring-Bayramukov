package ru.otus.home5.dao;

import ru.otus.home5.domains.Book;

import java.util.List;


public interface BookDao {

    long insert(Book book);

    List<Book> getAll();

    Book getById(long id);

    void deleteById(long id);

}
