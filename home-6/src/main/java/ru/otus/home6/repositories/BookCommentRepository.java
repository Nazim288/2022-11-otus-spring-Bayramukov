package ru.otus.home6.repositories;

import ru.otus.home6.domains.BookComment;
import ru.otus.home6.dto.BookCommentDto;

import java.util.List;

public interface BookCommentRepository {
    List<BookComment> findAllCommentsByBookId(Long bookId);

    void delete(Long id);

    BookComment update(BookCommentDto dto);

    BookComment save(BookComment comment);

}
