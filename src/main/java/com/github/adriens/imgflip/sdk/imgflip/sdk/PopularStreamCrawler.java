/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.imgflip.sdk.imgflip.sdk;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.adriens.imgflip.sdk.imgflip.sdk.base.Crawler;
import com.github.adriens.imgflip.sdk.imgflip.sdk.domain.PopularStream;
import org.omg.CORBA.INITIALIZE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PopularStreamCrawler extends Crawler {

    final static Logger logger = LoggerFactory.getLogger(PopularStreamCrawler.class);

    public static final String URL = ImgFlipURLHelper.IMGFLIP_ROOT_URL.concat(ImgFlipURLHelper.POPULAR_STREAM_URL);
    public static final String NSFW_PARAM = "?nsfw=1";
    public static final String INFINITE_CHAR = "∞";


    public static List<PopularStream> getPopularStreams() throws IOException {

        // not included nsfw content by default according to imgflip behavior
        return getPopularStreams(false);
    }

    public static List<PopularStream> getPopularNSFWStreams() throws IOException {

        return getPopularStreams(true).stream().filter(PopularStream::getNSFW).collect(Collectors.toList());
    }

    public static List<PopularStream> getPopularStreams(Boolean includeNSFW) throws IOException {
        List<PopularStream> list = getPopularStreams(URL.concat(NSFW_PARAM));

        if (includeNSFW)
            return list;

        return list.stream().filter(ps -> !ps.getNSFW()).collect(Collectors.toList());
    }

    private static List<PopularStream> getPopularStreams(String url) throws IOException {
        List<PopularStream> popularStreams = new ArrayList<>();

        WebClient webClient = buildWebClient();
        HtmlPage page = webClient.getPage(url);
        HtmlDivision div = page.getFirstByXPath("//div[@class='str-list']");
        List<DomElement> domElements = div.getByXPath("//div[@class='str-item']");

        for (DomElement item : domElements) {
            PopularStream popularStream = new PopularStream();

            // title + url
            HtmlElement titleElement = item.getFirstByXPath(".//a[@class='str-item-link']");
            if (titleElement != null) {
                popularStream.setTitle(titleElement.getTextContent());
                popularStream.setUrl(ImgFlipURLHelper.IMGFLIP_ROOT_URL.concat(titleElement.getAttribute("href")));
            }

            // nsfw flag
            HtmlElement isNSFWElement = item.getFirstByXPath(".//span[@class='str-item-nsfw']");
            popularStream.setNSFW(isNSFWElement != null);

            // description
            HtmlElement descriptionElement = item.getFirstByXPath(".//div[@class='str-item-description']");
            if (descriptionElement != null)
                popularStream.setDescription(descriptionElement.getTextContent());

            // follow count
            popularStream.setFollowCount(getFollowCount(item));

            logger.debug("{}", popularStream);
            popularStreams.add(popularStream);
        }

        logger.debug("populars streams count : {}", popularStreams.size());
        return popularStreams;
    }

    private static Integer getFollowCount(DomElement dom) {
        HtmlElement followCountElement = dom.getFirstByXPath(".//div[@class='str-item-follow-count']");
        if (followCountElement != null) {
            if (INFINITE_CHAR.equals(followCountElement.getTextContent())) {
                logger.warn("Infinite char detected {}, replace it by {}", INFINITE_CHAR, Integer.MAX_VALUE);
                return Integer.MAX_VALUE;
            } else
                return Integer.parseInt(followCountElement.getTextContent());
        }

        return null;
    }


    public static void main(String[] args) throws Exception {

        logger.info("get populars streams (NSFW not included)");
        List<PopularStream> defaultPopularStreams = PopularStreamCrawler.getPopularStreams();
        for (PopularStream popularStream : defaultPopularStreams) {
            logger.info("title : {} {}", popularStream.getTitle(), popularStream.getNSFW() ? "(NSFW)" : "");
        }
        logger.info("Count : {}", defaultPopularStreams.size());

        logger.info("get populars NSFW streams (only)");
        List<PopularStream> nsfwPopularStreams = PopularStreamCrawler.getPopularNSFWStreams();
        for (PopularStream popularStream : nsfwPopularStreams) {
            logger.info("title : {} {}", popularStream.getTitle(), popularStream.getNSFW() ? "(NSFW)" : "");
        }
        logger.info("Count : {}", nsfwPopularStreams.size());

        System.exit(0);
    }
}
