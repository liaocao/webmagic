package com.cookie.webmagic.repository;

import com.cookie.webmagic.dataobject.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sky
 * @date 2019/8/1 10:41
 */
public interface NewsRepository extends JpaRepository<News, String> {

}
