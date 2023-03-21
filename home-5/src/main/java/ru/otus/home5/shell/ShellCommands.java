package ru.otus.home5.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.home5.dao.AuthorDao;
import ru.otus.home5.dao.BookDao;
import ru.otus.home5.dao.GenreDao;
import ru.otus.home5.domains.Author;
import ru.otus.home5.domains.Book;
import ru.otus.home5.domains.Genre;
import ru.otus.home5.dto.BookDto;
import ru.otus.home5.service.BookService;

import java.util.*;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    final private AuthorDao authorDao;
    final private GenreDao genreDao;
    final private BookDao bookDao;
    final private BookService bookService;

    @ShellMethod(value = "save new author", key = {"author save", "a-save"})
    public String authorSave(@ShellOption() String name, String lastName) {
        authorDao.insert(new Author(name, lastName));
        return "AUTHOR SAVE SUCCESSFULLY";
    }

    @ShellMethod(value = "get book by id", key = {"bookById", "b-id"})
    public String bookGetById(@ShellOption() Long id) {
        BookDto book = bookService.getById(id);
        return printBooks(Collections.singletonList(book));
    }

    @ShellMethod(value = "delete book by id", key = {"book-del"})
    public String bookDeleteById(@ShellOption() Long id) {
        bookDao.deleteById(id);
        return "BOOK DELETE SUCCESSFULLY";
    }

    @ShellMethod(value = "save new genre", key = {"genre save", "g-save"})
    public String genreSave(@ShellOption() String name) {
        genreDao.insert(new Genre(name));
        return "GENRE SAVE SUCCESSFULLY";
    }

    @ShellMethod(value = "save new book", key = {"book save", "b-save"})
    public String bookSave(@ShellOption() String name, Long authorId, Long genreId) {
        Author author = null;
        Genre genre = null;
        try {
            author = authorDao.getById(authorId);
        } catch (Exception e) {
            throw new RuntimeException(String.format("author with id [%s} not found", authorId));
        }
        try {
            genre = genreDao.getById(genreId);
        } catch (Exception e) {
            throw new RuntimeException(String.format("genre with id [%s} not found", genreId));
        }
        bookDao.insert(new Book(name, author, genre));
        return "SAVE SUCCESSFULLY";
    }

    @ShellMethod(value = "get all books", key = {"all-books", "b-all"})
    public String getAllBooks() {
        return printBooks(bookService.getAll());
    }

    @ShellMethod(value = "get all authors", key = {"all-authors", "a-all"})
    public String getAllAuthors() {
        return printAuthors(authorDao.getAll());
    }

    @ShellMethod(value = "get all books", key = {"all-genre", "g-all"})
    public String getAllGenres() {
        return printGenres(genreDao.getAll());
    }

    private String printBooks(List<BookDto> books) {
        StringBuilder text = new StringBuilder();
        int i = 0;
        if (books.size() > 0) {
            for (BookDto book : books) {
                text.append(++i).append(")").append("\n")
                        .append("book name: ").append(book.getName()).append("\n")
                        .append("book author: ").append(book.getAuthor().getName()).append(" ").append(book.getAuthor().getLastName()).append("\n")
                        .append("book genre: ").append(book.getGenre().getName()).append("\n");
            }
            return text.toString();
        } else return "not found";

    }

    private String printAuthors(List<Author> authors) {
        StringBuilder text = new StringBuilder();
        int i = 0;
        if (authors.size() > 0) {
            for (Author author : authors) {
                text.append(++i).append(")").append("\n")
                        .append("id: ").append(author.getId()).append("\n")
                        .append("name: ").append(author.getName()).append("\n")
                        .append("last name: ").append(author.getLastName()).append("\n");

            }
            return text.toString();
        } else return "not found";
    }

    private String printGenres(List<Genre> genres) {
        StringBuilder text = new StringBuilder();
        int i = 0;
        if (genres.size() > 0) {
            for (Genre genre : genres) {
                text.append(++i).append(")").append("\n")
                        .append("id: ").append(genre.getId()).append("\n")
                        .append("name: ").append(genre.getName()).append("\n");

            }
            return text.toString();
        } else return "not found";
    }


}
