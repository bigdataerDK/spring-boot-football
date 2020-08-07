package com.bcjj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bcjj.service.RedisSaveService;
import com.bcjj.util.RedisKeyUtil;
import com.bcjj.util.TimeStamp2Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description 存储的实现
 * @author: By--Dk
 * @create: 2020-07-18 19:13:57
 **/
@Service
public class RedisSaveServiceImpl implements RedisSaveService {

    @Autowired
    StringRedisTemplate stringRedisTemplate = null;

    @Override
    public void save2Redis(String jsonString) {
        //"match_id"_"type"_"timestamp", s"${mEventJson.toString}")

        //{"match_id":"BEIJI-GZHOU-140720-2","event":{"type":"AT1","timestamp":"点击时时间戳"}}
        //{"match_id":"BEIJI-GZHOU-140720-2","event":{"stat":3,"type":" Extra Time2","timestamp":"点击时时间戳"}}

        final JSONObject fianlEventJson = new JSONObject();
        final JSONObject fianlMEventJson = new JSONObject();

        final JSONObject mEventJsonObject = JSON.parseObject(jsonString);
        //获取比赛id
        final String match_id = mEventJsonObject.getString("match_id");
        //获取event类型和timestamp
        final String event = mEventJsonObject.getString("event");
        final JSONObject evevtJO = JSON.parseObject(event);
        final String eventType = evevtJO.getString("type");
        final String eventTimestamp = evevtJO.getString("timestamp");
        Object eventstat = 0;
        if (evevtJO.get("stat")!= null){
            eventstat = evevtJO.get("stat");
        }

//判断是否是当前日期的时间和对应在比赛信息库中的列表匹配，只有当前天的数据才进行写操作，不是当前天的比赛，不进行写操作
        String eventDate = TimeStamp2Date.stampToDate(Long.parseLong(eventTimestamp));
        Set<Object> match_info = stringRedisTemplate.opsForHash().keys("match_Info");
        for (Object key:match_info){
            String mEvent = stringRedisTemplate.opsForHash().get("match_Info", key).toString();
            String[] matchNews = mEvent.split("_");
            String matchDate = matchNews[1];
            String matchID = matchNews[7];
//            System.out.println("======"+eventDate);
//            System.out.println("======"+match_id);
//            System.out.println(matchDate);
//            System.out.println(matchID);
            if (eventDate.equals(matchDate) && match_id.equals(matchID)){

//                System.out.println("+++++");
                //判断事件是否为开始、补时，获取其时间戳作为标准时间，并把每个事件的当前比赛时间作为json的值拼接到event的日志当中

                if(eventType.trim().equals("KO1")||eventType.trim().equals("KO2")){
                    stringRedisTemplate.opsForValue().set(match_id+"_"+"startRT1",eventTimestamp);
                    //设置存入上半场开始时间的过期时间
                    stringRedisTemplate.expire(match_id+"_"+"startRT1",6*60*60, TimeUnit.SECONDS);
//            System.out.println("主队开球"+stringRedisTemplate.opsForValue().get(match_id + "_" + "startRT1"));
                }
                if(eventType.trim().equals("Start RT2")){
                    stringRedisTemplate.opsForValue().set(match_id+"_"+"startRT2",eventTimestamp);
                    //设置存入下半场开始时间的过期时间
                    stringRedisTemplate.expire(match_id+"_"+"startRT2",6*60*60, TimeUnit.SECONDS);
//            System.out.println(stringRedisTemplate.opsForValue().get(match_id + "_" + "startRT2"));
                }
//
                if(eventType.trim().equals("Extra Time1")){
                    String stat = evevtJO.getString("stat");
                    stringRedisTemplate.opsForValue().set(match_id+"_"+"Extra Time1",stat);
                    //设置存入上半场补时时间的过期时间
                    stringRedisTemplate.expire(match_id+"_"+"Extra Time1",6*60*60, TimeUnit.SECONDS);
//            System.out.println(stringRedisTemplate.opsForValue().get(match_id + "_" + "Extra Time1"));
                }

                //获取比赛中的事件时间
                long eventTsInt = Long.parseLong(eventTimestamp);
//
                //上半场中的比赛时间
                long rt1MatchTs = 0;
                //下半场中的比赛时间
                long rt2MatchTs = 0;
//
                //分、秒和结果时间
                long mTime = 0;
                long sTime = 0;
                String resMeventTime = null;
//
//        如果获取下半场的时间戳为0
                String startRT2 = null;
                        startRT2 =  stringRedisTemplate.opsForValue().get(match_id + "_" + "startRT2");
                if( startRT2 == null ){
                    String startRT1 = stringRedisTemplate.opsForValue().get(match_id + "_" + "startRT1");
//                    System.out.println("上半场开始"+startRT1);
                    if (startRT1 != null){
                        long startRT1L = Long.parseLong(startRT1);
                        if (eventTsInt >= startRT1L ){
                            rt1MatchTs = eventTsInt - startRT1L;
                        }
                    }

                }else {
                    startRT2 = stringRedisTemplate.opsForValue().get(match_id+"_"+"startRT2");
//
//                    System.out.println("下半场开始"+startRT2);
                    long startRT2L = Long.parseLong(startRT2);
                    rt2MatchTs = eventTsInt+2700000L - startRT2L;
                }
//比赛时间
                long mathTs = rt1MatchTs+rt2MatchTs;
                mTime = mathTs/(1000*60);
                sTime = (mathTs/1000)%60;
//如果总时间大于上半场常规45分钟+补时时间，总的时间应减去上半场补时时间
                if(sTime<10){
                    resMeventTime = mTime+":0"+sTime;
                }else {
                    resMeventTime = mTime+":"+sTime;
                }

                String explain= RedisKeyUtil.getExplain(eventType);

                fianlEventJson.put("type",eventType);
                fianlEventJson.put("explain",explain);
                fianlEventJson.put("game_time",resMeventTime);
                fianlEventJson.put("timestamp",eventTimestamp);

                if(eventType.trim().equals("CORNER1")||eventType.trim().equals("CORNER2")
                        ||eventType.trim().equals("REDCARD1")||eventType.trim().equals("REDCARD2")
                        ||eventType.trim().equals("YELLCARD1")||eventType.trim().equals("YELLCARD2")
                        ||eventType.trim().equals("Extra Time1")||eventType.trim().equals("Extra Time2")
                        ){
                    fianlEventJson.put("stat",eventstat);
                }
                fianlMEventJson.put("event",fianlEventJson);
                fianlMEventJson.put("match_id",match_id);
                System.out.println(fianlMEventJson);
//
                stringRedisTemplate.opsForValue().set(match_id.concat("_").concat(eventType).concat("_").concat(eventTimestamp),fianlMEventJson.toString());
                stringRedisTemplate.expire(match_id.concat("_").concat(eventType).concat("_").concat(eventTimestamp),6*60*60, TimeUnit.SECONDS);

        }
        }
    }
}
