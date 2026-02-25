package com.jobtracker;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);

    	if (session == null || session.getAttribute("userId") == null) {
    	    response.sendRedirect("login.jsp");
    	    return;
    	}

        int totalJobs = 0;
        int pendingJobs = 0;
        int selectedJobs = 0;
        int rejectedJobs = 0;

        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();

         // Total jobs
         ResultSet totalRs = stmt.executeQuery("SELECT COUNT(*) FROM jobs");
         if (totalRs.next()) {
             totalJobs = totalRs.getInt(1);
         }

         // Pending
         ResultSet pendingRs = stmt.executeQuery("SELECT COUNT(*) FROM jobs WHERE status='Pending'");
         if (pendingRs.next()) {
             pendingJobs = pendingRs.getInt(1);
         }

         // Selected
         ResultSet selectedRs = stmt.executeQuery("SELECT COUNT(*) FROM jobs WHERE status='Selected'");
         if (selectedRs.next()) {
             selectedJobs = selectedRs.getInt(1);
         }

         // Rejected
         ResultSet rejectedRs = stmt.executeQuery("SELECT COUNT(*) FROM jobs WHERE status='Rejected'");
         if (rejectedRs.next()) {
             rejectedJobs = rejectedRs.getInt(1);
         }

         PreparedStatement reminderPs = conn.prepareStatement(
        		    "SELECT job_title, deadline FROM jobs " +
        		    "WHERE deadline = CURDATE() OR deadline = DATE_ADD(CURDATE(), INTERVAL 1 DAY)"
        		);

        		ResultSet reminderRs = reminderPs.executeQuery();

        		List<String> reminders = new ArrayList<>();

        		while (reminderRs.next()) {
        		    String title = reminderRs.getString("job_title");
        		    Date deadline = reminderRs.getDate("deadline");

        		    LocalDate today = LocalDate.now();
        		    LocalDate jobDeadline = deadline.toLocalDate();

        		    String message;

        		    if (jobDeadline.equals(today)) {
        		        message = title + " - Deadline Today";
        		    } else {
        		        message = title + " - Deadline Tomorrow";
        		    }

        		    reminders.add(message);
        		}

        		request.setAttribute("reminders", reminders);

        } catch (Exception e) {
            e.printStackTrace();
        }

     
        
        request.setAttribute("totalJobs", totalJobs);
        request.setAttribute("pendingJobs", pendingJobs);
        request.setAttribute("selectedJobs", selectedJobs);
        request.setAttribute("rejectedJobs", rejectedJobs);
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}