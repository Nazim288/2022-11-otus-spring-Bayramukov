package ru.otus.home4.dao;

import ru.otus.home4.domains.Answer;

import java.util.List;

public interface AnswerDao {
    List<Answer> findAll();
    Answer findById(Long id);

}
