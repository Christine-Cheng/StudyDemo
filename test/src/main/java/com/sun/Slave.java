package com.sun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**https://stackoverflow.com/questions/16788447/java-hibernate-check-mysql-replication-is-in-sync#
 * @author Happy
 * @create 2021/9/16-1:52
 **/
public class Slave {
    static final String DB_ADDRESS = "localhost";
    static final String DB_NAME = "mysql";
    static final String DB_USER = "root";
    
    public static void main (String[] args) {
        // get password
        String password = "";
        if (args!=null && args.length>0) {
            password = args[0];
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
            System.out.println("Driver issue: " + ex.getMessage());
            System.out.println("Driver issue: " + ex.getClass().toString());
        }
        // connect to database
        try {
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://"+DB_ADDRESS+"/"+DB_NAME+"?autoReconnect=true",DB_USER,password);
            
            // Do something with the Connection
            System.out.println("Connection: " + conn);
            Statement stmt = conn.createStatement();
            
            
            ResultSet RS = stmt.executeQuery("SHOW TABLES");
            while (RS.next()) {
                System.out.println("table: '" + RS.getString(1) + "'");
            }
            
            // disconnect from database
            conn.close();
            stmt.close();
            
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
