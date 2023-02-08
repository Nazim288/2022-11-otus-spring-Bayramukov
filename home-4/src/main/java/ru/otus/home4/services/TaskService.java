package ru.otus.home4.services;


import ru.otus.home4.domains.Task;

public interface TaskService {
    void printAll();
    Task getById(Long id);
}
