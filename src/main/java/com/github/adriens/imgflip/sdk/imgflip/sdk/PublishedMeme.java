/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.imgflip.sdk.imgflip.sdk;

/**
 *
 * @author 3004SAL
 */
public class PublishedMeme {

    private String title;
    private String imageUrl;
    private String memeUrl;
    private String authorPath;
    private String author;
    private String rawScore;
    private String posterUrl;
    private String mp4Url;
    private String webmUrl;
    private Score score;

    public PublishedMeme(){
        
    }

    @Override
    public String toString() {
        return "PublishedMeme{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", memeUrl='" + memeUrl + '\'' +
                ", authorPath='" + authorPath + '\'' +
                ", author='" + author + '\'' +
                ", rawScore='" + rawScore + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", mp4Url='" + mp4Url + '\'' +
                ", webmUrl='" + webmUrl + '\'' +
                ", score=" + score +
                '}';
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getMemeUrl(){
        return memeUrl;
    }
    public void setMemeUrl(String memeUrl){
        this.memeUrl = memeUrl;
    }
    

    /**
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return the authorPath
     */
    public String getAuthorPath() {
        return authorPath;
    }

    /**
     * @param authorPath the authorPath to set
     */
    public void setAuthorPath(String authorPath) {
        this.authorPath = authorPath;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the rawScore
     */
    public String getRawScore() {
        return rawScore;
    }

    /**
     * @param rawScore the rawScore to set
     */
    public void setRawScore(String rawScore) {
        this.rawScore = rawScore;
    }

    /**
     * @return the posterUrl
     */
    public String getPosterUrl() {
        return posterUrl;
    }

    /**
     * @param posterUrl the posterUrl to set
     */
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    /**
     * @return the mp4Url
     */
    public String getMp4Url() {
        return mp4Url;
    }

    /**
     * @param mp4Url the mp4Url to set
     */
    public void setMp4Url(String mp4Url) {
        this.mp4Url = mp4Url;
    }

    /**
     * @return the webmUrl
     */
    public String getWebmUrl() {
        return webmUrl;
    }

    /**
     * @param webmUrl the webmUrl to set
     */
    public void setWebmUrl(String webmUrl) {
        this.webmUrl = webmUrl;
    }

    /**
     * @return the score
     */
    public Score getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Score score) {
        this.score = score;
    }
    
    
    
    
}
