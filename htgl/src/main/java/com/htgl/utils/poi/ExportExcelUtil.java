package com.htgl.utils.poi;

import com.htgl.pojo.Menber;
import com.htgl.pojo.Users;
import com.htgl.pojo1.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

public class ExportExcelUtil<T> {

    public static void exportExcelByRows(String title, String[] header, List<LinkedHashMap<String, Object>> rows,
                                         OutputStream out) throws IOException {
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

    /**
     * 读取excel
     * @param fileInputStream 文件
     * @param header 表格头
     * @param header_attr 表格头对应实体类属性
     * @param object 实体类(属性对应表格头)
     * @return ArrayList
     * @throws IOException
     * @throws IllegalAccessException
     */
    public static List readExcelData(FileInputStream fileInputStream, String[] header, String []header_attr, Object object) throws IOException, IllegalAccessException {
        //FileInputStream fileInputStream = new FileInputStream(path);//开启文件读取流
        XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);//读取文件
        //获取sheet
        XSSFSheet sheet = sheets.getSheet("Sheet1");
        List<Object> list = new ArrayList<>();
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        Row row = sheet.getRow(0);// 获取头部
        // 校验表格头是否一致
        // 校验长度
        if(row.getLastCellNum() != header.length){
            throw new IOException("表格头长度与header参数长度不一致");
        }
        // 校验表格头内容
        for(int i = 0; i < header.length; i++){
            if(!header[i].equals(row.getCell(i).toString())){
                throw new RuntimeException("表格内容不一致");
            }
        }
        // 存储内容
        for(int i = 1; i < rows; i++){
            row = sheet.getRow(i);
            int column = row.getLastCellNum();// 获取列数
            // 获取对象属性
            object = new Menber();
            Field[] fields = object.getClass().getDeclaredFields();
            if(fields.length <= 0){
                throw new RuntimeException("接收模板不符合");
            }
            for(int j = 0; j < column; j++){
                Cell cell = row.getCell(j);
                for(Field f : fields){
                    f.setAccessible(true);
                    if(f.getName().equals(header_attr[j])){// 判断表格对应属性与对象属性是否一致
                        // 类型划分
                        if(f.getType() == Integer.class){
                            f.set(object, Integer.parseInt(cell.toString()));
                        }
                        else if(f.getType() == String.class){
                            if(Objects.isNull(cell)){
                                f.set(object, "");
                            }else if(cell.getCellTypeEnum() == CellType.NUMERIC){
                                double numericCellValue = cell.getNumericCellValue();
                                String cellValue = NumberToTextConverter.toText(numericCellValue);
                                f.set(object, cellValue + "");
                            } else
                                f.set(object, cell.toString());
                        }
                    }
                }
            }
            list.add(object);

        }
        return list;
    }

    public static List readExcelData(String path, String[] header, String []header_attr, Object object) throws IOException, IllegalAccessException {
        FileInputStream fileInputStream = new FileInputStream(path);
        return readExcelData(fileInputStream, header, header_attr, object);
    }

    public static void main(String[] args) throws IOException, IllegalAccessException {
        String [] header = new String[]{"学号","姓名", "部门", "职称", "院系", "qq", "手机"};
        String [] header_name = {"sid", "sname", "dname", "tname", "cname", "qq", "phone"};
        Menber menber = new Menber();
        List list = readExcelData("F:\\muban.xlsx", header, header_name, menber);
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
