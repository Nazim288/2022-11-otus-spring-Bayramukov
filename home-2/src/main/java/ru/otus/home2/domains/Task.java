package ru.otus.home2.domains;

import lombok.Data;

@Data
public class Task {
    private Long id;
    private Answer answer;
    private Question question;
    private String taskNumber;
    private Long numberOfPoints;

}
