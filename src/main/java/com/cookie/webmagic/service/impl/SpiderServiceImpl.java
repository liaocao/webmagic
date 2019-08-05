package com.cookie.webmagic.service.impl;

import com.cookie.webmagic.pipeline.JdbcPipeline;
import com.cookie.webmagic.repository.NewsRepository;
import com.cookie.webmagic.service.SpiderService;
import com.cookie.webmagic.spider.MyCnblogsSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

/**
 * @author sky
 * @date 2019/8/5 17:21
 */
@Service
public class SpiderServiceImpl implements SpiderService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public void spiderNews() {
        System.out.println("run~~~~~~");
        Spider.create(new MyCnblogsSpider()).addUrl("http://www.xmtnews.com/events")
                .addPipeline(new JdbcPipeline()).run();
    }

    @Override
    public void spiderWisdomNews() {

    }
}
