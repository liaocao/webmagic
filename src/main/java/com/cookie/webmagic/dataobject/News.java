package com.cookie.webmagic.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author sky
 * @date 2019/8/1 15:54
 */
@Entity
@Data
public class News {

    @Id
    private String newsId;
    private String newsTitle;
    private String newsSummary;
    private String newsKeyword;
    private Date newsTime;
    private String newsUrl;
}
