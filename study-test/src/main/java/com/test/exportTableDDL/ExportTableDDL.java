package com.test.exportTableDDL;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-02-29 10:09:24
 **/
public class ExportTableDDL {
    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3308/db_ruoyi?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        String dbUser = "root";
        String dbPassword = "123456";
        String file = "C:\\Workspace\\ZTemp\\export.sql";
        
        
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            
            String catalog = "db_ruoyi";
            // String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = "sys_%"; // 替换为你的表名
            // String[] types = {"TABLE", "VIEW"};
            String[] types = {"TABLE"};
            
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet result = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);
            
            while (result.next()) {
                String tableName = result.getString(3);
                System.out.println("Fetching DDL for table: " + tableName);
                
                // 取数据库中表的创建脚本通常依赖于数据库具体的 SQL 命令
                // 例如，MySQL 使用 SHOW CREATE TABLE 语句
                ResultSet ddlResult = conn.createStatement().executeQuery("SHOW CREATE TABLE " + tableName);
                
                while (ddlResult.next()) {
                    String createDDL = ddlResult.getString(2) + ";";
                    System.out.println(createDDL);
                    
                    String dropTable = "drop table if exists db_ruoyi." + tableName + ";";
                    // 写入到文件
                    writer.write("-- RuoYiTableName: " + tableName);
                    writer.newLine();
                    writer.write(dropTable);
                    writer.newLine();
                    writer.write(createDDL);
                    writer.newLine();
                    writer.newLine();
                    writer.append("\r\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
