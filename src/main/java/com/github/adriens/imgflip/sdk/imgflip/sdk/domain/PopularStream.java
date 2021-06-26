package com.github.adriens.imgflip.sdk.imgflip.sdk.domain;

public class PopularStream {

    private String title;

    private String description;

    private String url;

    private Boolean isNSFW;

    private Integer followCount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getNSFW() {
        return isNSFW;
    }

    public void setNSFW(Boolean NSFW) {
        isNSFW = NSFW;
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    @Override
    public String toString() {
        return "PopularStream{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", isNSFW=" + isNSFW +
                ", nbFollow=" + followCount +
                '}';
    }
}
