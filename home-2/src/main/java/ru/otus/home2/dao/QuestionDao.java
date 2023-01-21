package ru.otus.home2.dao;

import ru.otus.home2.domains.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
    Question findById(Long id);
}
