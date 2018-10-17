
package com.slaverivanje.blog.domain;

import java.util.List;

public class Quiz {

    private List<Question> questions;
    private String title;
    private String url;

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

}
