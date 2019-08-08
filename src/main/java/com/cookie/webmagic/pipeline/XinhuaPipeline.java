package com.cookie.webmagic.pipeline;


import com.cookie.webmagic.dataobject.Xinhuanews;
import com.cookie.webmagic.repository.XinhuaNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @author sky
 * @date 2019/8/5 11:27
 */
@Component
public class XinhuaPipeline implements Pipeline {

    @Autowired
    private XinhuaNewsRepository xinhuaNewsRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {

        List<Xinhuanews> data = resultItems.get("content");

        xinhuaNewsRepository.saveAll(data);
    }
}