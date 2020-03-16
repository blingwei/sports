package com.yw.sports.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 86187
 * 创建定时任务
 */
@Component
@EnableScheduling //开启定时任务
public class ScheduleTask {


    /**
     * 每隔3个小时把Redis中用户的运动数据写进数据库
     */
    @Async("scheduledPoolTaskExecutor")
    @Scheduled(cron = "0 0 0/3 * * ? ")
    public void insertUserSportsData(){

    }


}
