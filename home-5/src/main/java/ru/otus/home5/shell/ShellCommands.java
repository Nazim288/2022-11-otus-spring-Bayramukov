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

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    final private AuthorDao authorDao;
    final private GenreDao genreDao;
    final private BookDao bookDao;

    @ShellMethod(value = "save new author", key = {"author save", "a-save"})
    public String authorSave(@ShellOption() String name, String lastName) {
        authorDao.insert(new Author(name, lastName));
        return "AUTHOR SAVE SUCCESSFULLY";
    }

    @ShellMethod(value = "get book by id", key = {"bookById"})
    public String bookGetById(@ShellOption() Long id) {
        Book book = bookDao.getById(id);
        return "book name: " + book.getName();
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
        bookDao.insert(new Book(name, authorId, genreId));
        return "SAVE SUCCESSFULLY";
    }

   @ShellMethod(value = "get all books", key = {"get-books", "b-all"})
    public String getAllBooks() {
        return printBooks(bookDao.getAll());
    }

    private String printBooks(List<Book> books){
        StringBuilder text = new StringBuilder();
        for (Book book : books){
            text.append("\n").append("book name: ").append(book.getName());
        }
        return text.toString();

    }


}
