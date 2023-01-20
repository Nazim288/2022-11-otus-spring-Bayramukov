package ru.otus.home2.dao;

import ru.otus.home2.domains.Task;

import java.util.List;

public interface TaskDao {
   List<Task> findAll();
   Task findById(Long id);
}
