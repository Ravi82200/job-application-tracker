<!DOCTYPE html>
<html>
<div class="back-btn">
    <a href="DashboardServlet" class="back-link">
        <i class="bi bi-arrow-left"></i>
    </a>
</div>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
<style>
body {
    background: url('https://images.unsplash.com/photo-1504384308090-c894fdcc538d') 
              no-repeat center center fixed;
    background-size: cover;
    font-family: 'Poppins', sans-serif;
}

.glass-card {
    background: rgba(0, 0, 0, 0.85);
    backdrop-filter: blur(15px);
    border-radius: 25px;
    padding: 50px;
    width: 100%;
    max-width: 550px;
    box-shadow: 0 0 50px rgba(0,0,0,0.8);
    animation: fadeIn 0.8s ease-in-out;
}

@keyframes fadeIn {
    from { opacity: 0; transform: translateY(30px); }
    to { opacity: 1; transform: translateY(0); }
}

.back-btn {
    position: absolute;
    top: 25px;
    left: 30px;
}

.back-link {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 45px;
    height: 45px;
    background: rgba(0,0,0,0.7);
    border-radius: 50%;
    color: #ffffff;
    font-size: 20px;
    text-decoration: none;
    backdrop-filter: blur(10px);
    transition: 0.3s ease;
}

.back-link:hover {
    background: linear-gradient(135deg, #667eea, #764ba2);
    transform: scale(1.1);
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
    <title>Add Job</title>
    
</head>
<body>
<!-- Logout Button -->
<a href="LogoutServlet" class="logout-btn" title="Logout">
    &#128682;
</a>

<div class="container d-flex justify-content-center align-items-center" style="min-height:100vh;">

    <div class="glass-card">

        <h2 class="text-center mb-4 fw-bold text-white">Add New Job</h2>

        <form action="AddJobServlet" method="post">

            <div class="mb-3">
                <label class="form-label text-light">Job Title</label>
                <input type="text" name="job_title" class="form-control form-control-lg" required>
            </div>

            <div class="mb-3">
                <label class="form-label text-light">Company</label>
                <input type="text" name="company" class="form-control form-control-lg" required>
            </div>

            <div class="mb-3">
                <label class="form-label text-light">Status</label>
                <select name="status" class="form-select form-select-lg">
                    <option value="Pending">Pending</option>
                    <option value="Selected">Selected</option>
                    <option value="Rejected">Rejected</option>
                </select>
            </div>
            
            <div class="mb-3">
    <label class="form-label text-light">Applied Date</label>
    <input type="date" name="applied_date" 
           class="form-control form-control-lg"
           value="<%= java.time.LocalDate.now() %>" required>
</div>

            <div class="mb-4">
                <label class="form-label text-light">Deadline</label>
                <input type="date" name="deadline" class="form-control form-control-lg">
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-success btn-lg px-4">Save Job</button>
                <a href="DashboardServlet" class="btn btn-outline-light btn-lg ms-2">Cancel</a>
            </div>

        </form>

    </div>

</div>

</body>
</html>