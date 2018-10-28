package com.slaverivanje.blog.controller;

import com.slaverivanje.blog.domain.Question;
import com.slaverivanje.blog.domain.Quiz;
import com.slaverivanje.blog.service.IQuizService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
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

    @GetMapping(QUIZ_MAPPING + "/{url}")
    public Quiz findQuiz(@PathVariable("url") String url ) {
        return quizService.findByUrl(url);
    }

    @GetMapping(QUIZ_MAPPING + "/static/{filename}")
    public String staticJson(@PathVariable("filename") String filename) {
        Resource resource = new ClassPathResource("/static/quiz/" + filename);
        String quizContent = "";
        try(InputStream stream = resource.getInputStream()) {
            quizContent = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe);
        }
        return quizContent;
    }

    @PostMapping(QUIZ_MAPPING + "/{url}/{questionNumber}/{answerIndex}")
    public Question answerQuestion(@PathVariable("url") String url,
        @PathVariable("questionNumber") Long questionNumber,
        @PathVariable("answerIndex") Long answerIndex) {
        Question question = quizService.answerQuestion(url, questionNumber, answerIndex);
        return question;
    }

}

