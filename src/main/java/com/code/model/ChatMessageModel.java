package com.code.model;

import java.sql.Date;

public class ChatMessageModel {
    private String id;
    private String text;
    private String author;
    private Date date;
 
    public ChatMessageModel() {
    }
 
    public ChatMessageModel(String text, String author, Date date) {
        this.text = text;
        this.author = author;
        this.date = date;
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
 
    public Date getCreateDate() {
        return date;
    }
 
    public void setCreateDate(Date date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ",\"text\":\"" + text + '\"' +
                ",\"author\":\"" + author + '\"' +
                ",\"date\":\"" + date + "\"" +
                '}';
    }
}