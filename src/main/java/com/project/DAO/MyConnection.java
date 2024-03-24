package com.project.DAO;
import java.sql.*;
public class MyConnection {
    public static Connection getMyConnection() throws Exception
    {
    String dbUrl = "jdbc:mysql://localhost:3306/hello";
    String user = "root";
    String password = "123456789";
    Connection con = DriverManager.getConnection(dbUrl, user, password);
    return con;
    }
}

