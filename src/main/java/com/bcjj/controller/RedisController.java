package com.bcjj.controller;

import com.alibaba.fastjson.JSONObject;
import com.bcjj.bean.MatchInfo;
import com.bcjj.service.RedisHandelService;
import com.bcjj.service.impl.RedisHandelServiceImpl;
import com.bcjj.service.impl.RedisSaveServiceImpl;
import org.omg.CORBA.TIMEOUT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: By--Dk
 * @create: 2020-07-18 14:16:22
 **/
@RestController
//@RequestMapping("/redis")
public class RedisController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisHandelServiceImpl redisHandelService = null;

    @Autowired
    private RedisSaveServiceImpl redisSaveService = null;

    //返回matchid
    @RequestMapping("matchInfo")
    @ResponseBody
    @CrossOrigin
    public JSONObject getMatchID(){
        //获取比赛id的list
        return redisHandelService.getMatchID();
    }

    //分页
    @GetMapping("matchinfopage")
    @CrossOrigin
    public List<JSONObject> getPageMatchID(@RequestParam Integer page){
        //获取比赛id的list
        return redisHandelService.getPageMatchID(page);
    }

    //获取event的json数据处理存入redis

    @RequestMapping("event")
    @CrossOrigin
    public int saveEvent2Redis(@RequestBody String jsonString){
        System.out.println(jsonString);
        redisSaveService.save2Redis(jsonString);
        return 1;
    }
}
