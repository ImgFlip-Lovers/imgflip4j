/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.imgflip.sdk.imgflip.sdk;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.Html;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.adriens.imgflip.sdk.imgflip.sdk.base.Crawler;
import com.github.adriens.imgflip.sdk.imgflip.sdk.domain.Stream;
import com.github.adriens.imgflip.sdk.imgflip.sdk.domain.Submission;
import com.github.adriens.imgflip.sdk.imgflip.sdk.domain.Template;
import com.github.adriens.imgflip.sdk.imgflip.sdk.domain.User;
import com.github.adriens.imgflip.sdk.imgflip.sdk.domain.enums.RankIcon;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserCrawler extends Crawler {

    final static Logger logger = LoggerFactory.getLogger(UserCrawler.class);

    public static final String URL = ImgFlipURLHelper.IMGFLIP_ROOT_URL.concat(ImgFlipURLHelper.USER_URL);

    public static String getUserUrl(String userName) {
        final String userUrl = StringUtils.join(Arrays.asList(URL, userName), "/");

        logger.debug("url for user {} : {}", userName, userUrl);
        return StringUtils.join(Arrays.asList(URL, userName), "/");
    }

    public static User getUser(String userName) throws IOException {
        return getUser(URL, userName);
    }

    private static User getUser(String url, String userName) throws IOException {
        User user = new User();

        WebClient webClient = buildWebClient();
        HtmlPage page = webClient.getPage(String.format("%s/%s", url, userName));

        // general
        DomElement titleElements = page.getElementById("user-title");
        user.setRankIcon(getRankIcon(titleElements));
        user.setUserName(getUsername(titleElements));
        user.setPoints(getPoints(titleElements));

        // joined date
        HtmlElement joinElement = page.getHtmlElementById("user-joined");
        user.setJoinDate(getJoinedDate(joinElement));

        // tag line
        HtmlElement tagLineElement = page.getHtmlElementById("user-tagline");
        user.setTagLine(getTagLine(tagLineElement));

        // stats
        DomElement statsElements = page.getFirstByXPath(".//div[@class='user-stats']");
        setStats(statsElements, user);

        String mediasPath = ".//div[@class='user-imgs-wrap'][%s]/div[@class='user-imgs']/a";

        // submissions & templates
        List<HtmlElement> submissionsElements = page.getByXPath(String.format(mediasPath, 1));
        user.setLatestSubmissions(getSubmissions(submissionsElements));

        List<HtmlElement> templatesElements = page.getByXPath(String.format(mediasPath, 2));
        user.setTopUploadedTemplates(getTemplates(templatesElements));

        // streams moderated & followed
        List<DomElement> streamsModeratedElements = page.getByXPath("//*[@id='user-streams']/div");
        user.setStreamsModerated(getStreams(streamsModeratedElements));

        List<DomElement> streamsFollowedElements = page.getByXPath("//*[@id='user-streams-followed']/div");
        user.setStreamsFollowed(getStreams(streamsFollowedElements));

        logger.debug("{}", user);
        return user;
    }

    private static RankIcon getRankIcon(DomElement dom) {
        HtmlElement rankIconElement = dom.getFirstByXPath(".//div");
        if (rankIconElement != null) {
            String cleanId = rankIconElement.getAttribute("class").replace("ico ", "");
            return RankIcon.urlForId(cleanId);
        }

        return null;
    }

    private static String getUsername(DomElement dom) {
        HtmlElement userNameElement = dom.getFirstByXPath(".//span[@class='u-username']");
        if (userNameElement != null)
            return userNameElement.getTextContent();

        return null;
    }

    private static BigDecimal getPoints(DomElement dom) {
        HtmlElement pointsElement = dom.getFirstByXPath(".//span[@id='user-points']");
        if (pointsElement != null)
            return new BigDecimal(pointsElement.getTextContent()
                    .replace("(", "")
                    .replace(")", ""));

        return null;
    }

    private static Date getJoinedDate(HtmlElement html) {
        if (html != null) {
            String cleanDate = html.getTextContent().replace("Joined ", "");
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(cleanDate);
            } catch (ParseException e) {
                logger.error("Error parsing joinedDate : {}", cleanDate);
            }
        }

        return null;
    }

    private static String getTagLine(HtmlElement html) {
        if (html != null)
            return html.getTextContent();

        return null;
    }

    private static void setStats(DomElement dom, User user) {

        // get sub-elements by index position
        user.setFeaturedImages(getStat(dom, 1, " Featured Images"));
        user.setCreations(getStat(dom, 2, " Creations"));
        user.setComments(getStat(dom, 3, " Comments"));
        user.setFollowers(getStat(dom, 4, " Followers"));
    }

    private static Integer getStat(DomElement dom, Integer position, String removeText) {
        final String path = String.format(".//div[@class='user-stat'][%s]", position);

        HtmlElement html = dom.getFirstByXPath(path);
        if (html != null) {
            String content = html.getTextContent().replace(removeText, "");
            return Integer.valueOf(content);
        }

        return null;
    }

    private static List<Submission> getSubmissions(List<HtmlElement> htmls) {
        List<Submission> submissions = new ArrayList<>();

        for (HtmlElement html : htmls) {
            Submission submission = new Submission();

            HtmlElement img = html.getFirstByXPath(".//img");
            if (img != null)
                submission.setThumbnail(ImgFlipURLHelper.addHttpsProtocolIfMissing(img.getAttribute("src")));

            submission.setUrl(ImgFlipURLHelper.IMGFLIP_ROOT_URL.concat(html.getAttribute("href")));
            submissions.add(submission);
        }

        return submissions;
    }

    private static List<Template> getTemplates(List<HtmlElement> htmls) {
        List<Template> templates = new ArrayList<>();

        for (HtmlElement html : htmls) {
            Template template = new Template();

            HtmlElement img = html.getFirstByXPath(".//img");
            if (img != null) {
                template.setThumbnail(ImgFlipURLHelper.addHttpsProtocolIfMissing(img.getAttribute("src")));
                template.setAlt(img.getAttribute("alt"));
                template.setTitle(img.getAttribute("title"));
            }

            template.setUrl(ImgFlipURLHelper.IMGFLIP_ROOT_URL.concat(html.getAttribute("href")));
            templates.add(template);
        }

        return templates;
    }

    private static List<Stream> getStreams(List<DomElement> doms) {
        List<Stream> streams = new ArrayList<>();

        for (DomElement dom : doms) {
            Stream stream = new Stream();

            HtmlElement a = dom.getFirstByXPath(".//a");
            if (a != null) {
                stream.setTitle(a.getTextContent());
                stream.setUrl(ImgFlipURLHelper.IMGFLIP_ROOT_URL.concat(a.getAttribute("href")));
                streams.add(stream);
            }

        }

        return streams;
    }

    public static void main(String[] args) throws Exception {
        final String userName = "SCP-1908_Mr.Soap";

        String url = getUserUrl(userName);
        User user = getUser(userName);

        logger.info("user {} ({}) : {}", userName, url, user);

        System.exit(0);
    }
}
