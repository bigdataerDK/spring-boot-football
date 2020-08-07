package com.bcjj.bean;

import java.io.Serializable;

/**
 * @description 关于比赛的赛事信息0_2020-07-25_18:15_广州恒大淘宝_上海绿地申花_20207001
 * @author: By--Dk
 * @create: 2020-07-18 16:01:37
 **/

public class MatchInfo implements Serializable {

    public MatchInfo(String id, String date, String minTime, String week, String home, String visit, String area, String matchId) {
        this.id = id;
        this.date = date;
        this.minTime = minTime;
        this.week = week;
        this.home = home;
        this.visit = visit;
        this.area = area;
        this.matchId = matchId;
    }

    // "id": 0,
    //  "date": "2020-07-25",
    //   "time": "18:00",
    //   "week": "六",
    //   "host_team": "山东鲁能泰山",
    //   "guest_team": "北京中赫国安",
    //   "area": '苏州奥林匹克体育中心' 
    private String id;
    private String date;
    private String minTime;
    private String week;
    private String home;
    private String visit;
    private String area;
    private String matchId;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    @Override
    public String toString() {
        return "MatchInfo{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", minTime='" + minTime + '\'' +
                ", home='" + home + '\'' +
                ", visit='" + visit + '\'' +
                ", matchId='" + matchId + '\'' +
                ", week='" + week + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
