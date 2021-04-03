/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.imgflip.sdk.imgflip.sdk;

/**
 * A set of stativ helpers around Imgflip Urls
 * @author 3004SAL
 */
public class ImgFlipURLHelper {
    
    public static final String IMGFLIP_ROOT_URL = "https://imgflip.com/";

    public static String getPagePath(String inStream, int page) {
        String out = IMGFLIP_ROOT_URL;
        if (inStream == null || inStream.isEmpty()) {
            out += "/m/fun";
        } else {
            out += "/m/" + inStream;
        }
        if (page == 0) {
            // page 0 and 1 re the same ones
            out += "?page=1";
        } else {
            out += "?page=" + page;
        }
        return out;
    }
    public static String getPagePath(String inStream) {
        return getPagePath(inStream, null);
    }
    public static String getPagePath() {
        return getPagePath("fun");
    }
    public static String getPagePath(String inStream, String sortOption) {
        String out = "";
        if (inStream == null || inStream.isEmpty()) {
            out += "/m/" + HOTTEST_STREAMS.FUN.url();
        } else {
            out += "/m/" + inStream;
        }
        if (sortOption == null || sortOption.isEmpty()) {
            out += "?sort=latest";
        } else {
            out += "?sort=" + sortOption;
        }
        return out;
    }
}
