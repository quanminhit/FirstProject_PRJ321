/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.util;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ADMIN
 */
public class DBHelper {

    public static Connection getConnection()
            throws /*ClassNotFoundException,*/ SQLException, NamingException {

        //1. Get current context (lấy file hệ thống của máy tính)
        Context currentContext = new InitialContext();
        //2. Lookup tomcat context
        Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
        //3. Lookup DS
        DataSource ds = (DataSource)tomcatContext.lookup("DS007");
        //4. Open connection DS
        Connection con = ds.getConnection();
        
        return con;
        //Hệ điều hành khác nhau, khác nhau quy tắc đặt tên file và thư mục đặt file,
        //Khái niệm context: đồng nhất cơ chế gọi đường dẫn tên file và thư mục trên máy tính// JMDI 

//        //1. Load Driver (Driver is available)
//        //Su dung throw de controller biet chuyen gi xay ra de xu li
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        
//        //2. Create Connection String
//        String url = "jdbc:sqlserver://"
//                + "localhost:1433;"
//                + "databaseName=Loginn";
//        //3. Open Connection
//        Connection con = DriverManager.getConnection(url,"sa","0835748091quan");
//        return con;
    }
}
