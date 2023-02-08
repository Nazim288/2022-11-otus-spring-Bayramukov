package ru.otus.home4.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.home4.domains.Answer;
import ru.otus.home4.domains.Question;
import ru.otus.home4.domains.Student;
import ru.otus.home4.domains.Task;
import ru.otus.home4.services.impl.ExamServiceImpl;

import java.util.List;

@DisplayName("Должен ")
@ExtendWith(MockitoExtension.class)
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

        student1 = new Student("Student1","LastName1");

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