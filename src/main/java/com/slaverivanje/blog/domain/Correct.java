
package com.slaverivanje.blog.domain;

public class Correct {

    private Long index;
    private String text;

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Correct{" +
                "index=" + index +
                ", text='" + text + '\'' +
                '}';
    }
}
