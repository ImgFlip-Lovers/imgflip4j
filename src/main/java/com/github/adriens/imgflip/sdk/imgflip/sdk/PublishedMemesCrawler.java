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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 3004SAL
 */
public class PublishedMemesCrawler {

    final static Logger logger = LoggerFactory.getLogger(PublishedMemesCrawler.class);

    private static WebClient buildWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setDownloadImages(false);
        return webClient;
    }

    public PublishedMemesCrawler() {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
    }

// Page url composition utilities
    // Crawling methods
    public static List<PublishedMeme> getPublishedMemes()
            throws IOException {
        return getPublishedMemes(1);
    }

    public static List<PublishedMeme> getPublishedMemes(int page)
            throws IOException {
        return getPublishedMemes("fun", page);
    }

    public static List<PublishedMeme> getPublishedMemes(String stream, int page)
            throws IOException {
        String lStream;
        if(stream == null || stream.isEmpty()){
            lStream = "fun";
        }
        String imgFlipUrl = ImgFlipURLHelper.getPagePath(stream, page);
        return getPublishedMemes(imgFlipUrl);
    }

    public static List<PublishedMeme> getPublishedMemes(String targetUrl)
            throws IOException {
        ArrayList<PublishedMeme> out = new ArrayList<>();
        WebClient webClient = buildWebClient();

        logger.info("About to fetch Published Memes from : <" + targetUrl + ">");
        HtmlPage htmlPage = webClient.getPage(targetUrl);
        HtmlDivision element = (HtmlDivision) htmlPage.getHtmlElementById("base-left");
        //DomNodeList<HtmlElement> list = element.getElementsByAttribute("div", "class", "base-unit clearfix");
        List<DomElement> divMemesList = element.getByXPath("//div[@class='base-unit clearfix']");
        logger.info("Found <" + divMemesList.size() + "> published memes.");
        // iterate over published memes
        Iterator<DomElement> memeDivsIterator = divMemesList.iterator();
        int i = 1;
        DomElement lDivision;
        //String imgPath;
        String imgTitle;
        String imgTitleAlt;
        //HtmlElement lHref;
        String rawXml;
        String lMemeTitle;
        HtmlElement lTitleElement;
        String lTargetMemeUrl;

        logger.info("-------------------------------------------------------------");
        while (memeDivsIterator.hasNext()) {
            PublishedMeme lMeme = new PublishedMeme();

            lDivision = memeDivsIterator.next();
            logger.info("Working on meme <" + i + ">...");
            logger.info(lDivision.getTextContent().trim());

            // Get the meme title
            lTitleElement = lDivision.getFirstByXPath(".//h2[@class='base-unit-title']/a");
            if (lTitleElement != null) {
                lMemeTitle = lTitleElement.getTextContent();
                // get the meme url ref
                lTargetMemeUrl = lTitleElement.getAttribute("href");
                lTargetMemeUrl = ImgFlipURLHelper.IMGFLIP_ROOT_URL + lTargetMemeUrl;
                lMeme.setMemeUrl(lTargetMemeUrl);
                logger.info("Found meme target url : <" + lTargetMemeUrl + ">");
                
            } else {
                lMemeTitle = "";
            }
            logger.info("Meme title : <" + lMemeTitle + ">");
            lMeme.setTitle(lMemeTitle);

            //lHref = lDivision.getFirstByXPath(".//div[@class='base-img-wrap-wrap']/div[@class='base-img-wrap']/a");
            //lHref = lDivision.getFirstByXPath(".//h2[1]/a");
            //HtmlElement lHref = lDivision.getFirstByXPath("//h2[@class='base-unit-title']/a");
            //HtmlElement lHref = lDivision.getFirstByXPath("/html/body/div[2]/div[3]/div[6]/h2/a");
            //logger.info("href : <" + lHref + ">");
            //imgPath = lHref.getAttribute("href");
            //imgTitleAlt = lHref.getAttribute("alt");
            //logger.info(lHref.asXml());
            //logger.info("Found href : <" + imgPath + ">");
            //logger.info("Found title Alt : <" + imgTitleAlt + ">");

            // get <img> now
            HtmlElement img = lDivision.getFirstByXPath(".//div[@class='base-img-wrap-wrap']/div[@class='base-img-wrap']/a[@class='base-img-link']/img");
            //HtmlElement img = lDivision.getFirstByXPath(".//div[1]/div/a/img");
            if (img != null) {
                String imageSrc = img.getAttribute("src");
                logger.info("Image source : <" + imageSrc + ">");
                lMeme.setImageUrl(imageSrc);
            }
            // try alternate img source
            HtmlElement img2 = lDivision.getFirstByXPath(".//div[@class='base-img-wrap-wrap']/div[@class='base-img-wrap']/a[@class='base-img-link']/div");
            if (img2 != null) {
                String imageSrc2 = img2.getAttribute("data-src");
                logger.info("Image source : <" + imageSrc2 + ">");
                lMeme.setImageUrl(imageSrc2);
            }

            // get video
            HtmlElement video = lDivision.getFirstByXPath(".//div[@class='base-img-wrap-wrap']/div[@class='base-img-wrap']/a[@class='base-img-link']/video[@class='base-img ctx-gif']");
            if (video != null) {
                String videoSrc = video.getAttribute("data-src");

                String posterSrc = video.getAttribute("poster");
                logger.info("Poster : <" + posterSrc + ">");
                lMeme.setPosterUrl(posterSrc);
                // get mp4 url
                HtmlElement mp4Element = lDivision.getFirstByXPath(".//div[@class='base-img-wrap-wrap']/div[@class='base-img-wrap']/a[@class='base-img-link']/video[@class='base-img ctx-gif']/source[@type='video/mp4']");
                if (mp4Element != null) {
                    String mp4Url = mp4Element.getAttribute("src");
                    logger.info("mp4 url : <" + mp4Url + ">");
                    lMeme.setMp4Url(mp4Url);
                }

                // get webm url
                HtmlElement webmElement = lDivision.getFirstByXPath(".//div[@class='base-img-wrap-wrap']/div[@class='base-img-wrap']/a[@class='base-img-link']/video[@class='base-img ctx-gif']/source[@type='video/webm']");
                if (webmElement != null) {
                    String webmUrl = webmElement.getAttribute("src");
                    logger.info("webm url : <" + webmUrl + ">");
                    lMeme.setWebmUrl(webmUrl);
                }

            }
            // get author
            HtmlElement authorElement = lDivision.getFirstByXPath(".//div[@class='base-info']/div[@class='base-author']/a[@class='u-username']");
            if (authorElement != null) {
                String authorUrl = authorElement.getAttribute("href");
                String author = authorElement.getTextContent();
                logger.info("Author url : <" + authorUrl + ">");
                lMeme.setAuthorPath(authorUrl);
                logger.info("Author : <" + author + ">");
                lMeme.setAuthor(author);
            }

            // get counters
            HtmlElement scoreElement = lDivision.getFirstByXPath(".//div[@class='base-info']/div[@class='base-view-count']");

            if (scoreElement != null) {
                logger.info("Filling meme score datas...");
                String rawsScore = scoreElement.getTextContent();
                Score lScore = new Score(rawsScore);
                lMeme.setScore(lScore);
            } else {
                logger.warn("No score data available.");
            }

            out.add(lMeme);
            i++;
            logger.info("-------------------------------------------------------------");
        }

        return out;
    }

    public static int getNbPublishedMemeInPage(String imgFlipUrl) {
        WebClient webClient = buildWebClient();
        int out;
        logger.info("About to count Published Memes from : <" + imgFlipUrl + ">");

        try {
            HtmlPage htmlPage = webClient.getPage(imgFlipUrl);
            HtmlDivision element = (HtmlDivision) htmlPage.getHtmlElementById("base-left");
            //DomNodeList<HtmlElement> list = element.getElementsByAttribute("div", "class", "base-unit clearfix");
            List<DomElement> divMemesList = element.getByXPath("//div[@class='base-unit clearfix']");
            out = divMemesList.size();
            logger.info("Found <" + out + "> published memes.");
            return out;
        } catch (IOException ex) {
            logger.error("Could not count memes in <" + imgFlipUrl + ">, return 0.");
            return 0;
        }

    }

    // get the next page after the current one
    public static String getNextPageUrl(String anyImgflipUrl) throws IOException {
        String out = "";
        WebClient webClient = buildWebClient();
        logger.info("About to fetch Published Memes from : <" + anyImgflipUrl + ">");
        HtmlPage htmlPage = webClient.getPage(anyImgflipUrl);
        HtmlDivision element = (HtmlDivision) htmlPage.getElementById("base-left");
        HtmlElement nextButtn = element.getFirstByXPath("//div[@class='pager']/a[@class='pager-next l but']");
        if (nextButtn == null) {
            logger.warn("No next page for <" + anyImgflipUrl + ">. return null.");
            return null;
        }
        out = nextButtn.getAttribute("href");
        out = ImgFlipURLHelper.IMGFLIP_ROOT_URL + out;
        logger.info("Next page for <" + anyImgflipUrl + "> is : <" + out + ">");
        return out;
    }

    public static List<MemeTag> getTagsOfMemeFromurl(String aMemeUrl) throws IOException{
        WebClient webClient = buildWebClient();
        //int out;
        logger.info("About to count get tags of Memes from : <" + aMemeUrl + ">");
        String tagName;
        String tagUrl;
        List<MemeTag> out = new ArrayList<MemeTag>();
        try{
            
            HtmlPage htmlPage = webClient.getPage(aMemeUrl);
            HtmlDivision element = (HtmlDivision) htmlPage.getFirstByXPath("//div[@class='img-tags']");
            List<DomElement> divTagList = element.getByXPath(".//a");
            int nbTags = divTagList.size();
            logger.info("Found <" + nbTags + "> tags on <" + aMemeUrl + ">");
            for(int i= 0; i < divTagList.size() ; i++){
                MemeTag lTag = new MemeTag();
                tagUrl = divTagList.get(i).getAttribute("href");
                tagName = divTagList.get(i).asNormalizedText();
                lTag.setUrl(tagUrl);
                lTag.setName(tagName);
                logger.info((i+1) + ". Found tag : " + lTag);
                out.add(lTag);
            }
            return out;
        }
        catch(IOException ex){
            throw ex;
        }
    }
    public static void main(String[] args) throws Exception {
        //List<PublishedMeme> memes = PublishedMemesCrawler.getPublishedMemes();
        //PublishedmemesCrawler.getNextPageUrl("https://imgflip.com/";//m/fun?sort=latest&after=53z8gv");
        //List<PublishedMeme> memes = PublishedMemesCrawler.getPublishedMemes("fun", 1);
        /*int i = 1;
        for (PublishedMeme aMeme : memes) {
            System.out.println(i + ". " + aMeme);
            i++;
        }*/
        PublishedMemesCrawler.getTagsOfMemeFromurl("https://imgflip.com/i/54vin9");
        System.exit(0);
    }
}
