package ru.otus.home6.repositories.impl;

import org.springframework.stereotype.Repository;
import ru.otus.home6.domains.Author;
import ru.otus.home6.domains.Book;
import ru.otus.home6.domains.Genre;
import ru.otus.home6.dto.BookDto;
import ru.otus.home6.repositories.BookRepository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @PersistenceContext
    private final EntityManager em;

    public BookRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre", Book.class);
        return query.getResultList();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public void delete(Long id) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Book update(BookDto dto) {
        Book updatedBook;
        Optional<Book> book = Optional.ofNullable(em.find(Book.class, dto.getId()));

        if (book.isPresent()) {
            updatedBook = book.get();
            if (nonNull(dto.getName())) {
                updatedBook.setName(dto.getName());
            }
            if (nonNull(dto.getGenre_id())) {
                Optional<Genre> genre = Optional.ofNullable(em.find(Genre.class, dto.getId()));
                if (genre.isPresent()) {
                    updatedBook.setGenre(genre.get());
                } else {
                    throw new EntityNotFoundException(Genre.class.getName());
                }
            }

            if (nonNull(dto.getAuthor_id())) {
                Optional<Author> author = Optional.ofNullable(em.find(Author.class, dto.getId()));
                if (author.isPresent()) {
                    updatedBook.setAuthor(author.get());
                } else {
                    throw new EntityNotFoundException(Author.class.getName());
                }
            }
        } else {
            throw new EntityNotFoundException(Book.class.getName());
        }
        em.persist(updatedBook);
        return book.get();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }
}
