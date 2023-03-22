package ru.otus.home5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.home5.dao.AuthorDao;
import ru.otus.home5.dao.BookDao;
import ru.otus.home5.dao.GenreDao;
import ru.otus.home5.domains.Author;
import ru.otus.home5.domains.Book;
import ru.otus.home5.domains.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Home5ApplicationTests {
    private static final String EXISTING_BOOK_NAME = "book-name";
    private static final String EXISTING_GENRE_NAME = "genre-name";
    private static final String EXISTING_AUTHOR_NAME = "author-name";
    private static final String EXISTING_AUTHOR_LASTNAME = "author-lastname";

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private GenreDao genreDao;

    private Book book;
    private Long bookId;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void beforeTransaction() {
        Author author = new Author(EXISTING_AUTHOR_NAME, EXISTING_AUTHOR_LASTNAME);
        Genre genre = new Genre(EXISTING_GENRE_NAME);

        long authorId = authorDao.insert(author);
        author.setId(authorId);
        System.out.println("add test author");

        long genreId = genreDao.insert(genre);
        genre.setId(genreId);
        System.out.println("add test genre");
        book = new Book(EXISTING_BOOK_NAME, author, genre);

        bookId = bookDao.insert(book);
        System.out.println("add test book");
        book.setId(bookId);

    }

    @DisplayName("возвращать книгу по его id")
    @Test
    void shouldReturnExpectedPersonById() {
        Book actualBook = bookDao.getById(bookId);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(book);
    }


}
