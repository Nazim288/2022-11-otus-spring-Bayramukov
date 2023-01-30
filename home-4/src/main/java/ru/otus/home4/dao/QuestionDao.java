package ru.otus.home4.dao;

import ru.otus.home4.domains.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
    Question findById(Long id);
}
