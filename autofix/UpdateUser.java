// package com.crud;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateUser extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                out.println("<h2>Update User</h2>");
                out.println("<form action='update' method='post'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("<input type='text' name='name' value='" + rs.getString("name") + "' required><br>");
                out.println("<input type='email' name='email' value='" + rs.getString("email") + "' required><br>");
                out.println("<input type='text' name='phone' value='" + rs.getString("phone") + "' required><br>");
                out.println("<input type='text' name='carModel' value='" + rs.getString("carModel") + "' required><br>");
                out.println("<input type='text' name='carNumber' value='" + rs.getString("carNumber") + "' required><br>");
                out.println("<input type='submit' value='Update'>");
                out.println("</form>");
            } else {
                out.println("User not found.");
            }

            out.println("<br><a href='read'>Back</a>");
        } catch(Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String carModel = req.getParameter("carModel");
        String carNumber = req.getParameter("carNumber");

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE users SET name = ?, email = ?, phone = ?, carModel = ?, carNumber = ? WHERE id = ?");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, carModel);
            ps.setString(5, carNumber);
            ps.setInt(6, id);
            ps.executeUpdate();

            res.sendRedirect("read");
        } catch(Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
