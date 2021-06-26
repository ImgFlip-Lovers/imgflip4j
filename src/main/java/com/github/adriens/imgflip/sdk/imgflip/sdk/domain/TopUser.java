package com.github.adriens.imgflip.sdk.imgflip.sdk.domain;

import com.github.adriens.imgflip.sdk.imgflip.sdk.domain.enums.RankIcon;

import java.math.BigDecimal;

public class TopUser {

    private String rank;

    private String user;

    private Integer featuredImgs;

    private Integer creations;

    private BigDecimal points;

    private RankIcon rankIcon;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getFeaturedImgs() {
        return featuredImgs;
    }

    public void setFeaturedImgs(Integer featuredImgs) {
        this.featuredImgs = featuredImgs;
    }

    public Integer getCreations() {
        return creations;
    }

    public void setCreations(Integer creations) {
        this.creations = creations;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public RankIcon getRankIcon() {
        return rankIcon;
    }

    public void setRankIcon(RankIcon rankIcon) {
        this.rankIcon = rankIcon;
    }

    @Override
    public String toString() {
        return "TopUser{" +
                "rank='" + rank + '\'' +
                ", user='" + user + '\'' +
                ", featuredImgs=" + featuredImgs +
                ", creations=" + creations +
                ", points=" + points +
                ", rankIcon=" + rankIcon +
                '}';
    }
}
