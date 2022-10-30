package com.htgl.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    //载入配置文件
    static {
        DRIVER = "com.mysql.cj.jdbc.Driver";
        URL = "jdbc:mysql://10.150.2.57:3306/hgl?serverTimezone=UTC";
        USERNAME = "root";
        PASSWORD = "yibanjishubu";
    }
    static {
        try {
            Class.forName(DRIVER);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    //获得数据库连接
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //释放资源
    public static void release(java.sql.Statement statement, Connection conn){
        release(null, statement, conn);
    }
    //释放资源
    public static void release(java.sql.ResultSet rs, java.sql.Statement statement, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void release(java.sql.PreparedStatement statement, Connection conn){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //获取连接池
//    public static DataSource getDataSource(){
//        return dataSource;
//    }
}
