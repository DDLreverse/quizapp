package com.ddl.quizapp.controller;

import com.ddl.quizapp.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ddl.quizapp.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public List<Question> getAllQuestions() {
        // Get questions from Service Layer
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}") // value of category will be assigned to the parameter
    public List<Question> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return "success";
    }
}
