package com.github.adriens.imgflip.sdk.imgflip.sdk.domain;

import com.github.adriens.imgflip.sdk.imgflip.sdk.domain.enums.RankIcon;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class User {

    private RankIcon rankIcon;

    private String userName;

    private BigDecimal points;

    private Date joinDate;

    private String tagLine;

    private Integer featuredImages;

    private Integer creations;

    private Integer comments;

    private Integer followers;

    private List<Submission> latestSubmissions;

    private List<Template> topUploadedTemplates;

    private List<Stream> streamsModerated;

    private List<Stream> streamsFollowed;

    public RankIcon getRankIcon() {
        return rankIcon;
    }

    public void setRankIcon(RankIcon rankIcon) {
        this.rankIcon = rankIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public Integer getFeaturedImages() {
        return featuredImages;
    }

    public void setFeaturedImages(Integer featuredImages) {
        this.featuredImages = featuredImages;
    }

    public Integer getCreations() {
        return creations;
    }

    public void setCreations(Integer creations) {
        this.creations = creations;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public List<Submission> getLatestSubmissions() {
        return latestSubmissions;
    }

    public void setLatestSubmissions(List<Submission> latestSubmissions) {
        this.latestSubmissions = latestSubmissions;
    }

    public List<Template> getTopUploadedTemplates() {
        return topUploadedTemplates;
    }

    public void setTopUploadedTemplates(List<Template> topUploadedTemplates) {
        this.topUploadedTemplates = topUploadedTemplates;
    }

    public List<Stream> getStreamsModerated() {
        return streamsModerated;
    }

    public void setStreamsModerated(List<Stream> streamsModerated) {
        this.streamsModerated = streamsModerated;
    }

    public List<Stream> getStreamsFollowed() {
        return streamsFollowed;
    }

    public void setStreamsFollowed(List<Stream> streamsFollowed) {
        this.streamsFollowed = streamsFollowed;
    }

    @Override
    public String toString() {
        return "User{" +
                "rankIcon=" + rankIcon +
                ", userName='" + userName + '\'' +
                ", points=" + points +
                ", joinDate=" + joinDate +
                ", tagLine='" + tagLine + '\'' +
                ", featuredImages=" + featuredImages +
                ", creations=" + creations +
                ", comments=" + comments +
                ", followers=" + followers +
                ", latestSubmissions=" + latestSubmissions +
                ", topUploadedTemplates=" + topUploadedTemplates +
                ", streamsModerated=" + streamsModerated +
                ", streamsFollowed=" + streamsFollowed +
                '}';
    }
}
