package com.slaverivanje.blog.service;

import com.slaverivanje.blog.domain.Question;
import com.slaverivanje.blog.domain.Quiz;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

@Service
public class QuizServiceImpl implements IQuizService {

    private Quiz quiz = new Quiz();

    @Override
    public Quiz findByUrl(String url) {
        ClassPathResource quizResource = new ClassPathResource("/static/quiz/" + url + ".adoc");
        return parse(quizResource);
    }

    private Quiz parse(ClassPathResource quizResource) {
        // TODO: ovde treba da dodas logiku
        try(InputStream stream = quizResource.getInputStream()){
            String quizContent;
            quizContent = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));


        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe);
        }
        return quiz;
    }

    @Override
    public Question answerQuestion(String url, Long questionNumber, Long answerIndex) {
        if(quiz.getUrl().equals(url)) {
            return (Question) quiz.getQuestions(questionNumber);
        }
        else System.out.println("Answer does not exist!");
            return null;//return null;
    }

}
