package ru.otus.home4.dao;

import ru.otus.home4.domains.Task;

import java.util.List;

public interface TaskDao {
   List<Task> findAll();
   Task findById(Long id);
}
