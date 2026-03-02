package com.jobtracker;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddJobServlet")
public class AddJobServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    response.sendRedirect("addjob.jsp");
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);

    	if (session == null || session.getAttribute("userId") == null) {
    	    response.sendRedirect("login.jsp");
    	    return;
    	}

        String jobTitle = request.getParameter("job_title");
        String company = request.getParameter("company");
        String status = request.getParameter("status");
        String appliedDate = request.getParameter("applied_date");
        String deadlineDate = request.getParameter("deadline");
        if (deadlineDate == null || deadlineDate.isEmpty()) {
            throw new ServletException("Deadline date is missing!");
        }

        java.sql.Date deadline = java.sql.Date.valueOf(deadlineDate);
     // Calculate reminder = deadline - 1 day
     long oneDay = 24 * 60 * 60 * 1000;
     java.sql.Date reminder = new java.sql.Date(deadline.getTime() - oneDay);

        try {
            Connection conn = DBConnection.getConnection();
            
            int userId = (int) session.getAttribute("userId");

            String sql = "INSERT INTO jobs (job_title, company, status, applied_date, deadline_date, reminder_date, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, jobTitle);
            ps.setString(2, company);
            ps.setString(3, status);
            ps.setDate(4, java.sql.Date.valueOf(appliedDate));
            ps.setDate(5, deadline);
            ps.setDate(6, reminder);
            ps.setInt(7, userId);
            
            ps.executeUpdate();

            response.getWriter().println("Job Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}