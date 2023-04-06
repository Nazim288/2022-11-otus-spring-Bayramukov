package ru.otus.home6.service;

import ru.otus.home6.domains.BookComment;
import ru.otus.home6.dto.BookCommentDto;


public interface BookCommentService {

    void delete(BookComment comment);

    BookComment create(BookCommentDto dto);
    BookComment update(BookCommentDto dto);

}
