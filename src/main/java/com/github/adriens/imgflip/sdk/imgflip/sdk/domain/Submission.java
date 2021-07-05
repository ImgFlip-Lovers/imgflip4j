package com.github.adriens.imgflip.sdk.imgflip.sdk.domain;

public class Submission {

    String url;

    String thumbnail;

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

    @Override
    public String toString() {
        return "Submissions{" +
                "url='" + url + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
