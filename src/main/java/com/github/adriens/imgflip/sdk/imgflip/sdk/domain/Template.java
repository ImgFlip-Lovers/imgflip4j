package com.github.adriens.imgflip.sdk.imgflip.sdk.domain;

public class Template {

    String url;

    String thumbnail;

    String alt;

    String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Template{" +
                "url='" + url + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", alt='" + alt + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
