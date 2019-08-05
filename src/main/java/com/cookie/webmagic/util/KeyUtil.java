package com.cookie.webmagic.util;

import java.util.Random;

/**
 * @author sky
 * @date 2019/7/25 16:54
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String getUniqueKey(){//synchronized防止多线程重复
        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;//保证生成的是六位数

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
