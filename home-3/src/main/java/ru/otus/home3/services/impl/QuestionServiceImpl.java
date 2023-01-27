package ru.otus.home3.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.home3.dao.QuestionDao;
import ru.otus.home3.domains.Question;
import ru.otus.home3.services.QuestionService;

import java.util.List;
@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    final private QuestionDao questionDao;

    @Override
    public List<Question> getAll() {
        return questionDao.findAll();

    }
}
