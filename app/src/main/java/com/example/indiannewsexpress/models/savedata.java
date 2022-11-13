package com.example.indiannewsexpress.models;

public class savedata {
    String title,imagelink,newslink,discription,source,author,time;
    public  savedata()
    {

    }
    public savedata(String title, String imagelink, String newslink, String discription, String source, String author, String time) {
        this.title = title;
        this.imagelink = imagelink;
        this.newslink = newslink;
        this.discription = discription;
        this.source = source;
        this.author = author;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getNewslink() {
        return newslink;
    }

    public void setNewslink(String newslink) {
        this.newslink = newslink;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
