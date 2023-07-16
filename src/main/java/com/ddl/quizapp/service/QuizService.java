package com.ddl.quizapp.service;

import com.ddl.quizapp.dao.QuestionDAO;
import com.ddl.quizapp.dao.QuizDAO;
import com.ddl.quizapp.model.Question;
import com.ddl.quizapp.model.QuestionWrapper;
import com.ddl.quizapp.model.Quiz;
import com.ddl.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDAO quizDAO;
    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        if(quiz.isPresent()) {
            List<Question> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();

            for (Question q : questionsFromDB) {
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4(), q.getQuestionTitle());
                questionsForUser.add(qw);
            }

            return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        if(quiz.isPresent()) {
            List<Question> questions = quiz.get().getQuestions();
            int right = 0;
            int i = 0;
            for(Response response: responses) {
                if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
                    right++;
                }
                i++;
            }

            return new ResponseEntity<>(right, HttpStatus.OK);
        }

        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }
}
