// package com.crud;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ReadUsers extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            out.println("<h2>Users List</h2><table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Car Model</th><th>Car Number</th><th>Actions</th></tr>");
            while(rs.next()) {
                int id = rs.getInt("id");
                out.println("<tr><td>" + id + "</td><td>" + rs.getString("name") + "</td><td>" +
                    rs.getString("email") + "</td><td>" +
                    rs.getString("phone") + "</td><td>" +
                    rs.getString("carModel") + "</td><td>" +
                    rs.getString("carNumber") + "</td><td>" +
                    "<a href='update?id=" + id + "'>Edit</a> | " +
                    "<a href='delete?id=" + id + "'>Delete</a></td></tr>");
            }
            out.println("</table><br><a href='index.html'>Back</a>");
        } catch(Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
