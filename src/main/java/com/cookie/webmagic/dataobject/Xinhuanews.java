package com.cookie.webmagic.dataobject;

import com.cookie.webmagic.util.KeyUtil;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author sky
 * @date 2019/8/6 17:24
 */
@Entity
@Data
public class Xinhuanews {

    @Id
    private String newsId = KeyUtil.getUniqueKey();

    private String des = "";

    private String imgUrl = "";
    private String keyword = "";
    private Date pubtime = new Date();

    private String sitename = "";
    private String title = "";
    private String url = "";

}
