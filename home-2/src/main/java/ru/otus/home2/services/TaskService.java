package ru.otus.home2.services;


import ru.otus.home2.domains.Task;

public interface TaskService {
    void printAll();
    Task getById(Long id);
}
