package com.example.news;

import java.io.Serializable;

public class NewsData implements Serializable {
    String imageUrl;
    String source;
    String title;
    String date;
    String url;
    NewsData(String imageUrl,String source,String title,String date,String url){
        this.imageUrl=imageUrl;
        this.source=source;
        this.title=title;
        this.date=date;
        this.url=url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
