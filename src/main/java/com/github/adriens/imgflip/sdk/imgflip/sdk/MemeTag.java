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
public class MemeTag {
    private String name;
    private String url;
    
    public MemeTag(){
        
    }
    
    public String toString(){
        String out = "";
        out += "Tag <" + getName() + ">, url : <" + getUrl() + ">";
        return out;
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
    
    
    
}
