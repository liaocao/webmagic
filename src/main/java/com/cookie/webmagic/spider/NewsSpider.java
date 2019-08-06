package com.cookie.webmagic.spider;

import com.cookie.webmagic.dataobject.XinhuaNews;
import com.google.gson.Gson;
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
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
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

        page.putField("content", nodes);
    }

    private void analysisXinhua(Page page){

//        if (page.getUrl().regex(InternetSite.LIST_API).match()) {
//            List<String> ids = new JsonPathSelector("$.content.results[*].url").selectList(page.getRawText());
//        List<XinhuaNews> result = new JsonPathSelector("$.content.results[*]").selectList(page.getRawText());

        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject(page.getRawText());

        String results = jsonObject.getJSONObject("content").getString("results");
        gson.fromJson(results, new TypeToken<List<XinhuaNews>>() {}.getType());
        System.out.println("测试");
//        } else {
//            page.putField("title", new JsonPathSelector("$.data.title").select(page.getRawText()));
//            page.putField("content", new JsonPathSelector("$.data.content").select(page.getRawText()));
//        }
    }
}
