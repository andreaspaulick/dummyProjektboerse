package com.andreas.dummyProjektboerse.Entity;

import javax.persistence.*;

@Entity
public class Posts implements Comparable<Posts>{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String title;
    private String content;
    private String status;
    private String course;
    private String start;
    private String end;
    private String max_party;
    private String tags;

    public Posts() {
    }

    public Posts(String title, String content, String status, String course, String start, String end, String max_party, String tags) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.course = course;
        this.start = start;
        this.end = end;
        this.max_party = max_party;
        this.tags = tags;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getMax_party() {
        return max_party;
    }

    public void setMax_party(String max_party) {
        this.max_party = max_party;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

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
