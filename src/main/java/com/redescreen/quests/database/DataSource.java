package com.redescreen.quests.database;

import com.redescreen.quests.database.exception.DatabaseConnectionException;
import com.redescreen.quests.database.jdbc.JdbcAuthentication;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static HikariConfig config;
    private static HikariDataSource hikariDataSource;

    static {
        config = new HikariConfig();
    }

    public static void setup(JdbcAuthentication jdbcAuthentication) {
        config.setJdbcUrl(jdbcAuthentication.getUrl());
        config.setUsername(jdbcAuthentication.getUsername());
        config.setPassword(jdbcAuthentication.getPassword());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        hikariDataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() {
        try {
            return hikariDataSource.getConnection();
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }

    public static void disconnect() {
        if (hikariDataSource != null) {
            hikariDataSource.close();
        }
    }
}
