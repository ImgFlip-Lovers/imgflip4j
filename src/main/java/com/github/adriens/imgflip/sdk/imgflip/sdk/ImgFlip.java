/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.imgflip.sdk.imgflip.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 3004SAL
 */
public class ImgFlip {

    final static Logger logger = LoggerFactory.getLogger(ImgFlip.class);
    public static final String IMGFLIP_API_ROOT_URK = "https://api.imgflip.com/";

    /* Returned 100 memes ordered by how many times they were captioned
    in the last 30 days.*/
    public static List<Meme> getMemes() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        logger.info("Querying imgflip API...");
        String inputData = Unirest.get(IMGFLIP_API_ROOT_URK + "get_memes")
                .asString()
                .getBody();
        logger.info("Mapping json response to Meme objects...");
        RawData raw = mapper.readValue(inputData, RawData.class);
        logger.info("Returning Memes as list of objects.");
        return raw.getData().getMemes();
    }

    public static List<Meme> getTopNMemes(int n) throws JsonProcessingException {
        int m = n;
        if (m > 100) {
            logger.warn("n canot exceed 100, will only return top 100 memes.");
            m = 100;
        }
        List<Meme> topNMemes = getMemes().stream().limit(m).collect(Collectors.toList());
        return topNMemes;
    }

    public static void main(String[] args) {
        try {
            List<Meme> memes = ImgFlip.getMemes();
            System.out.println("Found <" + memes.size() + "> memes");
            // loop on top 100 most captionned memes
            System.out.println("-- Top 10 most captionned memes --");
            int i = 1;
            for (Meme meme : getTopNMemes(10)) {
                System.out.println(i + ". " + meme);
                i++;
            }
            System.exit(0);
        } catch (JsonProcessingException ex) {
            logger.error("Got an error while processing memes from json answer : " + ex.getMessage());
            System.exit(1);
        }
    }
}
