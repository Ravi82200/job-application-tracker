package com.jobtracker;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EditJobServlet")
public class EditJobServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);

    	if (session == null || session.getAttribute("userId") == null) {
    	    response.sendRedirect("login.jsp");
    	    return;
    	}

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM jobs WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	out.println("<!DOCTYPE html>");
            	out.println("<html>");
            	out.println("<head>");
            	out.println("<title>Edit Job</title>");

            	out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            	out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css'>");

            	out.println("<style>");

            	out.println("body { " +
            	"background: linear-gradient(rgba(0,0,0,0.75), rgba(0,0,0,0.75)), " +
            	" url('https://images.unsplash.com/photo-1504384308090-c894fdcc538d') \r\n"
            	+ "              no-repeat center center fixed; " +
            	"background-size: cover; " +
            	"background-position: center; " +
            	"background-attachment: fixed; " +
            	"min-height: 100vh; margin:0; padding:0;}");

            	out.println(".glass-card { background: rgba(0,0,0,0.78); backdrop-filter: blur(15px); border-radius: 20px; padding: 40px; box-shadow: 0 0 40px rgba(0,0,0,0.8); }");

            	out.println(".back-btn { position:absolute; top:30px; left:40px; }");
            	out.println(".back-link { display:flex; align-items:center; justify-content:center; width:50px; height:50px; background:rgba(0,0,0,0.8); border-radius:50%; color:white; font-size:22px; text-decoration:none; transition:0.3s; }");
            	out.println(".back-link:hover { background:linear-gradient(135deg,#667eea,#764ba2); transform:scale(1.15); }");
                out.println(".logout-btn {\r\n"
                		+ "    position: fixed;\r\n"
                		+ "    top: 20px;\r\n"
                		+ "    right: 30px;\r\n"
                		+ "    font-size: 28px;\r\n"
                		+ "    text-decoration: none;\r\n"
                		+ "    background: rgba(255,255,255,0.1);\r\n"
                		+ "    padding: 10px 14px;\r\n"
                		+ "    border-radius: 50%;\r\n"
                		+ "    backdrop-filter: blur(6px);\r\n"
                		+ "    color: white;\r\n"
                		+ "    transition: 0.3s ease;\r\n"
                		+ "}\r\n"
                		+ "\r\n"
                		+ ".logout-btn:hover {\r\n"
                		+ "    background: #ff4b2b;\r\n"
                		+ "    transform: scale(1.1);\r\n"
                		+ "}");
            	out.println("</style>");
            	out.println("</head>");
            	out.println("<body>");
            	out.println("<!-- Logout Button -->\r\n"
            			+ "<a href=\"LogoutServlet\" class=\"logout-btn\" title=\"Logout\">\r\n"
            			+ "    &#128682;\r\n"
            			+ "</a>");

            	out.println("<div class='back-btn'>");
            	out.println("<a href='ViewJobsServlet' class='back-link'><i class='bi bi-arrow-left'></i></a>");
            	out.println("</div>");

            	out.println("<div class='container d-flex justify-content-center align-items-center' style='min-height:100vh;'>");
            	out.println("<div class='glass-card w-100' style='max-width:600px;'>");

            	out.println("<h2 class='text-center text-white mb-4 fw-bold'>Edit Job</h2>");

            	out.println("<form action='UpdateJobServlet' method='post'>");

            	out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");

            	out.println("<div class='mb-3'>");
            	out.println("<label class='text-white'>Title</label>");
            	out.println("<input type='text' name='job_title' value='" + rs.getString("job_title") + "' class='form-control form-control-lg'>");
            	out.println("</div>");

            	out.println("<div class='mb-3'>");
            	out.println("<label class='text-white'>Company</label>");
            	out.println("<input type='text' name='company' value='" + rs.getString("company") + "' class='form-control form-control-lg'>");
            	out.println("</div>");

            	out.println("<div class='mb-3'>");
            	out.println("<label class='text-white'>Applied Date</label>");
            	out.println("<input type='date' name='applied_date' value='" + rs.getDate("applied_date") + "' class='form-control form-control-lg'>");
            	out.println("</div>");

            	out.println("<div class='mb-3'>");
            	out.println("<label class='text-white'>Deadline</label>");
            	out.println("<input type='date' name='deadline_date' value='" + rs.getDate("deadline") + "' class='form-control form-control-lg'>");
            	out.println("</div>");

            	out.println("<div class='mb-3'>");
            	out.println("<label class='text-white'>Status</label>");
            	out.println("<select name='status' class='form-select form-select-lg'>");
            	out.println("<option value='Pending'" + ("Pending".equals(rs.getString("status")) ? " selected" : "") + ">Pending</option>");
            	out.println("<option value='Selected'" + ("Selected".equals(rs.getString("status")) ? " selected" : "") + ">Selected</option>");
            	out.println("<option value='Rejected'" + ("Rejected".equals(rs.getString("status")) ? " selected" : "") + ">Rejected</option>");
            	out.println("</select>");
            	out.println("</div>");

            	out.println("<div class='d-flex justify-content-between mt-4'>");
            	out.println("<button type='submit' class='btn btn-success btn-lg'>Update Job</button>");
            	out.println("<a href='ViewJobsServlet' class='btn btn-outline-light btn-lg'>Cancel</a>");
            	out.println("</div>");

            	out.println("</form>");
            	out.println("</div>");
            	out.println("</div>");
            	out.println("</body>");
            	out.println("</html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}