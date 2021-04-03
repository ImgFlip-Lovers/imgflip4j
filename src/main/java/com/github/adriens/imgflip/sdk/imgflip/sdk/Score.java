/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.imgflip.sdk.imgflip.sdk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 3004SAL
 */
public class Score {

    private int nbViews;
    private int nbUpvotes;
    private int nbComments;

    final static Logger logger = LoggerFactory.getLogger(Score.class);
    
    public Score() {

    }

    public Score(String aRawInputScore) {
        ;
        String rawData = aRawInputScore.replace(",", "");
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rawData);
        
        logger.info("Attempting to extract scores from <" + aRawInputScore + ">");
        
        int tmpViews;
        int tmpUpVotes;
        int tmpComments;
        
        if (matcher.find()) {
            tmpViews =  Integer.parseInt(matcher.group());
            logger.info("Found Views : <" + tmpViews + ">");
            setNbViews(tmpViews);
        }
        
        if (matcher.find()) {
            tmpUpVotes =  Integer.parseInt(matcher.group());
            logger.info("Found Upvotes : <" + tmpUpVotes + ">");
            setNbUpvotes(tmpUpVotes);
        }
        
        if (matcher.find()) {
            tmpComments =  Integer.parseInt(matcher.group());
            logger.info("Found  Comments : <" + tmpComments + ">");
            setNbComments(tmpComments);
        } 
    }

    public String toString(){
        String out;
        out = "<" + nbViews + "> view(s), <" + nbUpvotes + "> upVote(s) and <" + nbComments + "> comment(s)";
        return out;
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

}
