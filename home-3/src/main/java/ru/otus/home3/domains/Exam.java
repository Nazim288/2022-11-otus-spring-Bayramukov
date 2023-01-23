package ru.otus.home3.domains;

import lombok.Data;

@Data
public class Exam {
    private int numberOfCorrectAnswers;
    private int totalNumberOfPoints;
    private String student;

}
