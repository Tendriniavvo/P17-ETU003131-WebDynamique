<%@ page import="entities.Prevision" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Depenses</title>
    <link rel="stylesheet" href="css/sidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="css/forms.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        form {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="number"], input[type="date"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        select {
            width: 100%;
            padding: 8px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .nav-links {
            margin-bottom: 20px;
            text-align: center;
        }
        .nav-links a {
            margin: 0 10px;
            text-decoration: none;
            color: #4CAF50;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <div class="sidebar-header">
            <h3><i class="fas fa-chart-line"></i> Gestion Budget</h3>
        </div>
        <div class="sidebar-menu">
            <a href="PrevisionForm.jsp">
                <i class="fas fa-calendar-alt"></i>
                <span>Previsions</span>
            </a>
            <a href="depenseForm" class="active-link">
                <i class="fas fa-money-bill-wave"></i>
                <span>Depenses</span>
            </a>
            <a href="recapitulatif">
                <i class="fas fa-chart-bar"></i>
                <span>Etat</span>
            </a>
            <a href="logout">
                <i class="fas fa-sign-out-alt"></i>
                <span>Deconnexion</span>
            </a>
        </div>
    </div>

    <div class="main-content">
        <div class="form-container">
            <h2 class="form-title">Formulaire d'ajout de depense</h2>
            <form action="saveDepense" method="post">
                <% if (request.getParameter("success") != null) { %>
                    <div class="alert alert-success">La depense a ete ajoutee avec succes!</div>
                <% } %>
                <% if (request.getParameter("error") != null) { %>
                    <% if (request.getParameter("error").equals("montant")) { %>
                        <div class="alert alert-error">Le montant de la depense depasse le montant prevu!</div>
                    <% } else { %>
                        <div class="alert alert-error">Une erreur s'est produite lors de l'ajout de la depense.</div>
                    <% } %>
                <% } %>
                <div class="form-group">
                    <label class="form-label" for="prevision">Prevision associee :</label>
                    <select class="form-control" id="prevision" name="prevision_id" required>
                        <option value="">Selectionner une prevision</option>
                        <% 
                            List<Prevision> previsions = (List<Prevision>) request.getAttribute("previsions");
                            if (previsions != null) {
                                for (Prevision prev : previsions) {
                        %>
                            <option value="<%= prev.getId() %>">
                                <%= prev.getDescription() %> - <%= prev.getMontant() %>
                            </option>
                        <% 
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="form-group">
                    <label class="form-label" for="montant">Montant :</label>
                    <input type="number" class="form-control" id="montant" name="montant" step="0.01" required>
                </div>

                <div class="form-group">
                    <label class="form-label" for="date_depense">Date de la dépense :</label>
                    <input type="date" class="form-control" id="date_depense" name="date_depense" required>
                </div>

                <button type="submit" class="submit-btn">
                    <i class="fas fa-save"></i> Ajouter la dépense
                </button>
            </form>
        </div>
    </div>
</body>
</html>
