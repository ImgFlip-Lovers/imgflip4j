/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.imgflip.sdk.imgflip.sdk;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.adriens.imgflip.sdk.imgflip.sdk.domain.PopularStream;
import org.apache.xpath.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PopularStreamCrawler {

    final static Logger logger = LoggerFactory.getLogger(PopularStreamCrawler.class);

    public static final String URL = ImgFlipURLHelper.IMGFLIP_ROOT_URL.concat(ImgFlipURLHelper.POPULAR_STREAM_URL);
    public static final String NSFW_PARAM = "?nsfw=1";

    private static WebClient buildWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setDownloadImages(false);
        return webClient;
    }

    public PopularStreamCrawler() {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
    }

    public static List<PopularStream> getPopularStreams() throws IOException {

        // not included nsfw content by default according to imgflip behavior
        return getPopularStreams(false);
    }

    public static List<PopularStream> getPopularStreams(Boolean includeNSFW) throws IOException {
        if (includeNSFW)
            return getPopularStreams();

        return getPopularStreams(URL.concat(NSFW_PARAM)).stream().filter(ps -> !ps.getNSFW()).collect(Collectors.toList());
    }

    public static List<PopularStream> getPopularNSFWStreams() throws IOException {

        return getPopularStreams(true).stream()
                .filter(PopularStream::getNSFW)
                .collect(Collectors.toList());
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
            HtmlElement followCountElement = item.getFirstByXPath(".//div[@class='str-item-follow-count']");
            if (followCountElement != null)
                popularStream.setFollowCount(Integer.parseInt(followCountElement.getTextContent()));

            logger.info("{}", popularStream);
            popularStreams.add(popularStream);
        }

        logger.info("populars streams count : {}", popularStreams.size());
        return popularStreams;
    }


    public static void main(String[] args) throws Exception {

        PopularStreamCrawler.getPopularStreams();

        System.exit(0);
    }
}
