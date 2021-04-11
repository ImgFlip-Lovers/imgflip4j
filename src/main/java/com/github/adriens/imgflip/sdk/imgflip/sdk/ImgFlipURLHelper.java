/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.imgflip.sdk.imgflip.sdk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

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
        return getPagePath(inStream, 1);
    }
    public static String getPagePath() {
        return getPagePath("fun");
    }
    public static String getPagePath(String inStream, String sortOption, int page) {
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
        out += "&page=" + page;
        return out;
    }
    
    //////////////////////////////////////////////////////////////////////
    // Top like queries
    public static String getPathOfTop(String inStream, String sortOption, int page){
        String out = IMGFLIP_ROOT_URL;
        //ex: https://imgflip.com/m/fun?sort=top-1d
        if (inStream == null || inStream.isEmpty()) {
            out += "/m/" + HOTTEST_STREAMS.FUN.url();
        } else {
            out += "/m/" + inStream;
        }
        // now put the sort option
        out += "?sort=" + sortOption;
        out += "&page=" + page;
        return out;
    }
     public static String getPathOfTop(String inStream, String sortOption){
         return getPathOfTop(inStream, sortOption, 1);
     }
    public static String getPathOfTopOneDay(String stream, int page){
        return getPathOfTop(stream, "top-1d", page);
    }
    public static String getPathOfTopOneDay(String stream){
        return getPathOfTop(stream, "top-1d", 1);
    }
    public static String getPathOfTopSevendays(String stream, int page){
        return getPathOfTop(stream, "top-7d", page);
    }
    
    public static String getPathOfTopSevendays(String stream){
        return getPathOfTopSevendays(stream, 1);
    }
    public static String getPathOfTopThirtyDays(String stream, int page){
        return getPathOfTop(stream, "top-30d", page);
    }
    public static String getPathOfTopThirtyDays(String stream){
        return getPathOfTopThirtyDays(stream, 1);
    }
    public static String getPathOfTopLastMonth(String stream, int page){
        //top-2021-03
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        Date lDate = calendar.getTime();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");  
        String sortOpt = "top-" + dateFormat.format(lDate);
        return getPathOfTop(stream, sortOpt,page);
    }
    public static String getPathOfTopLastMonth(String stream){
         return getPathOfTopLastMonth(stream, 1);
    }
    public static String getPathOfTopLastYear(String stream, int page){
        //top-2021-03
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date lDate = calendar.getTime();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy");  
        String sortOpt = "top-" + dateFormat.format(lDate);
        return getPathOfTop(stream, sortOpt, page);
    }
    public static String getPathOfTopLastYear(String stream){
         return getPathOfTopLastYear(stream, 1);
     }
    public static String getPathOfTopOfMonthOfYear(String stream, int year, int month, int page){
        String sortOpt = "top-" + year + "-" + StringUtils.leftPad(month+"", 2, "0");
        return getPathOfTop(stream, sortOpt, page);
    }
    public static String getPathOfTopOfMonthOfYear(String stream, int year, int month){
        return getPathOfTopOfMonthOfYear(stream, year, month, 1);
    }
    public static String getPathOfTopOfYearWithPage(String stream, int year, int page){   
        String sortOpt = "top-" + year;
        return getPathOfTop(stream, sortOpt, page);
    }
    public static String getPathOfTopOfYear(String stream, int year){
        return getPathOfTopOfYearWithPage(stream, year, 1);
    }
    
    public static void main(String[] arg){
        System.out.println("Path Fun for Last Month: <" + ImgFlipURLHelper.getPathOfTopLastMonth("fun")+ ">");
        System.out.println("Path Fun for Last Year: <" + ImgFlipURLHelper.getPathOfTopLastYear("fun")+ ">");
        System.out.println("Path Fun for Februray 2017: <" + ImgFlipURLHelper.getPathOfTopOfMonthOfYear("politics", 2017, 2) + ">");
        System.exit(0);
    }
    
}
