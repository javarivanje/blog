package com.slaverivanje.blog.service;

import com.slaverivanje.blog.domain.Question;
import com.slaverivanje.blog.domain.Quiz;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

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
        return null;
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
