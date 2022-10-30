package com.hgl.utils.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class ExportExcelUtil<T> {

    public void exportExcelByRows(String title, String[] header, Collection<T> rows,
                                  OutputStream out) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        // 生成表格
        Sheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽
        sheet.setDefaultColumnWidth((short)10);
        // 设置表格头
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);//设置背景色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置边框
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        // 字体
        Font font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 12);
        // 应用字体
        style.setFont(font);
        // 创建行
        Row row = sheet.createRow(0);
        for(short i = 0; i < header.length; i++){
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(header[i]);
            cell.setCellValue(text);
        }
        // 设置表格内容
        CellStyle style2 = workbook.createCellStyle();
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);
        style2.setAlignment(HorizontalAlignment.CENTER);
        style2.setVerticalAlignment(VerticalAlignment.CENTER);
        style2.setFillForegroundColor(IndexedColors.WHITE1.index);//设置背景色
        // 生成另一个字体
        Font font2 = workbook.createFont();
        font.setFontName("仿宋_GB2312");
        font.setColor((short) 64);//黑色
        // 把字体应用到当前的样式
        style2.setFont(font2);
        // 遍历集合
        Iterator<T> it = rows.iterator();
        int index = 0;
        while (it.hasNext()){
            index++;
            row = sheet.createRow(index);
            T t = (T)it.next();
            Field[] fields = t.getClass().getDeclaredFields();
            for(short i = 0; i < fields.length; i++){
                Cell cell = row.createCell(i);
                cell.setCellStyle(style2);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                Class tCls = t.getClass();
                Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                Object value = getMethod.invoke(t, new Object[]{});
                // 根据javabean的方法类型，判断值的类型后进行强制类型转换（具体需要自己根据情况修改）
                String textValue = null;
                //javabean里边的get和set方法为Integer时做处理
                if (value instanceof Integer) {
                    int intValue = (Integer) value;
                    cell.setCellValue(intValue);
                }
                else if (value instanceof Double) {
                    double intValue = (Double) value;
                    cell.setCellValue(intValue);
                }
                else if (value instanceof Boolean) {
                    boolean bValue = (Boolean) value;
                    textValue = bValue ? "是" : "否";
                    cell.setCellValue(textValue);
                }
                else if (value instanceof byte[]) {
                    // 声明一个画图的顶级管理器
                    Drawing drawing= sheet.createDrawingPatriarch();
                    row.setHeightInPoints(60);
                    sheet.setColumnWidth(i, (short) (35.7 * 80));
                    byte[] bsValue = (byte[]) value;
                    XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 1023, 255, i, index, i+1, index+1);
                    anchor.setAnchorType(ClientAnchor.AnchorType.byId(0));
                    drawing.createPicture(anchor, workbook.addPicture(bsValue, XSSFWorkbook.PICTURE_TYPE_JPEG));
                } else {
                    if (value != null) {
                        textValue = value.toString();
                    } else {
                        textValue = "";
                    }
                    cell.setCellValue(textValue);
                }

            }
        }

        workbook.write(out);
    }

    public static void exportExcelByRows(String title, String[] header, List<LinkedHashMap<String, Object>> rows,
                                  OutputStream out) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        // 生成表格
        Sheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽
        sheet.setDefaultColumnWidth((short)10);
        // 设置表格头
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);//设置背景色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置边框
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        // 字体
        Font font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 12);
        // 应用字体
        style.setFont(font);
        // 创建行
        Row row = sheet.createRow(0);
        for(short i = 0; i < header.length; i++){
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(header[i]);
            cell.setCellValue(text);
        }
        // 设置表格内容
        CellStyle style2 = workbook.createCellStyle();
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);
        style2.setAlignment(HorizontalAlignment.CENTER);
        style2.setVerticalAlignment(VerticalAlignment.CENTER);
        style2.setFillForegroundColor(IndexedColors.WHITE1.index);//设置背景色
        // 生成另一个字体
        Font font2 = workbook.createFont();
        font.setFontName("仿宋_GB2312");
        font.setColor((short) 64);//黑色
        // 把字体应用到当前的样式
        style2.setFont(font2);
        int index = 0;
        for (LinkedHashMap<String, Object> map : rows){
            int cellIndex = 0;
            row = sheet.createRow(++index);
            for (String key : map.keySet()){
                Cell cell = row.createCell(cellIndex++);
                cell.setCellStyle(style2);

                if(map.get(key) instanceof BigDecimal){
                    BigDecimal bigDecimal = new BigDecimal(map.get(key).toString());
                    Integer v = bigDecimal.intValue();
                    cell.setCellValue(v);
                } else {
                    if(map.get(key).toString() != null && !map.get(key).toString().equals(""))
                    cell.setCellValue(map.get(key).toString());
                }
            }
        }
        workbook.write(out);
    }
}
