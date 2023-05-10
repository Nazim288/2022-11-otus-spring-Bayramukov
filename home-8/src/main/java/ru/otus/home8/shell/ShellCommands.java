package ru.otus.home8.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.home8.domain.Book;
import ru.otus.home8.domain.BookComment;
import ru.otus.home8.dto.BookDto;
import ru.otus.home8.service.BookService;

import java.util.List;

import static java.util.Objects.nonNull;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final BookService bookService;

    @ShellMethod(value = "save new book", key = {"s-b"})
    public String saveBook(@ShellOption() String name, String authorId, String genreId) {
        BookDto dto = new BookDto(name, authorId, genreId);
        bookService.createBook(dto);
        return "BOOK SAVED";
    }

    @ShellMethod(value = "get all books", key = {"all-books", "all-b"})
    public String getAllBooks() {
        return printBooks(bookService.getAllBooks());
    }

    @ShellMethod(value = "get book by id", key = {"", "id-b"})
    public String getBookById(@ShellOption() String id) {
        return printBook(bookService.getBookById(id));
    }

    @ShellMethod(value = "delete book by id", key = {"d-b"})
    public String deleteBook(@ShellOption() String bookId) {
        String result = "BOOK DELETED";
        if (!bookService.deleteBook(bookId)) {
            result = "not deleted, book not found";
        }
        return result;
    }

    @ShellMethod(value = "save comment", key = {"s-c"})
    public String saveBookComment(@ShellOption() String value, String bookId) {
        bookService.addComment(bookId, value);
        return "COMMENT SAVED";
    }

    @ShellMethod(value = "updated book", key = {"u-b"})
    public String updateBook(@ShellOption() String bookId, String name, String author, String genre) {
        BookDto dto = new BookDto(name, author, genre);
        bookService.updateBook(bookId, dto);
        return "BOOK UPDATED";
    }


    @ShellMethod(value = "get all book comments", key = {"all-c"})
    public String getAllBookComments(@ShellOption() String bookId) {
        return printBookComments(bookService.getComments(bookId));
    }

    @ShellMethod(value = "delete bookComment by id", key = {"d-c"})
    public String deleteBookComment(@ShellOption() String commentId) {
        bookService.deleteCommentById(commentId);
        return "COMMENT DELETED";
    }


    private String printBooks(List<Book> books) {
        StringBuilder text = new StringBuilder();
        int i = 0;
        if (books.size() > 0) {
            for (Book dto : books) {
                text.append(++i).append(")").append("\n")
                        .append("book id: ").append(dto.getId()).append("\n")
                        .append("book name: ").append(dto.getName()).append("\n")
                        .append("book author: ").append(dto.getAuthor().getName()).append(" ").append("\n")
                        .append("book genre: ").append(dto.getGenre().getName()).append("\n");
            }
            return text.toString();
        } else return "not found";


    }

    private String printBook(Book dto) {
        StringBuilder text = new StringBuilder();
        int i = 0;
        if (nonNull(dto)) {

            text.append(++i).append(")").append("\n")
                    .append("book id: ").append(dto.getId()).append("\n")
                    .append("book name: ").append(dto.getName()).append("\n")
                    .append("book author: ").append(dto.getAuthor().getName()).append(" ").append("\n")
                    .append("book genre: ").append(dto.getGenre().getName()).append("\n");

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
