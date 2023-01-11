package ru.otus.homework.services.impl;

import ru.otus.homework.dao.AnswerDao;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.services.AnswerService;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {

    private final AnswerDao answerDao;

    public AnswerServiceImpl(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    @Override
    public List<Answer> getAll() {
        return answerDao.findAll();
    }
}
