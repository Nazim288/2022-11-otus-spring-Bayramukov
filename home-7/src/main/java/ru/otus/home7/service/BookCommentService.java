package ru.otus.home7.service;

import ru.otus.home7.domain.BookComment;

import java.util.List;

public interface BookCommentService {
    List<BookComment> getAllCommentByBookId(Long id);
    void delete(Long id);
    void addComment(Long bookId, String text);

}
