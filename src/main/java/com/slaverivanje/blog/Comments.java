package com.slaverivanje.blog;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comments {
    @Id
    private String id;
    private String articleId;
    private String text;
    private String author;

    public Comments() {
    }

    public Comments(String id, String articleId, String text, String author) {
        this.id = id;
        this.articleId = articleId;
        this.text = text;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id='" + id + '\'' +
                ", articleId='" + articleId + '\'' +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
