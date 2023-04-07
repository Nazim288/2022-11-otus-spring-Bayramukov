package ru.otus.home7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.home7.domain.BookComment;

import java.util.List;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {

    List<BookComment> findBookCommentByBookId(Long id);
}
