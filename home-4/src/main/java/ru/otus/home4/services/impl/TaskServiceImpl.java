package ru.otus.home4.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.home4.dao.TaskDao;
import ru.otus.home4.domains.Task;
import ru.otus.home4.services.TaskService;

import java.util.List;
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    final private TaskDao taskDao;
    @Override
    public void printAll() {
        final List<Task> tasks = taskDao.findAll();
        for (Task task : tasks) {
            System.out.println(buildText(task));
        }
    }

    @Override
    public Task getById(Long id) {
        return null;
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
