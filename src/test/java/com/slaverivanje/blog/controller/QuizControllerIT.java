package com.slaverivanje.blog.controller;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QuizControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenQuizStaticFile_whenGetWithFilename_thenReturnJsonQuiz() throws Exception {
        mockMvc.perform(get(QuizController.QUIZ_MAPPING + "/unicorns")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.url", is("http://urbaninstitute.github.io/quick-quiz/")))
            .andExpect(jsonPath("$.title", is("How well do you know real creatures?")))
            .andExpect(jsonPath("$.questions", hasSize(2)))
            .andExpect(jsonPath("$.questions[0].answers", hasSize(4)))
            .andExpect(jsonPath("$.questions[0].answers[0]", is("Loch Ness Monster")))
            .andExpect(jsonPath("$.questions[0].correct.index", is(2)))
            .andExpect(jsonPath("$.questions[0].correct.text", startsWith("The unicorn is a mythical creature")))
            .andExpect(jsonPath("$.questions[0].number",is(1)))
            .andExpect(jsonPath("$.questions[0].prompt",is("Which of the following is the most real?")));
    }

}
