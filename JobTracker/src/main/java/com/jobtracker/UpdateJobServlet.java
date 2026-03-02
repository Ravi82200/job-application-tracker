package com.jobtracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UpdateJobServlet")
public class UpdateJobServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    response.sendRedirect("dashboard.jsp");
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);

    	if (session == null || session.getAttribute("userId") == null) {
    	    response.sendRedirect("login.jsp");
    	    return;
    	}

        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("job_title");
        String company = request.getParameter("company");
        String status = request.getParameter("status");
        String appliedDateStr = request.getParameter("applied_date");
        String deadlineDateStr = request.getParameter("deadline_date");

        java.sql.Date appliedDate = java.sql.Date.valueOf(appliedDateStr);
        java.sql.Date deadlineDate = java.sql.Date.valueOf(deadlineDateStr);

        // Recalculate reminder (1 day before deadline)
        long oneDay = 24 * 60 * 60 * 1000;
        java.sql.Date reminderDate = new java.sql.Date(deadlineDate.getTime() - oneDay);

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
            	    "UPDATE jobs SET job_title=?, company=?, status=?, applied_date=?, deadline=?, reminder_date=? WHERE id=?");
            ps.setString(1, title);
            ps.setString(2, company);
            ps.setString(3, status);
            ps.setDate(4, appliedDate);
            ps.setDate(5, deadlineDate);
            ps.setDate(6, reminderDate);
            ps.setInt(7, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("ViewJobsServlet");
    }
}