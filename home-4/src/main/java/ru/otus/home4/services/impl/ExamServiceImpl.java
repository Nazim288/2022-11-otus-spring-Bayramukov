package ru.otus.home4.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.home4.config.LocalConfig;
import ru.otus.home4.dao.TaskDao;
import ru.otus.home4.domains.Exam;
import ru.otus.home4.domains.Student;
import ru.otus.home4.domains.Task;
import ru.otus.home4.enums.LocalMessages;
import ru.otus.home4.services.ExamService;
import ru.otus.home4.util.Printer;

import java.util.List;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class ExamServiceImpl implements ExamService {
    final private TaskDao taskDao;
    final private Scanner scanner = new Scanner(System.in);
    final private MessageSource messageSource;
    final private LocalConfig locale;

    @Override
    public Student runExam(String name, String lastName) {
        Student student = new Student(name, lastName);
        startExamForStudent(student);

        return student;
    }

    private void askQuestion(Task task, int questionNumber) {
        Printer.print(
                messageSource.getMessage(
                        LocalMessages.ASK_QUESTION.getValue()
                        , new String[]{String.valueOf(questionNumber), task.getQuestion().getContent(), String.valueOf(task.getNumberOfPoints())}
                        , locale.getLocale()
                )
        );
    }

    public boolean isAnswerCorrect(Task task, String answer) {
        return task.getAnswer().getValue().equalsIgnoreCase(answer);
    }

    public void start(Student student, List<Task> tasks) {
        int questionNumber = 1;
        long points = 0;
        for (Task task : tasks) {
            askQuestion(task, questionNumber);
            if (isAnswerCorrect(task, scanner.next())) {
                points = points + task.getNumberOfPoints();
            }
            questionNumber++;
        }
        student.setPoints(points);
    }

    private void printResults(Student student) {
        Printer.print(
                messageSource.getMessage(
                        LocalMessages.EXAM_RESULT.getValue(),
                        new String[]{student.getName(), String.valueOf(student.getPoints())},
                        locale.getLocale()));
    }

    private void askName() {
        Printer.print(
                messageSource.getMessage(
                        LocalMessages.ASK_STUDENT_NAME.getValue(),
                        new String[0],
                        locale.getLocale()));
    }

    private void askLastName() {
        Printer.print(
                messageSource.getMessage(
                        LocalMessages.ASK_STUDENT_LASTNAME.getValue(),
                        new String[0],
                        locale.getLocale()));
    }

    private void printSalutation(Student student) {
        Printer.printLn(
                messageSource.getMessage(
                        LocalMessages.EXAM_SALUTATION.getValue(),
                        new String[]{student.getName()},
                        locale.getLocale()
                )
        );
    }

    private void startExamForStudent(Student student) {
        final List<Task> tasks = taskDao.findAll();
        Exam exam = new Exam();
        exam.setStudent(student.getName());

        printSalutation(student);
        start(student, tasks);
        printResults(student);
    }


}
