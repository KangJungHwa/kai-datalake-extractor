package com.kai.smart.platform.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class HiveConnectionUtils {

//
//    @Value("${hive.connectionURL}")
//    String hiveUrl;
//
//    @Value("${hive.username}")
//    String userName;
//
//    @Value("${hive.password}")
//    String passWord;

    public  Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            conn = DriverManager.getConnection("jdbc:hive2://172.30.1.191:10000/default", null, null);
        } catch (ClassNotFoundException e) {
            log.error("Class Not Found Exception:{}", e);
        } catch (SQLException e) {
            log.error("SQL Exception:{}", e);
        }
        return conn;
    }


}
