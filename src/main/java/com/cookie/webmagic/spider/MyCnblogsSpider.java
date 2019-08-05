package com.cookie.webmagic.spider;

import com.cookie.webmagic.dataobject.News;
import com.cookie.webmagic.pipeline.JdbcPipeline;
import com.cookie.webmagic.util.DateUtil;
import com.cookie.webmagic.util.KeyUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.ElementSelector;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sky
 * @date 2019/8/2 16:12
 */
public class MyCnblogsSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public Site getSite() {
        return site;
    }

    public void process(Page page) {
//        if (!page.getUrl().regex("http://www.cnblogs.com/[a-z 0-9 -]+/p/[0-9]{7}.html").match()) {
//            page.addTargetRequests(
//                    page.getHtml().xpath("//*[@id=\"mainContent\"]/div/div/div[@class=\"postTitle\"]/a/@href").all());
//        } else {
//            page.putField(page.getHtml().xpath("//*[@id=\"cb_post_title_url\"]/text()").toString(),
//                    page.getHtml().xpath("//*[@id=\"cb_post_title_url\"]/@href").toString());
//        }
        page.putField("title", page.getHtml().xpath("//a[@class='headers']/text()"));
        List<String> all = page.getHtml().xpath("//a[@class='headers']/text()").all();
//        for (String a: all) {
//            System.out.println(a);
//        }//需要把这一页中的所有内容整理出来

        List<Selectable> nodes = page.getHtml().xpath("//ul[@class=pic-list]/li").nodes();
//        Elements elements=doc.select("ul[class=pic-list]").select("li");

        List<News> data = new ArrayList<>();
        for (Selectable node : nodes) {

            Selectable root = node.xpath("div[@class=pic-summary]");
            String newsID = KeyUtil.getUniqueKey();
//            String newsUrl = root.select("a").attr("href");
//            String newsTitle = ele.select("div[class=pic-summary]").select("a").attr("title");
//            String newsKeyword = root.select("div[class=tags-box]").select("span[class=keywords]").select("a").text();
//            String newsTime = root.select("div[class=title]").select("div[class=source]").select("time").text();
//            String newsSummary = root.select("p").text();

            String newsUrl = root.xpath("//a/@href").toString();
            String newsTitle = node.xpath("//div[@class=pic-summary]/a/@title").toString();
            String newsKeyword = root.xpath("//div[@class=tags-box]/span[@class=keywords]/a/text()").toString();
            String newsTime = root.xpath("//div[@class=title]/div[@class=source]/time/text()").toString();
            String newsSummary = root.xpath("//p/text()").toString();

            News n = new News();
            n.setNewsId(newsID);
            n.setNewsUrl(newsUrl);
            n.setNewsTitle(newsTitle);
            n.setNewsKeyword(newsKeyword);
            n.setNewsSummary(newsSummary);
            if (newsTime == null) {
                n.setNewsTime(new Date());
            }else {
                n.setNewsTime(DateUtil.strToDateLong(newsTime));
            }
            data.add(n);

        }

        page.putField("content", data);
    }

    public static void main(String[] args) {
        Spider.create(new MyCnblogsSpider()).addUrl("http://www.xmtnews.com/events")
                .addPipeline(new JdbcPipeline()).run();
    }
}
