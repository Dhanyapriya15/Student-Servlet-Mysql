package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            Connection conn = DButil.getConnection();

            // SQL query to fetch student data
            String query = "SELECT student_id, name, department, cgpa FROM students"; 
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

          
            out.println("<html><body>");
            out.println("<h1>Student List</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>Student ID</th><th>Name</th><th>Department</th><th>CGPA</th></tr>");
            
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("student_id") + "</td><td>" 
                            + rs.getString("name") + "</td><td>" 
                            + rs.getString("department") + "</td><td>" 
                            + rs.getDouble("cgpa") + "</td></tr>");
            }
            out.println("</table>");
            out.println("</body></html>");

            rs.close();
            stmt.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
            e.printStackTrace(out);
        }
    }
}
