<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Previsions</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            margin: 0;
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        .container {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 8px 24px rgba(0,0,0,0.1);
            text-align: center;
            max-width: 500px;
            width: 90%;
        }
        h1 {
            color: #2c3e50;
            margin-bottom: 1.5rem;
        }
        .credentials-info {
            background: #f8f9fa;
            padding: 1rem;
            border-radius: 5px;
            margin: 1.5rem 0;
            text-align: left;
        }
        .credentials-info p {
            margin: 0.5rem 0;
            color: #666;
        }
        .credentials-info strong {
            color: #2c3e50;
        }
        .welcome-button {
            display: inline-block;
            padding: 12px 24px;
            background-color: #34495e;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: background-color 0.3s;
            margin-top: 1rem;
        }
        .welcome-button:hover {
            background-color: #34495e;
        }
        .welcome-button i {
            margin-right: 8px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Gestion des Previsions et Depenses</h1>
        
        <div class="credentials-info">
            <h3><i class="fas fa-info-circle"></i> Informations de connexion</h3>
            <p><strong>Nom d'utilisateur :</strong> admin</p>
            <p><strong>Mot de passe :</strong> admin</p>
        </div>

        <a href="login.jsp" class="welcome-button">
            <i class="fas fa-sign-in-alt"></i>
            Commencer
        </a>
    </div>
</body>
</html>
