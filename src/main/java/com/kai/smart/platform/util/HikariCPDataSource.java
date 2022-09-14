package com.kai.smart.platform.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPDataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setDriverClassName("org.postgresql.Driver");

        config.setJdbcUrl("jdbc:postgresql://192.168.21.61:5432/lgddnaapip?currentSchema=api");
        config.setUsername("user");
        config.setPassword("password");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setConnectionTimeout(60000);//60000
        config.setMaxLifetime(120000);//120000
        config.setMaximumPoolSize(20);//40
        config.setMinimumIdle(10);
        config.setDriverClassName("org.postgresql.Driver");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private HikariCPDataSource(){}
}
