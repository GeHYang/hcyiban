package com.htgl.utils;

import com.htgl.pojo1.DutyTime;
import com.htgl.pojo1.User;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelTest {
  static String[] header = new String[] { "时间", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
  static String[] times = new String[] { "8:00-9:25", "9:50-12:00", "14:40-16:05", "16:30-17:55", "19:40-21:05" };


  public static List<DutyTime> export(List<DutyTime> dutyTimes, String path) {
    timeSort(dutyTimes);
    int colum = getMaxNum(dutyTimes) + 1;
    HSSFWorkbook hSSFWorkbook = new HSSFWorkbook();
    Sheet sheet = hSSFWorkbook.createSheet();
    Row row = sheet.createRow(0);
    
    for (int i = 0; i < header.length; i++) {
      row.createCell(i).setCellValue(header[i]);
    }
    int c = 1; int j;
    for (j = 0; j < dutyTimes.size(); j++) {
      for (int n = 0; n < colum; n++) {
        sheet.createRow(j * colum + n + 1);
      }
    }

    sheet.setColumnWidth(0, 3840);

    for (j = 0; j < 5; j++) {

      CellRangeAddress c1 = null;
      c1 = new CellRangeAddress(j * colum + 1, j * colum + colum, 0, 0);
      sheet.addMergedRegion(c1);
      Row row2 = sheet.getRow(j * colum + 1);
      row2.createCell(0).setCellValue(times[j]);
      CellStyle cellStyle = hSSFWorkbook.createCellStyle();
      cellStyle.setAlignment(HorizontalAlignment.CENTER);
      cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

      Font font = hSSFWorkbook.createFont();
      font.setFontName("楷体");
      font.setBold(true);

      cellStyle.setFont(font);

      row2.getCell(0).setCellStyle(cellStyle);
    }


    int k = 0;
    for (int m = 0; m < dutyTimes.size(); m++) {
      DutyTime dutyTime = dutyTimes.get(m);

      if (m % 5 == 0 && m != 0) {
        c++;
        k = 0;
      }
      for (int n = 0; n < colum; n++) {
        Row row1 = sheet.getRow(k * colum + n + 1);
        if (dutyTime.dutyUsers.size() > n) {
          row1.createCell(c).setCellValue(((User)dutyTime.dutyUsers.get(n)).getSname());
        }
      }
      k++;
    }
    OutputStream out = null;
    try {
      out = new FileOutputStream(path + "duty.xls");
      hSSFWorkbook.write(out);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (out != null) {
          out.flush();
          out.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        if (hSSFWorkbook != null) {
          hSSFWorkbook.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return timeSort(dutyTimes);
  }


  public static List<DutyTime> timeSort(List<DutyTime> dutyTimes) {
    for (int i = 0; i < dutyTimes.size() - 1; i++) {
      for (int j = 0; j < dutyTimes.size() - i - 1; j++) {
        if (((DutyTime)dutyTimes.get(j)).compareToTime(dutyTimes.get(j + 1)) == 1) {
          Collections.swap(dutyTimes, j, j + 1);
        }
      }
    }
    return dutyTimes;
  }


  public static int getMaxNum(List<DutyTime> dutyTimes) {
    int max = 0;
    for (int i = 0; i < dutyTimes.size(); i++) {
      if (((DutyTime)dutyTimes.get(i)).dutyUsers.size() > max) {
        max = ((DutyTime)dutyTimes.get(i)).dutyUsers.size();
      }
    }
    return max;
  }
}