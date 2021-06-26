/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.imgflip.sdk.imgflip.sdk;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.adriens.imgflip.sdk.imgflip.sdk.base.Crawler;
import com.github.adriens.imgflip.sdk.imgflip.sdk.domain.TopUser;
import com.github.adriens.imgflip.sdk.imgflip.sdk.domain.enums.RankIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TopUserCrawler extends Crawler {

    final static Logger logger = LoggerFactory.getLogger(TopUserCrawler.class);

    public static final String URL = ImgFlipURLHelper.IMGFLIP_ROOT_URL.concat(ImgFlipURLHelper.TOP_USERS_URL);

    public static List<TopUser> getTopUsers() throws IOException {
        return getTopUsers(URL);
    }

    private static List<TopUser> getTopUsers(String url) throws IOException {
        List<TopUser> topUsers = new ArrayList<>();

        WebClient webClient = buildWebClient();
        HtmlPage page = webClient.getPage(url);

        List<DomElement> rows = page.getByXPath("//*[@id='user-page']/a");

        for (DomElement row : rows) {
            TopUser topUser = new TopUser();

            HtmlElement rankElement = row.getFirstByXPath(".//div[@class='tu-rank']");
            if (rankElement != null)
                topUser.setRank(rankElement.getTextContent());

            HtmlElement userNameElement = row.getFirstByXPath(".//span[@class='u-username']");
            if (userNameElement != null)
                topUser.setUser(userNameElement.getTextContent());

            HtmlElement rankIconElement = row.getFirstByXPath(".//div[@class='tu-user']/div");
            if (rankIconElement != null) {
                String cleanId = rankIconElement.getAttribute("class").replace("ico ", "");
                RankIcon rankIcon = RankIcon.urlForId(cleanId);
                topUser.setRankIcon(rankIcon);
            }

            setInfos(row, topUser);

            logger.debug("{}", topUser);
            topUsers.add(topUser);
        }

        logger.debug("top users count : {}", topUsers.size());
        return topUsers;
    }


    /**
     * Set infos : {@link TopUser#setPoints(BigDecimal) points}, {@link TopUser#setCreations(Integer) creations} and {@link TopUser#setFeaturedImgs(Integer) featuredImgs}
     * of {@link TopUser user} from the {@link DomElement}
     * @param dom source
     * @param user user to set infos
     */
    private static void setInfos(DomElement dom, TopUser user) {
        String infoXpath = ".//div[@class='tu-info'][%s]";

        HtmlElement pointsElement = dom.getFirstByXPath(String.format(infoXpath, 1));
        if (pointsElement != null)
            user.setPoints(new BigDecimal(pointsElement.getTextContent()));

        HtmlElement creationsElement = dom.getFirstByXPath(String.format(infoXpath, 2));
        if (creationsElement != null)
            user.setCreations(Integer.valueOf(creationsElement.getTextContent()));

        HtmlElement featuredImgsElement = dom.getFirstByXPath(String.format(infoXpath, 3));
        if (featuredImgsElement != null)
            user.setFeaturedImgs(Integer.valueOf(featuredImgsElement.getTextContent()));

    }

    public static void main(String[] args) throws Exception {

        logger.info("get top users");
        List<TopUser> users = TopUserCrawler.getTopUsers();
        for (TopUser user : users) {
            logger.info("user : {} {}", user.getRank(), user.getUser());
        }
        logger.info("Count : {}", users.size());

        System.exit(0);
    }
}
