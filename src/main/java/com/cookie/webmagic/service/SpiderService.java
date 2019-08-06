package com.cookie.webmagic.service;

import com.cookie.webmagic.dataobject.News;

import java.util.List;

/**
 * @author sky
 * @date 2019/8/5 17:19
 */
public interface SpiderService {

    void spiderNews();

    void spiderWisdomNews();

    void saveNews(List<News> newsList);
}
