package ru.otus.homework.dao;

import ru.otus.homework.domain.Answer;

import java.util.List;

public interface AnswerDao {
    List<Answer> findAll();
    Answer findById(Long id);

}
