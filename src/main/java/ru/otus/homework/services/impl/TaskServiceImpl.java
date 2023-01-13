package ru.otus.homework.services.impl;

import ru.otus.homework.dao.TaskDao;
import ru.otus.homework.domain.Task;
import ru.otus.homework.services.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    final private TaskDao taskDao;

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void printAll() {
        final List<Task> tasks = taskDao.getAll();
        for (Task task : tasks) {
            System.out.println(buildText(task));
        }
    }

    private String buildText(Task task) {
        return "\n"
                + "[" + task.getTaskNumber() + "]" + "\n"
                + task.getQuestion().getContent() + "\n"
                + "correct answer: " + task.getAnswer().getValue() + "\n"
                + "the number of points for the correct answer: " + task.getNumberOfPoints() + "\n"
                + "<><><><><><><><><><><><><><><><><><><><><><><><><><><>";
    }


}
