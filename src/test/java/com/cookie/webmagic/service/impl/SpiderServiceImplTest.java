package com.cookie.webmagic.service.impl;

import com.cookie.webmagic.dataobject.News;
import com.cookie.webmagic.service.SpiderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sky
 * @date 2019/8/5 17:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderServiceImplTest {

    @Autowired
    private SpiderService service;

    @Test
    public void spiderNews() {
        System.out.println("Thread："+Thread.currentThread().getName());
        service.spiderNews();
    }

    @Test
    public void spiderWisdomNews() {
        service.spiderWisdomNews();
    }
}