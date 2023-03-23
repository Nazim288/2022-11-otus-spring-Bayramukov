package ru.otus.home6.service;

import ru.otus.home6.domains.BookComment;
import ru.otus.home6.dto.BookCommentDto;

import java.util.List;


public interface BookCommentService {

    void delete(Long id);

    BookComment update(BookCommentDto dto);

    BookComment create(BookCommentDto dto);

    List<BookComment> getAllByBookId(Long bookId);

}
