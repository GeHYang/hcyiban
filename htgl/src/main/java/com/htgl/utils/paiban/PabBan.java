package com.htgl.utils.paiban;

import java.util.*;

/**
 * 排班测试
 */
public class PabBan {

    // 记录每个时间段空闲人员数量
    private static Integer[] order;// 编排顺序
    private static Integer[] count = new Integer[30];// 总共30个时间段

    public static void main(String[] args) {
        paiban();
    }
    // 排班测试
    // 数据调整
    public static Map paiban(){
        Map<String, String[]> data = TestData.getMap();
        long time = new Date().getTime();
        // 数据调整
        Map<String, List> data1 = new HashMap<>();
        for(String key : data.keySet()){
            List list = new ArrayList();
            for(int i = 0; i < data.get(key).length; i++){
                Integer[] states = new Integer[5];
                String[] strs = data.get(key)[i].split(",");
                for(int j = 0; j < 5; j++){
                    states[j] = Integer.parseInt(strs[j]);
                }
                list.add(states);
            }
            data1.put(key, list);
        }
        // 设置优先级
        setPriority(data1);
        // 将有课时间段置为-1
        setNotFreeTime(data1);
        // 计时
        System.out.println(new Date().getTime() - time);
        return data1;
    }
    /**
     * 优先级调整：优先级越低越先安排
     *  1、34节、89节西区有课 -- > 5
     *  2、12节、67节西区有课 -- > 4
     */
    static Map<String, List> setPriority(Map<String, List> map){
        int i;
        for(String key : map.keySet()){
            // 获得每个人一周空闲情况
            List<Integer[]> list = map.get(key);
            // 遍历每天的所有时间段
            for(i = 0; i < list.size(); i++){
                Integer[] states = list.get(i);// 得到一天的情况
                // 设置12节有课优先级
                if(states[0] == 2 && states[1] == 0){
                    states[1] = 4;
                }
                // 34节有课优先级
                if(states[1] == 2 && states[0] == 0){
                    states[0] = 5;
                }
                // 设置67节有课优先级
                if(states[3] == 2 && states[4] == 0){
                    states[4] = 4;
                }
                // 89节有课优先级
                if(states[4] == 2 && states[3] == 0){
                    states[3] = 5;
                }
            }
        }
        return map;
    }
    /**
     * 将有课时间段置为-1
     */
    static Map<String, List> setNotFreeTime(Map<String, List> map){
        int i, j;

        for(String key : map.keySet()){
            List<Integer[]> states = map.get(key);
            // 修改有课时间段的值
            for(i = 0; i < states.size(); i++){
                Integer[] state = states.get(i);
                for(j = 0; j < state.length; j++){
                    if(state[j] == 1 || state[j] == 2){
                        state[j] = -1;
                    } else {
                        count[i * 5 + j]++;// 对时间段人数+1
                    }
                }
            }
        }
        return map;
    }
    /**
     *  通过每个时间段人数设置编排顺序
     */
    static void orderByCount(){
        // 初始化顺序表
        for(int i = 0; i < 30; i++){
            order[i] = i;
        }
        // 循环判定
        for(int i = 0; i < count.length - 1; i++){
            for(int j = 0; j < count.length - i - 1; j++){
                if(count[j] > count[j+1]){
                    int temp = order[j];
                    order[j] = order[j + 1];
                    order[j + 1] = temp;
                }
            }
        }
    }

    // 编排
    static void arrange(Map<String, List> map){
        // 用来存储已安排次数
        Map<String, DutyTable> map1 = new HashMap<>();

        // 开排
        for(int i = 0; i < order.length; i++){
            int week = order[i] / 5;// 获取星期
            int time = order[i] % 5;// 获取时间段
            for(String key : map.keySet()){
                List<Integer[]> list = map.get(key);
                // 获取对应星期
                Integer[] weeks = list.get(week);
                // 获取对应时间段
                Integer time1 = weeks[time];
                // 判断是否是有空
                if(time1 == -1) continue;// 下一个
                // 判断当天是否已安排
                if(map1.get(key).week0 == week || map1.get(key).week1 == week) continue;
            }
        }
    }

    class DutyTable{
        Integer week0 = -1;
        Integer week1 = -1;
        Integer count = 0;
    }
}
