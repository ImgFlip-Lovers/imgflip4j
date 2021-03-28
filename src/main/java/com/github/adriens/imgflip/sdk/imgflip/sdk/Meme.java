/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.imgflip.sdk.imgflip.sdk;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URL;

/**
 *
 * @author 3004SAL
 */
public class Meme {
private long id;
    private String name;
    private String url;
    private int width;
    private int height;
    @JsonProperty("box_count") private int boxCount;
    
    public Meme(){
        
    }
    
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the boxCount
     */
    @JsonGetter("box_count")
    public int getBoxCount() {
        return boxCount;
    }

    /**
     * @param boxCount the boxCount to set
     */
    public void setBoxCount(int boxCount) {
        this.boxCount = boxCount;
    }
    
    public String toString(){
        String out = "";
        out = "<" + getName() + ">, see <" + getUrl() + ">";
        return out;
    }
}
