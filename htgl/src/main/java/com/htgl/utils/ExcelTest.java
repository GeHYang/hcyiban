package com.htgl.utils;

import com.htgl.pojo1.DutyTime;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

/**
 * 值班表导出成excel
 */
public class ExcelTest {

    static String[] header = {"时间","星期一","星期二","星期三","星期四","星期五","星期六"};
    //生成
    public static List<DutyTime> export(List<DutyTime> dutyTimes, String path){
        timeSort(dutyTimes);//排序
        int colum = getMaxNum(dutyTimes) + 1;//每个时间段最大人数
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();//创建工作表
        Row row = sheet.createRow(0);
        for(int i = 0; i < header.length; i++){
            row.createCell(i).setCellValue(header[i]);
        }
        int c = 1;
        for(int i = 0; i < dutyTimes.size(); i++){
            for(int j = 0; j < colum; j++){
                sheet.createRow(i*colum + j + 1);
            }
        }
        Row row1;
        int k = 0;
        for (int i = 0; i < dutyTimes.size(); i++){
            DutyTime dutyTime = dutyTimes.get(i);

            if(i % 5 == 0 && i != 0){
                c++;
                k = 0;
            }
            for(int j = 0; j < colum; j++){
                row1 = sheet.getRow((k * colum) + j + 1);
                if(dutyTime.dutyUsers.size() > j){
                    row1.createCell(c).setCellValue(dutyTime.dutyUsers.get(j).getSname());
                }
            }
            k++;
        }
        OutputStream out = null;
        try {
            out = new FileOutputStream(path + "duty.xls");
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(out != null) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            try{
                if(wb != null) {
                    wb.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return timeSort(dutyTimes);
    }
    //对时间段进行排序
    public static List<DutyTime> timeSort(List<DutyTime> dutyTimes){

        for(int i  = 0; i < dutyTimes.size() - 1; i++){
            for(int j = 0; j < dutyTimes.size() - i - 1; j++){
                if(dutyTimes.get(j).compareToTime(dutyTimes.get(j+1)) == 1){
                    Collections.swap(dutyTimes, j, j+1);
                }
            }
        }
        return dutyTimes;
    }

    //获取最多人值班的时间段的人数
    public static int getMaxNum(List<DutyTime> dutyTimes){
        int max = 0;
        for(int i = 0; i < dutyTimes.size(); i++){
            if(dutyTimes.get(i).dutyUsers.size() > max){
                max = dutyTimes.get(i).dutyUsers.size();
            }
        }
        return max;
    }

}
