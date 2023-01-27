package ru.otus.home3.dao;

import ru.otus.home3.domains.Answer;

import java.util.List;

public interface AnswerDao {
    List<Answer> findAll();
    Answer findById(Long id);

}
