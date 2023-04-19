package ru.otus.home7.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.home7.domain.BookComment;
import ru.otus.home7.dto.BookCreateDto;
import ru.otus.home7.dto.BookDto;
import ru.otus.home7.dto.BookUpdateDto;
import ru.otus.home7.service.BookCommentService;
import ru.otus.home7.service.BookService;

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
    public String deleteBook(@ShellOption() Long bookId) {
        bookService.delete(bookId);
        return "BOOK DELETED";
    }

    @ShellMethod(value = "save new book", key = {"save-b"})
    public String saveBook(@ShellOption() String name, Long authorId, Long genreId) {
        BookCreateDto dto = new BookCreateDto(name, authorId, genreId);
        bookService.create(dto);
        return "BOOK SAVED";
    }

    @ShellMethod(value = "updated book", key = {"up-b"})
    public String updateBook(@ShellOption() Long bookId, String name, Long genreId) {
        BookUpdateDto dto = new BookUpdateDto(bookId, name, genreId);
        bookService.update(dto);
        return "BOOK UPDATED";
    }

    @ShellMethod(value = "get all book comments", key = {"all-c"})
    public String getAllBookComments(@ShellOption() Long bookId) {
        return printBookComments(bookCommentService.getAllCommentByBookId(bookId));
    }

    @ShellMethod(value = "delete bookComment by id", key = {"del-c"})
    public String deleteBookComment(@ShellOption() Long id) {
        bookCommentService.delete(id);
        return "COMMENT DELETED";
    }

    @ShellMethod(value = "save comment", key = {"save-c"})
    public String saveBookComment(@ShellOption() String value, Long bookId) {
        bookCommentService.addComment(bookId, value);
        return "COMMENT SAVED";
    }

    private String printBooks(List<BookDto> books) {
        StringBuilder text = new StringBuilder();
        int i = 0;
        if (books.size() > 0) {
            for (BookDto dto : books) {
                text.append(++i).append(")").append("\n")
                        .append("book id: ").append(dto.getId()).append("\n")
                        .append("book name: ").append(dto.getName()).append("\n")
                        .append("book author: ").append(dto.getAuthorName()).append(" ").append("\n")
                        .append("book genre: ").append(dto.getGenre()).append("\n");
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
