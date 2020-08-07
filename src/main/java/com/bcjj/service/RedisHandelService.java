package com.bcjj.service;

import com.alibaba.fastjson.JSONObject;
import com.bcjj.bean.MatchInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisHandelService {

    public JSONObject getMatchID();
    public List<JSONObject> getPageMatchID(Integer page);
}
