package ru.otus.home2.dao;

import ru.otus.home2.domains.Answer;

import java.util.List;

public interface AnswerDao {
    List<Answer> findAll();
    Answer findById(Long id);

}
