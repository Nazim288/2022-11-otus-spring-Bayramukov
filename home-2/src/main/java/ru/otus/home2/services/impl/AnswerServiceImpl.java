package ru.otus.home2.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.home2.dao.AnswerDao;
import ru.otus.home2.domains.Answer;
import ru.otus.home2.services.AnswerService;

import java.util.List;
@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerDao answerDao;

    @Override
    public List<Answer> getAll() {
        return answerDao.findAll();
    }
}
