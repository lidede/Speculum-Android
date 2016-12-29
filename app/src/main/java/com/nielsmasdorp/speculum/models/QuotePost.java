package com.nielsmasdorp.speculum.models;


public class QuotePost {

    private String title;
    private String author;
    private String ups;

    public QuotePost(String title, String author, String ups) {
        this.title = title;
        this.author = author;
        this.ups = ups;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUps() {
        return ups;
    }
}
