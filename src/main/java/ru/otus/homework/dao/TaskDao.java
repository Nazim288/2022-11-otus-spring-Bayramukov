package ru.otus.homework.dao;

import ru.otus.homework.domain.Task;

import java.util.List;

public interface TaskDao {
   List<Task> getAll();
}
