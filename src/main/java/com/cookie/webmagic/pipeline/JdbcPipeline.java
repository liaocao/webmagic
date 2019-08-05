package com.cookie.webmagic.pipeline;


import com.cookie.webmagic.dataobject.News;
import com.cookie.webmagic.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;

/**
 * @author sky
 * @date 2019/8/5 11:27
 */
public class JdbcPipeline implements Pipeline {

    @Autowired
    private NewsRepository repository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        //遍历所有结果，输出到控制台，上面例子中的"author"、"name"、"readme"都是一个key，其结果则是对应的value
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            System.out.println(entry.getKey() + ":\t" + entry.getValue());
        }

        List<News> content = resultItems.get("content");
        repository.saveAll(content);
    }
}