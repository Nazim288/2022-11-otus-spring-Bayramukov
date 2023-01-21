package ru.otus.home2.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.home2.dao.TaskDao;
import ru.otus.home2.domains.Exam;
import ru.otus.home2.domains.Student;
import ru.otus.home2.domains.Task;
import ru.otus.home2.services.ExamService;
import ru.otus.home2.util.Printer;

import java.util.List;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class ExamServiceImpl implements ExamService {
    final private TaskDao taskDao;
    final Scanner scanner = new Scanner(System.in);

    @Override
    public void runExam() {
        Student student = getStudentData();
        startExamForStudent(student);
    }
    private void startExamForStudent(Student student) {
        final List<Task> tasks = taskDao.findAll();
        Exam exam = new Exam();
        exam.setStudent(student.getName());

        printSalutation(student);
        start(student, tasks);
        printResults(student);
        scanner.close();
    }

    private String askQuestion(Task task, int questionNumber) {
        return "\n question number " + questionNumber + ": \n"
                + task.getQuestion().getContent() + " \n"
                + "(the number of points for a correct answer is equal to) "
                + task.getNumberOfPoints() + "\n"
                + "Enter your answer:";
    }

    public boolean isAnswerCorrect(Task task, String answer) {
        return task.getAnswer().getValue().equalsIgnoreCase(answer);
    }

   private void printSalutation(Student student) {
        Printer.printLn("\n" + student.getName() + " good luck, the exam is starting");
    }
   public void start(Student student, List<Task> tasks) {
       int questionNumber = 1;
       long points = 0;
       for (Task task : tasks) {
           Printer.print(askQuestion(task, questionNumber));
           if (isAnswerCorrect(task, scanner.next())) {
               points = points + task.getNumberOfPoints();
           }
           questionNumber++;
       }
       student.setPoints(points);
   }
  private void printResults(Student student) {
      Printer.print("\n" + student.getName()
              + ", the exam is completed, your result: "
              + student.getPoints()
              + "\n"
              + "<><><><><><><><> END <><><><><><><><>");
    }


    private Student getStudentData() {
        Student student = new Student();
        Printer.print("Hello, write your name please:");
        student.setName(scanner.next());
        Printer.print("write your last name please:");
        student.setLastName(scanner.next());
        return student;
    }


}
