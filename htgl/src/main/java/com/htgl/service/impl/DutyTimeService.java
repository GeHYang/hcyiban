package com.htgl.service.impl;

import com.htgl.pojo1.DutyTime;
import com.htgl.pojo1.User;
import com.htgl.utils.JDBCUtils;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DutyTimeService {

    List<DutyTime> dutyTimes = null;

    //获取每个时间段的成员有空情况并进行一轮排序
    public List<DutyTime> getAllFreeTime(){
        dutyTimes = new ArrayList<>();
        String []weeks = {"mon", "tue", "wed", "thurs", "fri", "sat"};
        String []times = {"8:00-9:25","9:50-12:00","14:40-16:05",
                "16:30-17:55","19:40-21:05"};
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            int num = 1;
            for(int i = 0; i < weeks.length; i++){
                for(int j = 0; j < times.length; j++){
                    String sql = "select u.sid, sname, num, tid from users u, `" +
                            weeks[i] + "` where u.sid = " + "`" + weeks[i] + "`.sid and " +
                            "`" + times[j] + "` = '0'";
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql);
                    DutyTime dutyTime = new DutyTime();
                    dutyTime.setTime(num + "");
                    dutyTime.setDay(weeks[i]);
                    while (rs.next() ){
                        User user = new User();
                        user.setSid(rs.getString("sid"));
                        user.setSname(rs.getString("sname"));
                        user.setNum(rs.getInt("num"));
                        user.setTid(rs.getString("tid"));
                        dutyTime.freeUsers.add(user);
                    }

                    num++;
                    dutyTimes.add(dutyTime);
                }
            }
            JDBCUtils.release(stmt, conn);
        } catch (Exception e){
            e.printStackTrace();
        }
        sort(dutyTimes);
        twoSort();
        dutySort();
        return dutyTimes;
    }

    /**
     * 一轮排序： 按时间段人数从小到大排序，优先安排人数少的时间段
     */
    public void sort(List<DutyTime> dutyTimes){
        for (int i = 0; i < dutyTimes.size() - 1; i++){
            for (int j = 0; j < dutyTimes.size() - i - 1; j++){
                if(dutyTimes.get(j).compareTo(dutyTimes.get(j+1)) == 1){
                    Collections.swap(dutyTimes, j, j+1);
                }
            }
        }
    }

    /**
     * 二轮排序：对每位成员的空余时间(节数)进行排序，优先安排空余时间少的
     */
    public void twoSort(){
        for(int i = 0; i < dutyTimes.size(); i++){
            for (int j = 0; j < dutyTimes.get(i).freeUsers.size() - 1; j++){
                for (int k = 0; k < dutyTimes.get(i).freeUsers.size() - j - 1; k++){
                    if(dutyTimes.get(i).freeUsers.get(k).compareTo(dutyTimes.get(i).freeUsers.get(k+1)) == 1){
                        Collections.swap(dutyTimes.get(i).freeUsers, k, k+1);
                    }
                }
            }
        }
        for(int i = 0; i < dutyTimes.size(); i++){
            for (int j = 0; j < dutyTimes.get(i).freeUsers.size() - 1; j++){
                for (int k = 0; k < dutyTimes.get(i).freeUsers.size() - j - 1; k++){
                    if(dutyTimes.get(i).freeUsers.get(k).compareToTid(dutyTimes.get(i).freeUsers.get(k+1)) == 1){
                        Collections.swap(dutyTimes.get(i).freeUsers, k, k+1);
                    }
                }
            }
        }
    }



    //三轮排序
    public void thereSort(int i, int j){

        User user = dutyTimes.get(j).freeUsers.get(i);

        //将一天内其他有空时间删除
        for(int p = 0; p < dutyTimes.size(); p++){
            if(dutyTimes.get(p).getDay().equals(dutyTimes.get(j).getDay())){
                for(int q = 0; q < dutyTimes.get(p).freeUsers.size(); q++){
                    if(dutyTimes.get(p).freeUsers.get(q).getSid().equals(user.getSid())){
                        dutyTimes.get(p).freeUsers.remove(q);
                        break;
                    }
                }
            }
        }
        if(user.getPreTime() <= 1){
            for (int p = 0; p < dutyTimes.size(); p++){
                for(int q = 0; q < dutyTimes.get(p).freeUsers.size(); q++){
                    if(dutyTimes.get(p).freeUsers.get(q).getSid().equals(user.getSid())){
                        dutyTimes.get(p).freeUsers.remove(q);
                        break;
                    }
                }
            }
        } else{
            for (int p = 0; p < dutyTimes.size(); p++){
                for(int q = 0; q < dutyTimes.get(p).freeUsers.size(); q++){
                    if(dutyTimes.get(p).freeUsers.get(q).getSid().equals(user.getSid())){
                        dutyTimes.get(p).freeUsers.get(q).setPreTime(dutyTimes.get(p).freeUsers.get(q).getPreTime() - 1);
                        break;
                    }
                }
            }
        }

    }

    //最多人有空的时间段
    public int getMaxNum(){
        int max = 0;
        for (int p = 0; p < dutyTimes.size(); p++){
            for(int q = 0; q < dutyTimes.get(p).freeUsers.size(); q++){
                max = dutyTimes.get(p).freeUsers.size() > max ? dutyTimes.get(p).freeUsers.size() : max;
            }
        }
        return max;
    }

    //生成值班表数据
    public void dutySort(){
        int len = getMaxNum();
        for (int i = 0; i < len; i++){
            for (int j = 0; j < dutyTimes.size(); j++){
                if(dutyTimes.get(j).freeUsers.size() > 0){
                    dutyTimes.get(j).dutyUsers.add(dutyTimes.get(j).freeUsers.get(0));
                    thereSort(0, j);
                }
            }
            len = getMaxNum();
        }
    }

}
