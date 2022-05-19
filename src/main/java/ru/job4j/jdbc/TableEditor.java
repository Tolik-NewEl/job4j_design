package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void statement(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        statement(String.format(
                "create table if not exists %s ();",
                tableName
        ));
        System.out.println(getTableScheme(connection, tableName));
    }

    public void dropTable(String tableName) throws SQLException {
        statement(String.format(
                "drop table if exists %s;",
                tableName
        ));
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        statement(String.format(
                "alter table %s add column %s %s;",
                tableName, columnName, type
        ));
        System.out.println(getTableScheme(connection, tableName));
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        statement(String.format(
                "alter table %s drop column %s;",
                tableName, columnName
        ));
        System.out.println(getTableScheme(connection, tableName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        statement(String.format(
                "alter table %s rename column %s to %s;",
                tableName, columnName, newColumnName
        ));
        System.out.println(getTableScheme(connection, tableName));
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
        tableEditor.createTable("testTable");
        tableEditor.addColumn("testTable", "name", "text");
        tableEditor.renameColumn("testTable", "name", "object");
        tableEditor.dropColumn("testTable", "object");
        tableEditor.dropTable("testTable");
        }
    }
}
