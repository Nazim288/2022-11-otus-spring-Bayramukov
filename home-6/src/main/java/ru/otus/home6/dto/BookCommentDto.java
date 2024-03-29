package ru.otus.home6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookCommentDto {
    private Long id;
    private String commentValue;
    private Long bookId;




    public BookCommentDto(String commentValue, Long bookId) {
        this.commentValue = commentValue;
        this.bookId = bookId;
    }

    public BookCommentDto(Long id, String commentValue) {
        this.id = id;
        this.commentValue = commentValue;
    }
}
