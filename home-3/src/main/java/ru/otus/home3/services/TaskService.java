package ru.otus.home3.services;


import ru.otus.home3.domains.Task;

public interface TaskService {
    void printAll();
    Task getById(Long id);
}
