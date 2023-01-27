package ru.otus.home3.dao;

import ru.otus.home3.domains.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
    Question findById(Long id);
}
