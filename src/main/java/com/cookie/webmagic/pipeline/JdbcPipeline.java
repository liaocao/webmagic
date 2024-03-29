package com.cookie.webmagic.pipeline;


import com.cookie.webmagic.dataobject.News;
import com.cookie.webmagic.repository.NewsRepository;
import com.cookie.webmagic.service.SpiderService;
import com.cookie.webmagic.util.DateUtil;
import com.cookie.webmagic.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author sky
 * @date 2019/8/5 11:27
 * 针对http://www.xmtnews.com/events布局适配
 */
@Component
public class JdbcPipeline implements Pipeline {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {

        System.out.println("process Thread："+Thread.currentThread().getName());
        List<Selectable> nodes = resultItems.get("content");

        List<News> data = new ArrayList<>();
        for (Selectable node : nodes) {

            Selectable root = node.xpath("div[@class=pic-summary]");
            String newsID = KeyUtil.getUniqueKey();
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
        newsRepository.saveAll(data);
    }
}