package ru.otus.homework.services.impl;

import ru.otus.homework.dao.QuestionDao;
import ru.otus.homework.domain.Question;
import ru.otus.homework.services.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    final private QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }


    @Override
    public List<Question> getAll() {
        return questionDao.findAll();

    }
}
