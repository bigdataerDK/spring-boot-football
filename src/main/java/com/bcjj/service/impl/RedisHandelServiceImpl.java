package com.bcjj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bcjj.bean.MatchInfo;
import com.bcjj.service.RedisHandelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description redis的处理类
 * @author: By--Dk
 * @create: 2020-07-18 16:59:46
 **/

@Service
public class RedisHandelServiceImpl implements RedisHandelService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    @Override
    public JSONObject getMatchID() {

        final JSONObject matchIDJSONObject = new JSONObject();
        Set<Object> match_info = stringRedisTemplate.opsForHash().keys("match_Info");

        System.out.println(match_info);

        matchIDJSONObject.put("match_Info",match_info);

        return matchIDJSONObject;

    }

    @Override
    public List<JSONObject> getPageMatchID(Integer page) {

        Set<Object> match_info = stringRedisTemplate.opsForHash().keys("match_Info");

        List<JSONObject> listMap = new ArrayList<>();
        for (Object key:match_info) {
            JSONObject matchJSON = new JSONObject();

//            System.out.println(stringRedisTemplate.opsForHash().get("match_Info", key));
            final Object matchInfo = stringRedisTemplate.opsForHash().get("match_Info", key);

            final String[] elemts = matchInfo.toString().split("_");

            final List<String> strings = Arrays.asList(elemts);

//            List<String> matchs = strings
//                    .stream()
//                    .filter(matchinfo -> ((Integer.parseInt(strings.get(0)) > 5 *(page-1))&& (Integer.parseInt(strings.get(0)) <= 5 *page)))
////                    .map(string -> new MatchInfo())
//                    .collect(Collectors.toList());

            if((Integer.parseInt(strings.get(0)) > 5 *(page-1))&& (Integer.parseInt(strings.get(0)) <= 5 *page)){
                matchJSON.put("id",strings.get(0));
                matchJSON.put("date",strings.get(1));
                matchJSON.put("time",strings.get(2));
                matchJSON.put("week",strings.get(3));
                matchJSON.put("host_team",strings.get(4));
                matchJSON.put("visit",strings.get(5));
                matchJSON.put("area",strings.get(6));
                matchJSON.put("matchId",strings.get(7));
           }else {
                continue;
            }

            //添加map到list当中
            listMap.add(matchJSON);
        }
        return listMap;
    }
}
