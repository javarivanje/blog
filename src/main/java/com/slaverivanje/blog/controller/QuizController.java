package com.slaverivanje.blog.controller;

import com.slaverivanje.blog.domain.Question;
import com.slaverivanje.blog.domain.Quiz;
import com.slaverivanje.blog.service.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {

    public static final String QUIZ_MAPPING = "/quiz";

    private final IQuizService quizService;

    @Autowired
    public QuizController(IQuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(QUIZ_MAPPING + "/{url}/{url2}")
    public Quiz findQuiz(@PathVariable("url") String url, @PathVariable("url2") String url2 ) {
        return quizService.findByUrl(url+url2);
    }

    @PostMapping(QUIZ_MAPPING + "/{url}/{questionNumber}/{answerIndex}")
    public Question answerQuestion(@PathVariable("url") String url,
        @PathVariable("questionNumber") Long questionNumber,
        @PathVariable("answerIndex") Long answerIndex) {
        return quizService.answerQuestion(url, questionNumber, answerIndex);
    }

}

