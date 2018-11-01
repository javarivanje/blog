package com.slaverivanje.blog.controller;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.slaverivanje.blog.domain.Correct;
import com.slaverivanje.blog.domain.Question;
import com.slaverivanje.blog.domain.Quiz;
import com.slaverivanje.blog.service.IQuizService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RunWith(SpringRunner.class)
@WebMvcTest(QuizController.class)
public class QuizControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IQuizService quizService;

    @Test
    public void givenQuiz_whenGetQuiz_thenReturnJsonQuiz() throws Exception {
        Correct c = new Correct();
        c.setIndex(1L);
        c.setText("Milos!");

        Question q = new Question();
        String a1 = "Slavisa";
        String a2 = "Milos";
        List<String> answers = new ArrayList<>();
        answers.add(a1);
        answers.add(a2);
        String image = "Slika";
        Long number = 120L;
        String prompt = "Ko testira kontroler?";
        q.setAnswers(answers);
        q.setCorrect(c);
        q.setImage(image);
        q.setNumber(number);
        q.setPrompt(prompt);

        Quiz quiz = new Quiz();
        List<Question> questions = new ArrayList<>();
        questions.add(q);
        String title = "QuizControllerTests";
        String url = "tests";

        quiz.setQuestions(questions);
        quiz.setTitle(title);
        quiz.setUrl(url);

        given(quizService.findByUrl("tests")).willReturn(quiz);

        mockMvc.perform(get(QuizController.QUIZ_MAPPING + "/tests")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.url", is("tests")));


    }


    @Test
    public void givenQuizStaticFile_whenGetWithFilename_thenReturnJsonQuiz() throws Exception {
        mockMvc.perform(get(QuizController.QUIZ_MAPPING + "/static/unicorns.json")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.url", is("http://urbaninstitute.github.io/quick-quiz/")))
            .andExpect(jsonPath("$.title", is("How well do you know real creatures?")))
            .andExpect(jsonPath("$.questions", hasSize(3)))
            .andExpect(jsonPath("$.questions[0].answers", hasSize(4)))
            .andExpect(jsonPath("$.questions[0].answers[0]", is("Loch Ness Monster")))
            .andExpect(jsonPath("$.questions[0].correct.index", is(2)))
            .andExpect(jsonPath("$.questions[0].correct.text", startsWith("The unicorn is a mythical creature")))
            .andExpect(jsonPath("$.questions[0].number",is(1)))
            .andExpect(jsonPath("$.questions[0].prompt",is("Which of the following is the most real?")));
    }

    @Test
    public void givenQuestion_whenPutQuestion_thenReturnThatQuestion() throws Exception {
        Correct c = new Correct();
        c.setIndex(1L);
        c.setText("Milos!");

        Question q = new Question();
        String a1 = "Slavisa";
        String a2 = "Milos";
        List<String> answers = new ArrayList<>();
        answers.add(a1);
        answers.add(a2);
        String image = "Slika";
        Long number = 120L;
        String prompt = "Ko testira kontroler?";
        q.setAnswers(answers);
        q.setCorrect(c);
        q.setImage(image);
        q.setNumber(number);
        q.setPrompt(prompt);

        Quiz quiz = new Quiz();
        List<Question> questions = new ArrayList<>();
        questions.add(q);
        String title = "QuizControllerTests";
        String url = "tests";

        quiz.setQuestions(questions);
        quiz.setTitle(title);
        quiz.setUrl(url);

        Long one = 1L;
        when(quizService.answerQuestion("tests", one, one)).thenReturn(q);

        MockHttpServletRequestBuilder builder =
            post(QuizController.QUIZ_MAPPING + "/tests/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .characterEncoding("UTF-8")
                .content(getQuestionInJson());

        mockMvc.perform(builder)
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.answers[1]", is("Milos")));

    }

    private String getQuestionInJson() {
        return "{\"answers\":[\"Slavisa\",\"Milos\"],\"correct\":{\"index\":1,\"text\":\"Milos!\"},\"image\":\"Slika\",\"number\":120,\"prompt\":\"Ko testira kontroler?\"}";
    }

}
