
package com.slaverivanje.blog.domain;

import java.util.List;

public class Question {

    private List<String> answers;
    private Correct correct;
    private String image;
    private Long number;
    private String prompt; // short question text

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Correct getCorrect() {
        return correct;
    }

    public void setCorrect(Correct correct) {
        this.correct = correct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
