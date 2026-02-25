<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Job Tracker</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(rgba(0,0,0,0.8), rgba(0,0,0,0.8)),
                        url('https://images.unsplash.com/photo-1492724441997-5dc865305da7');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .glass-card {
            background: rgba(0,0,0,0.75);
            backdrop-filter: blur(15px);
            border-radius: 20px;
            padding: 40px;
            width: 100%;
            max-width: 450px;
            box-shadow: 0 0 40px rgba(0,0,0,0.9);
        }
    </style>
</head>
<body>

<div class="glass-card">
    <h2 class="text-center text-white mb-4 fw-bold">Register</h2>

    <form action="RegisterServlet" method="post">

        <div class="mb-3">
            <label class="text-white">Name</label>
            <input type="text" name="name" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="text-white">Email</label>
            <input type="email" name="email" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="text-white">Password</label>
            <input type="password" name="password" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-success w-100 mt-3">Register</button>

        <p class="text-center text-white mt-3">
            Already have an account?
            <a href="login.jsp" class="text-info">Login</a>
        </p>

    </form>
</div>

</body>
</html>