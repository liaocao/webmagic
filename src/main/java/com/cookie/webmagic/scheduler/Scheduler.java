package com.cookie.webmagic.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sky
 * @date 2019/8/8 14:11
 * @Scheduled(fixedRate=3000)：上一次开始执行时间点后3秒再次执行；
 * @Scheduled(fixedDelay=3000)：上一次执行完毕时间点3秒再次执行；
 * @Scheduled(initialDelay=1000, fixedDelay=3000)：第一次延迟1秒执行，然后在上一次执行完毕时间点3秒再次执行；
 * @Scheduled(cron="* * * * * ?")：按cron规则执行；
 */
@Component
public class Scheduler {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //每隔2秒执行一次
//    @Scheduled(fixedRate = 2000)
//    public void testTasks() {
//        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
//    }

    //每天3：05执行
//    @Scheduled(cron = "0 05 03 ? * *")
//    public void testTasks() {
//        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
//    }
}
