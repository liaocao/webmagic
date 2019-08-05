package com.cookie.webmagic.service.impl;

import com.cookie.webmagic.service.SpiderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        service.spiderNews();
    }

    @Test
    public void spiderWisdomNews() {
    }
}