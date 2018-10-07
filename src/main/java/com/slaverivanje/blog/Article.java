package com.slaverivanje.blog;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Article {

    @Id
    private String id;

    private String title;

    private String link;

    private String summary;

    private String body;

    private Date createdDate = new Date();

    public Article() {
    }

    public Article(String id, String title, String link, String summary, String body, Date createdDate) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.summary = summary;
        this.body = body;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", summary='" + summary + '\'' +
                ", body='" + body + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
