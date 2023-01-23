package ru.otus.home3.dao;

import ru.otus.home3.domains.Task;

import java.util.List;

public interface TaskDao {
   List<Task> findAll();
   Task findById(Long id);
}
