package com.andreas.dummyProjektboerse.Entity;

import javax.persistence.*;

@Entity
public class Posts implements Comparable<Posts>{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    public Posts() {
    }

    public Posts(String title, String content, String status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

    private String title, content, status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Posts posts) {
        //change sides to order the ID ascending/descending on the HTML page:
        return posts.getId()-this.getId();
    }
}
