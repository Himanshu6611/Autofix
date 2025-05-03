// package com.crud;
import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/crud_db", "root", "Himanshu@2006#"
        );
    }
}


// javac -cp "C:\Program Files\Apache Software Foundation\Tomcat 9.0\lib\servlet-api.jar" *.java


// res is an object of the HttpServletResponse class. This object represents the HTTP response sent back to the client (e.g., a browser).

// setContentType("text/html") tells the server that the response will be in HTML format. This helps the browser understand how to interpret the response content (in this case, it will render the response as HTML).