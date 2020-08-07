package com.bcjj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description 时间戳转时间的工具类
 * @author: By--Dk
 * @create: 2020-07-22 17:49:12
 **/
public class TimeStamp2Date {
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time_Date = sdf.format(new Date(time));
        return time_Date;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(TimeStamp2Date.stampToDate(System.currentTimeMillis()));
    }
}
