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

// some of the hottest streams
public enum HOTTEST_STREAMS {
    FUN("fun"),
    POLITICS("politics"),
    GAMING("gaming"),
    REPOST("repost"),
    CATS("cats"),
    SPORTS("sports"),
    REACTIONGIFS("reactiongifs"),
    TOURNAMENT("meme-tournament-1"),
    NONE("fun");

    private String url;

    HOTTEST_STREAMS(String channel) {
        this.url = url;
    }

    public String url() {
        return url;
    }

}
