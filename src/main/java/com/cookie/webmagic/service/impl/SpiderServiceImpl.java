package com.cookie.webmagic.service.impl;

import com.cookie.webmagic.constant.InternetSite;
import com.cookie.webmagic.dataobject.News;
import com.cookie.webmagic.pipeline.JdbcPipeline;
import com.cookie.webmagic.pipeline.XinhuaPipeline;
import com.cookie.webmagic.repository.NewsRepository;
import com.cookie.webmagic.service.SpiderService;
import com.cookie.webmagic.spider.NewsSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * @author sky
 * @date 2019/8/5 17:21
 */
@Service
public class SpiderServiceImpl implements SpiderService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private JdbcPipeline jdbcPipeline;

    @Autowired
    private XinhuaPipeline xinhuaPipeline;

    @Override
    public void spiderNews() {
        Spider.create(new NewsSpider()).addUrl("http://www.xmtnews.com/events")
                .addPipeline(jdbcPipeline).run();
    }

    @Override
    public void spiderWisdomNews() {
//        Spider.create(new NewsSpider()).addUrl("http://so.news.cn/#search/0/智慧城市/1/")
        Spider.create(new NewsSpider()).addUrl(InternetSite.LIST_API)
                .addPipeline(xinhuaPipeline).run();
    }

    @Override
    public void saveNews(List<News> newsList) {
        newsRepository.saveAll(newsList);
    }
}
