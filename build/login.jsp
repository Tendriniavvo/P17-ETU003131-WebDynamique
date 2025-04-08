<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession != null && "true".equals(currentSession.getAttribute("auth"))) {
        response.sendRedirect("welcome.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion - Gestion des Prévisions</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            margin: 0;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .login-container {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 8px 24px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
        }
        .login-header {
            text-align: center;
            margin-bottom: 2rem;
        }
        .login-header i {
            font-size: 3rem;
            color: #34495e;
            margin-bottom: 1rem;
        }
        .login-header h2 {
            color: #2c3e50;
            margin: 0;
        }
        .input-group {
            position: relative;
            margin-bottom: 1.5rem;
        }
        .input-group i {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #666;
        }
        .input-group input {
            width: 100%;
            padding: 12px 15px 12px 45px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 1rem;
            transition: border-color 0.3s;
        }
        .input-group input:focus {
            border-color: #34495e;
            outline: none;
        }
        .login-btn {
            width: 100%;
            padding: 12px;
            background-color: #34495e;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .login-btn:hover {
            background-color: #34495e;
        }
        .login-btn i {
            margin-right: 8px;
        }
        .credentials-hint {
            text-align: center;
            margin-top: 1rem;
            padding: 1rem;
            background: #f8f9fa;
            border-radius: 5px;
            font-size: 0.9rem;
            color: #666;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <i class="fas fa-user-circle"></i>
            <h2>Connexion</h2>
        </div>
        
        <form action="login" method="post" class="login-form">
            <div class="input-group">
                <i class="fas fa-user"></i>
                <input type="text" id="username" name="username" placeholder="Nom d'utilisateur" required>
            </div>
            
            <div class="input-group">
                <i class="fas fa-lock"></i>
                <input type="password" id="password" name="password" placeholder="Mot de passe" required>
            </div>
            
            <button type="submit" class="login-btn">
                <i class="fas fa-sign-in-alt"></i>Se connecter
            </button>
        </form>

        <div class="credentials-hint">
            <p><i class="fas fa-info-circle"></i> Utilisez admin/admin pour vous connecter</p>
        </div>
    </div>
</body>
</html>