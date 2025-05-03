// package com.crud;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class CreateUser extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        // Get the parameters from the form
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String carModel = req.getParameter("carModel");
        String carNumber = req.getParameter("carNumber");

        try {
            // Get the database connection
            Connection conn = DBConnection.getConnection();
            
            // Prepare the SQL query with the additional fields
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users(name, email, phone, carModel, carNumber) VALUES(?, ?, ?, ?, ?)"
            );
            
            // Set the values for the query
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, carModel);
            ps.setString(5, carNumber);
            
            // Execute the query
            ps.executeUpdate();
            
            // Provide feedback to the user
            out.println("User added successfully! <a href='index.html'>Back</a>");
        } catch(Exception e) {
            // Handle any exceptions
            out.println("Error: " + e.getMessage());
        }
    }
}
