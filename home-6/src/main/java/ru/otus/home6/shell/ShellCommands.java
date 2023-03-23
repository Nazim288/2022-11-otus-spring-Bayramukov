package ru.otus.home6.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.home6.domains.Book;
import ru.otus.home6.domains.BookComment;
import ru.otus.home6.dto.BookCommentDto;
import ru.otus.home6.dto.BookDto;
import ru.otus.home6.service.BookCommentService;
import ru.otus.home6.service.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final BookService bookService;
    private final BookCommentService bookCommentService;

    @ShellMethod(value = "get all books", key = {"all-books", "b-all"})
    public String getAllBooks() {
        return printBooks(bookService.getAll());
    }

    @ShellMethod(value = "delete book by id", key = {"del-b"})
    public String deleteBook(@ShellOption()Long bookId) {
        bookService.delete(bookId);
        return "BOOK DELETED";
    }

    @ShellMethod(value = "save new book", key = {"save-b"})
    public String saveBook(@ShellOption() String name, Long authorId, Long genreId) {
        BookDto dto = new BookDto(name, authorId, genreId);
        bookService.create(dto);
        return "BOOK SAVED";
    }

    @ShellMethod(value = "updated book", key = {"up-b"})
    public String updateBook(@ShellOption() Long bookId, String name, Long authorId, Long genreId) {
        BookDto dto = new BookDto(bookId, name, authorId, genreId);
        bookService.update(dto);
        return "BOOK UPDATED";
    }

    @ShellMethod(value = "get all book comments", key = {"all-c"})
    public String getAllBookComments(@ShellOption()Long bookId) {
        return printBookComments(bookService.getAllComments(bookId));
    }

    @ShellMethod(value = "delete bookComment by id", key = {"del-c"})
    public String deleteBookComment(@ShellOption()Long bookId) {
        bookCommentService.delete(bookId);
        return "COMMENT DELETED";
    }

    @ShellMethod(value = "save comment", key = {"save-c"})
    public String saveBookComment(@ShellOption() String value, Long bookId) {
        BookCommentDto dto = new BookCommentDto(value, bookId);
        bookCommentService.create(dto);
        return "COMMENT SAVED";
    }

   @ShellMethod(value = "update comment", key = {"up-c"})
    public String updateBookComment(@ShellOption() Long id, String value) {
        BookCommentDto dto = new BookCommentDto(id, value);
        bookCommentService.update(dto);
        return "UPDATE COMMENT";
    }


    private String printBooks(List<Book> books) {
        StringBuilder text = new StringBuilder();
        int i = 0;
        if (books.size() > 0) {
            for (Book book : books) {
                text.append(++i).append(")").append("\n")
                        .append("book name: ").append(book.getName()).append("\n")
                        .append("book author: ").append(book.getAuthor().getName()).append(" ").append(book.getAuthor().getLastName()).append("\n")
                        .append("book genre: ").append(book.getGenre().getName()).append("\n");
            }
            return text.toString();
        } else return "not found";

    }

    private String printBookComments(List<BookComment> comments) {
        StringBuilder text = new StringBuilder();
        int i = 0;
        if (comments.size() > 0) {
            for (BookComment comment : comments) {
                text.append(++i).append(": ").append(comment.getValue()).append("\n");
            }

        } else {
            return "not found";
        }
        return text.toString();
    }


}
