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
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

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
        //设置代理池
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                new Proxy("192.168.0.108",8888)
                ,new Proxy("192.168.0.109",8888)));//目前没法用，待分布式部署的时候试试

        Spider.create(new NewsSpider()).addUrl(InternetSite.LIST_API)
//                .setDownloader(httpClientDownloader)
                .addPipeline(xinhuaPipeline).run();
    }

    @Override
    public void saveNews(List<News> newsList) {
        newsRepository.saveAll(newsList);
    }
}
