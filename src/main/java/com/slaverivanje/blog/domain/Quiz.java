
package com.slaverivanje.blog.domain;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private List<Question> questions;
    private String title;
    private String url;

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Question> getQuestions(Long questionNumber) {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "questions=" + questions +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public static void main (String [] args){
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
        System.out.println(quiz);
    }
}
