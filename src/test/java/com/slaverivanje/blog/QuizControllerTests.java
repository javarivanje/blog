package com.slaverivanje.blog;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.slaverivanje.blog.controller.QuizController;
import com.slaverivanje.blog.domain.Correct;
import com.slaverivanje.blog.domain.Question;
import com.slaverivanje.blog.domain.Quiz;
import com.slaverivanje.blog.service.QuizServiceImpl;
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

@RunWith(SpringRunner.class)
@WebMvcTest(QuizController.class)
public class QuizControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuizServiceImpl quizService;

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

        mockMvc.perform(get( QuizController.QUIZ_MAPPING + "/tests")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url", is("tests")));
                //.andExpect(jsonPath("$.[1].author", is(c2.getAuthor())))
                //.andExpect(jsonPath("$.[2].author", is(c3.getAuthor())));

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
    }
}
