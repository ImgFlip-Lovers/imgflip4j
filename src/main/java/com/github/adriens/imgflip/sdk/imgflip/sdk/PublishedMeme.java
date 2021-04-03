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
    private String authorPath;
    private String author;
    private String rawScore;
    private int nbViews;
    private int nbUpvotes;
    private int nbComments;
    private String posterUrl;
    private String mp4Url;
    private String webmUrl;
    private Score score;

    public PublishedMeme(){
        
    }
    
    public String toString(){
        String out = "";
        out += "Meme <" + getTitle() + ">";
        if(getImageUrl()!= null){
            out += ", url: <" + getImageUrl() + ">";
        }
        if(getPosterUrl()!= null){
            out += ", url: <" + getPosterUrl() + ">";
        }
        // append the score
        if(getScore() != null){
            out += ", " + getScore();
        }
        
        
        return out;
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
     * @return the nbViews
     */
    public int getNbViews() {
        return nbViews;
    }

    /**
     * @param nbViews the nbViews to set
     */
    public void setNbViews(int nbViews) {
        this.nbViews = nbViews;
    }

    /**
     * @return the nbUpvotes
     */
    public int getNbUpvotes() {
        return nbUpvotes;
    }

    /**
     * @param nbUpvotes the nbUpvotes to set
     */
    public void setNbUpvotes(int nbUpvotes) {
        this.nbUpvotes = nbUpvotes;
    }

    /**
     * @return the nbComments
     */
    public int getNbComments() {
        return nbComments;
    }

    /**
     * @param nbComments the nbComments to set
     */
    public void setNbComments(int nbComments) {
        this.nbComments = nbComments;
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
