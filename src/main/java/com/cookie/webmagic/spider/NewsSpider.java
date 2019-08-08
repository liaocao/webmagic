package com.cookie.webmagic.spider;

import com.cookie.webmagic.constant.InternetSite;
import com.cookie.webmagic.convert.NullStringToEmptyAdapterFactory;
import com.cookie.webmagic.dataobject.Xinhuanews;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author sky
 * @date 2019/8/2 16:12
 */
public class NewsSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
//            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setCharset("UTF-8");;

    public Site getSite() {
        return site;
    }

    public void process(Page page) {
        //进来的时候还可以做分支，如果匹配到网址，就添加到target继续爬，否则添加到field
//        analysisXmt(page);
        analysisXinhua(page);
    }

    private void analysisXmt(Page page){
        //针对http://www.xmtnews.com/events布局适配
        List<Selectable> nodes = page.getHtml().xpath("//section[@class=ov]/ul[@class=pic-list]/li").nodes();
        System.out.println("NewsSpider Thread："+Thread.currentThread().getName());
        page.putField("content", nodes);
    }

    private void analysisXinhua(Page page){

        try {//每次进来之前先延迟一段时间，避免被识别为爬虫程序
            Thread.sleep((long) Math.random() * 10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Gson gson  = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();

        JSONObject jsonObject = new JSONObject(page.getRawText());

        String results = jsonObject.getJSONObject("content").getString("results");
        int pageCount = jsonObject.getJSONObject("content").getInt("pageCount");
        int curPage = jsonObject.getJSONObject("content").getInt("curPage");
        if(curPage < 20){
            curPage++;
            page.addTargetRequest(InternetSite.ACTIVE_LIST_API+curPage);
        }
        System.out.println("pageCount："+pageCount);
        System.out.println("curPage："+curPage);
//        results = JsonUtil.delRepeatIndexid(results);
        List<Xinhuanews> xinhuaNewsList = gson.fromJson(results, new TypeToken<List<Xinhuanews>>() {}.getType());

        page.putField("content", xinhuaNewsList);

    }
}
