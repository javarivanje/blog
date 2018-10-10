package com.slaverivanje.blog.service;

import com.slaverivanje.blog.domain.Question;
import com.slaverivanje.blog.domain.Quiz;

public interface IQuizService {

    Quiz findByUrl(String url);

    Question answerQuestion(String url, Long questionNumber, Long answerIndex);

}
