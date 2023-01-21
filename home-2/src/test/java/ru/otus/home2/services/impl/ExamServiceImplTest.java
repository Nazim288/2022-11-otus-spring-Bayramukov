package ru.otus.home2.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import ru.otus.home2.domains.Answer;
import ru.otus.home2.domains.Question;
import ru.otus.home2.domains.Student;
import ru.otus.home2.domains.Task;


import java.util.List;
import java.util.Scanner;

@DisplayName("Должен ")
@ExtendWith(MockitoExtension.class)
@Import(Scanner.class)

class ExamServiceImplTest {

    @InjectMocks
    private ExamServiceImpl examService;


    private Student student1;
    private List<Task> tasks;
    private Task task1;
    @BeforeEach
    void setUp() {

        final String ans1 = "100";
        final String ans2 = "200";
        final String ques1 = "100+0";
        final String ques2 = "100+100";

        student1 = new Student();
        student1.setName("Student1");
        student1.setLastName("LastName1");

        Answer answer1 = new Answer();
        answer1.setValue(ans1);
        Answer answer2 = new Answer();
        answer2.setValue(ans2);

        Question question1 = new Question();
        question1.setContent(ques1);
        Question question2 = new Question();
        question2.setContent(ques2);

        task1 = new Task();
        task1.setAnswer(answer1);
        task1.setQuestion(question1);
        Task task2 = new Task();
        task2.setAnswer(answer2);
        task2.setQuestion(question2);

        tasks= List.of(task1, task2);


    }

    @DisplayName("проверить что ответ верен")
    @Test
    void shouldCheckAnswerForFidelity(){
        boolean correct = examService.isAnswerCorrect(task1, "100");
        Assertions.assertTrue(correct);
        boolean notCorrect = examService.isAnswerCorrect(task1, "200");
        Assertions.assertFalse(notCorrect);


    }



}