package com.jobtracker;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ViewJobsServlet")
public class ViewJobsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);

    	if (session == null || session.getAttribute("userId") == null) {
    	    response.sendRedirect("login.jsp");
    	    return;
    	}

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<head>");
        out.println("<body>");
        out.println("<a href='LogoutServlet' class='logout-btn' title='Logout'>&#128682;</a>");
        out.println("<div class='back-btn'>");
        out.println("<a href='DashboardServlet' class='back-link'>");
        out.println("<i class='bi bi-arrow-left'></i>");
        out.println("</a>");
        out.println("</div>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css'>");
        out.println("<style>");
        out.println(".logout-btn { position: fixed; top:20px; right:30px; font-size:28px; text-decoration:none; background: rgba(255,255,255,0.1); padding:10px 14px; border-radius:50%; backdrop-filter: blur(6px); color:white; transition:0.3s; }");
        out.println(".logout-btn:hover { background:#ff4b2b; transform: scale(1.1);} ");
        out.println("body { " +
        		"background: linear-gradient(rgba(0,0,0,0.75), rgba(0,0,0,0.75)), " +
        		" url('https://images.unsplash.com/photo-1504384308090-c894fdcc538d') \r\n"
        		+ "              no-repeat center center fixed;" +
        		"background-size: cover; " +
        		"background-position: center; " +
        		"background-attachment: fixed; " +
        		"min-height: 100vh; " +
        		"margin:0; " +
        		"padding:0; " +
        		"}");
        out.println(".glass-card { " +
        		"background: rgba(0,0,0,0.78); " +
        		"backdrop-filter: blur(15px); " +
        		"border-radius: 20px; " +
        		"padding: 40px; " +
        		"box-shadow: 0 0 40px rgba(0,0,0,0.8); " +
        		"}");
        out.println(".back-btn { position:absolute; top:30px; left:40px; z-index:1000; }");

        out.println(".back-link { display:flex; align-items:center; justify-content:center; width:50px; height:50px; background:rgba(0,0,0,0.8); border-radius:50%; color:white; font-size:22px; text-decoration:none; box-shadow:0 0 15px rgba(0,0,0,0.6); transition:0.3s; }");

        out.println(".back-link:hover { background:linear-gradient(135deg,#667eea,#764ba2); transform:scale(1.15); }");out.println("</style>");
        out.println("</head>");

      
        out.println("<div class='container d-flex justify-content-center align-items-center' style='min-height:100vh;'>");
        out.println("<div class='glass-card w-100' style='max-width:1100px;'>");

        out.println("<h2 class='text-center text-white mb-4 fw-bold'>All Job Applications</h2>");
        out.println("<form method='get' action='ViewJobsServlet' class='row g-3 mb-4 justify-content-center px-4'>");
        out.println("<div class='col-md-5'>");
        out.println("<input type='text' name='search' class='form-control form-control-lg shadow-sm' placeholder='Search by title or company'>");
        out.println("</div>");

        out.println("<div class='col-md-3'>");
        out.println("<select name='status' class='form-select form-select-lg shadow-sm'>");
        out.println("<option value=''>All Status</option>");
        out.println("<option value='Pending'>Pending</option>");
        out.println("<option value='Selected'>Selected</option>");
        out.println("<option value='Rejected'>Rejected</option>");
        out.println("</select>");
        out.println("</div>");

        out.println("<div class='col-md-2'>");
        out.println("<button type='submit' class='btn btn-primary btn-lg w-100 shadow'>Filter</button>");
        out.println("</div>");

        out.println("</form>");

        out.println("<table class='table table-dark table-hover align-middle text-center'>");
        out.println("<thead><tr>");
        out.println("<thead><tr>");
        out.println("<th>ID</th>");
        out.println("<th>Title</th>");
        out.println("<th>Company</th>");
        out.println("<th>Applied</th>");
        out.println("<th>Deadline</th>");
        out.println("<th>Status</th>");
        out.println("<th>Actions</th>");
        out.println("</tr></thead>");
        out.println("<tbody>");
       
        try {
            Connection conn = DBConnection.getConnection();
            
            String search = request.getParameter("search");
            String status = request.getParameter("status");

            String sql = "SELECT * FROM jobs WHERE 1=1";

            List<String> params = new ArrayList<>();

            if (search != null && !search.trim().isEmpty()) {
                sql += " AND (job_title LIKE ? OR company LIKE ?)";
                params.add("%" + search + "%");
                params.add("%" + search + "%");
            }

            if (status != null && !status.trim().isEmpty()) {
                sql += " AND status = ?";
                params.add(status);
            }

            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 0; i < params.size(); i++) {
            	ps.setString(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("job_title") + "</td>");
                out.println("<td>" + rs.getString("company") + "</td>");
                out.println("<td>" + rs.getDate("applied_date") + "</td>");
                out.println("<td>" + rs.getDate("deadline") + "</td>");
                out.println("<td>" + rs.getString("status") + "</td>");
                out.println("<td>"
                        + "<a href='EditJobServlet?id=" + rs.getInt("id") + "'>Edit</a> | "
                        + "<a href='DeleteJobServlet?id=" + rs.getInt("id") + "'>Delete</a>"
                        + "</td>");
                out.println("</tr>");
            }
            out.println("</tbody></table>");
            out.println("</div></div>");

            out.println("</table>");
            out.println("</div>");   // closes glass-card
            out.println("</div>");   // closes container
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
    }
}