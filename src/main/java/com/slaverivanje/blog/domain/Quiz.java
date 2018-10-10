
package com.slaverivanje.blog.domain;

import java.util.List;

public class Quiz {

    private List<Quiz> questions;
    private String title;
    private String url;

    public List<Quiz> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Quiz> questions) {
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
