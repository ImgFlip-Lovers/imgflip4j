/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.imgflip.sdk.imgflip.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 3004SAL
 */
public class ImgFlipAPI {

    final static Logger logger = LoggerFactory.getLogger(ImgFlipAPI.class);
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
    
    
    public static Meme getMeme(int memeId) throws JsonProcessingException{
        Meme out = null;
        Map<Integer, Meme> map = getMemes().stream().collect(Collectors.toMap(Meme::getId, meme -> meme));
        out = map.get(memeId);
        if (out == null){
            logger.warn("Not able to find meme with id <" + memeId + "> from the top 100. Will return null.");
            return null;
        }
        else {
            return out;
        }
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
            List<Meme> memes = ImgFlipAPI.getMemes();
            System.out.println("Found <" + memes.size() + "> memes");
            // loop on top 100 most captionned memes
            System.out.println("-- Top 10 most captionned memes --");
            int i = 1;
            for (Meme meme : getTopNMemes(10)) {
                System.out.println(i + ". " + meme);
                i++;
            }
            
            // get a meme from its id
            System.out.println("Got meme from <217743513> : " + ImgFlipAPI.getMeme(217743513));
            
            System.exit(0);
        } catch (JsonProcessingException ex) {
            logger.error("Got an error while processing memes from json answer : " + ex.getMessage());
            System.exit(1);
        }
    }
}
