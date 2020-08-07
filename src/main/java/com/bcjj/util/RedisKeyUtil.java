package com.bcjj.util;

/**
 * @description
 * @author: By--Dk
 * @create: 2020-07-18 14:30:27
 **/
public class RedisKeyUtil {
    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值:列名
     *
     * @param tableName 表名
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @param column 列名
     * @return
     */
    public static String getKeyWithColumn(String tableName,String majorKey,String majorKeyValue,String column){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        buffer.append(column);
        return buffer.toString();
    }
    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值
     *
     * @param tableName 表名
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @return
     */
    public static String getKey(String tableName,String majorKey,String majorKeyValue){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        return buffer.toString();
    }

    public static String getExplain(String type){
        if(type.equals("KO1")){
            return "kick_off_home";
        }else if(type.equals("KO2")){
            return "kick_off_guest";
        }else if(type.equals("Stop RT1")){
            return "stop_first_half";
        }else if(type.equals("Start RT2")){
            return "start_second_half";
        }else if(type.equals("ON_DAT1")){
            return "on_danger_attak_home";
        }else if(type.equals("ON_DAT2")){
            return "on_danger_attak_guest";
        }else if(type.equals("OFF_DAT1")){
            return "off_danger_attak_home";
        }else if(type.equals("OFF_DAT2")){
            return "off_danger_attak_guest";
        }else if(type.equals("AT1")){
            return "attack_home";
        }else if(type.equals("AT2")){
            return "attack_guest";
        }else if(type.equals("DFK1")){
            return "dangerous_free_kick_home";
        }else if(type.equals("DFK2")){
            return "dangerous_free_kick_guest";
        }else if(type.equals("CONF_GOAL1")){
            return "goal_conifirmation_home";
        }else if(type.equals("CONF_GOAL2")){
            return "goal_conifirmation_guest";
        }else if(type.equals("CGOAL1")){
            return "cancel_goal_home";
        }else if(type.equals("CGOAL2")){
            return "cancel_goal_guest";
        }else if(type.equals("Extra Time1")){
            return "first_half_extra_time";
        }else if(type.equals("Extra Time2")){
            return "second_half_extra_time";
        }else if(type.equals("SAFE1")){
            return "safe_home";
        }else if(type.equals("SAFE2")){
            return "safe_guest";
        }else if(type.equals("OFF_AT1")){
            return "off_attack_home";
        }else if(type.equals("OFF_AT2")){
            return "off_attack_guest";
        }else if(type.equals("YC_RC1")){
            return "red_double_yellow_home";
        }else if(type.equals("YC_RC2")){
            return "red_double_yellow_guest";
        } else if(type.equals("CSAFE2")){
            return "cancel_safe_guest";
        }else if(type.equals("CORNER1")){
            return "corner_home";
        }else if(type.equals("CORNER2")){
            return "corner_guest";
        }else if(type.equals("REDCARD1")){
            return "red_card_home";
        }else if(type.equals("REDCARD2")){
            return "red_card_guest";
        }else if(type.equals("YELLCARD1")){
            return "yellow_card_home";
        }else if(type.equals("YELLCARD2")){
            return "yellow_card_guest";
        }else if(type.equals("PENALTY1")){
            return "penalty_kick_home";
        }else if(type.equals("PENALTY2")){
            return "penalty_kick_guest";
        }else if(type.equals("IN_PENALTY1")){
            return "penalty_goal_home";
        }else if(type.equals("LOST_PENALTY1")){
            return "penalty_lost_home";
        }else if(type.equals("IN_PENALTY2")){
            return "penalty_goal_guest";
        }else if(type.equals("LOST_PENALTY2")){
            return "penalty_lost_guest";
        }else if(type.equals("CSAFE1")){
            return "cancel_safe_home";
        }else {return "";}
    }
}

