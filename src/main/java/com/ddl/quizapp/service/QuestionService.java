package com.ddl.quizapp.service;

import com.ddl.quizapp.Question;
import com.ddl.quizapp.dao.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    public List<Question> getAllQuestions() {
        return questionDAO.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionDAO.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionDAO.save(question);
        return "success";
    }
}
