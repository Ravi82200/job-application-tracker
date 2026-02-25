<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;800&display=swap" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <div class="text-center mt-4 mb-5">
    <h1 class="hero-title">Job Application Tracker</h1>
   <p class="hero-sub">Track &bull; Analyze &bull; Succeed</p>
</div>
<%
    List<String> reminders = (List<String>) request.getAttribute("reminders");
    if (reminders != null && !reminders.isEmpty()) {
%>

<div class="reminder-container">
    <h5 class="reminder-title">
        <i class="bi bi-bell-fill"></i> Upcoming Deadlines
    </h5>

    <% for (String r : reminders) { %>
        <div class="reminder-item">
            <i class="bi bi-exclamation-circle-fill text-danger"></i>
            <span><%= r %></span>
        </div>
    <% } %>
</div>

<%
    }
%>
<style>
.reminder-container {
    background: rgba(255, 0, 0, 0.08);
    border: 1px solid rgba(255, 0, 0, 0.3);
    backdrop-filter: blur(10px);
    padding: 20px;
    border-radius: 15px;
    margin-bottom: 30px;
    max-width: 700px;
    margin-left: auto;
    margin-right: auto;
}

.reminder-title {
    color: #ff6b6b;
    font-weight: 600;
    margin-bottom: 15px;
    text-align: center;
}

.reminder-item {
    background: rgba(255, 255, 255, 0.05);
    padding: 10px 15px;
    border-radius: 10px;
    margin-bottom: 10px;
    color: #ffffff;
    display: flex;
    align-items: center;
    gap: 10px;
    transition: 0.3s ease;
}

.reminder-item:hover {
    background: rgba(255, 255, 255, 0.12);
    transform: translateX(5px);
}
.reminder-box {
    background: rgba(255, 69, 58, 0.15);
    border-left: 5px solid #ff453a;
    padding: 15px;
    border-radius: 10px;
    margin-bottom: 20px;
    color: #ffffff;
}

.reminder-item {
    font-weight: 500;
    padding: 5px 0;
}
body {
    font-family: 'Poppins', sans-serif;
}
body {
    background: linear-gradient(
                rgba(0, 0, 0, 0.80),
                rgba(0, 0, 0, 0.80)
              ),
              url('https://images.unsplash.com/photo-1504384308090-c894fdcc538d') 
              no-repeat center center fixed;
    background-size: cover;
}
.hero-title {
    font-size: 3.2rem;
    font-weight: 800;
    letter-spacing: 2px;
    text-align: center;
    color: #ffffff;
    text-shadow: 0 0 15px rgba(0, 123, 255, 0.8);
}

.hero-sub {
    text-align: center;
    color: rgba(255,255,255,0.85);
    font-size: 1.1rem;
    letter-spacing: 1px;
    margin-top: 10px;
}
</style>
<style>
.glass {
    background: rgba(20, 20, 20, 0.75);
    backdrop-filter: blur(15px);
    border-radius: 20px;
    box-shadow: 0 8px 32px rgba(0,0,0,0.6);
    color: #ffffff;
}
</style>
<style>
.card {
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    border-radius: 15px;
}

.card:hover {
    transform: translateY(-8px);
    box-shadow: 0 15px 30px rgba(0,0,0,0.4);
}
.stat-card {
    padding: 25px;
    border-radius: 20px;
    color: white;
    text-align: center;
    transition: all 0.3s ease;
    box-shadow: 0 10px 25px rgba(0,0,0,0.4);
}

.stat-card h2 {
    font-size: 2.2rem;
    font-weight: 700;
}

.stat-card:hover {
    transform: translateY(-10px);
    box-shadow: 0 20px 40px rgba(0,0,0,0.6);
}

.total-card {
    background: linear-gradient(135deg, #667eea, #764ba2);
}

.pending-card {
    background: linear-gradient(135deg, #f7971e, #ffd200);
}

.selected-card {
    background: linear-gradient(135deg, #00b09b, #96c93d);
}

.rejected-card {
    background: linear-gradient(135deg, #ff416c, #ff4b2b);
}
.logout-btn {
    position: fixed;
    top: 20px;
    right: 30px;
    font-size: 28px;
    text-decoration: none;
    background: rgba(255,255,255,0.1);
    padding: 10px 14px;
    border-radius: 50%;
    backdrop-filter: blur(6px);
    color: white;
    transition: 0.3s ease;
}

.logout-btn:hover {
    background: #ff4b2b;
    transform: scale(1.1);
}
</style>

</head>
<body>
<!-- Logout Button -->
<a href="LogoutServlet" class="logout-btn" title="Logout">
    &#128682;
</a>
<div class="container mt-5 p-4 glass">


<h2 class="mb-4 text-center text-white fw-bold">Dashboard Stats</h2>

<div class="row text-center mb-4">

   <div class="row text-center mb-4">

    <!-- Total -->
    <div class="col-md-3">
        <div class="stat-card total-card">
            <h6>Total Jobs</h6>
            <h2>${totalJobs}</h2>
        </div>
    </div>

    <!-- Pending -->
    <div class="col-md-3">
        <div class="stat-card pending-card">
            <h6>Pending</h6>
            <h2>${pendingJobs}</h2>
        </div>
    </div>

    <!-- Selected -->
    <div class="col-md-3">
        <div class="stat-card selected-card">
            <h6>Selected</h6>
            <h2>${selectedJobs}</h2>
        </div>
    </div>

    <!-- Rejected -->
    <div class="col-md-3">
        <div class="stat-card rejected-card">
            <h6>Rejected</h6>
            <h2>${rejectedJobs}</h2>
        </div>
    </div>

</div>

</div>
<h4 class="mt-5 mb-3 text-center">Job Status Distribution</h4>

<div class="row justify-content-center">
     <div class="col-md-6 p-4 rounded shadow" style="background:#1e1e1e;">
        <canvas id="jobChart" style="height:350px;"></canvas>
    </div>
</div>
<hr>

<div class="mt-4">
    <a href="addJob.jsp" class="btn btn-primary me-2">Add Job</a>
    <a href="ViewJobsServlet" class="btn btn-dark">View All Jobs</a>
</div>

<script>
const ctx = document.getElementById('jobChart').getContext('2d');

new Chart(ctx, {
    type: 'pie',
    data: {
        labels: ['Pending', 'Selected', 'Rejected'],
        datasets: [{
            data: [
                ${pendingJobs},
                ${selectedJobs},
                ${rejectedJobs}
            ],
            backgroundColor: [
                '#f39c12',
                '#2ecc71',
                '#e74c3c'
            ]
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        layout: {
            padding: 20
        },
        plugins: {
            legend: {
                position: 'bottom'
            }
        }
    }
});
</script>
</div>
</body>
</html>