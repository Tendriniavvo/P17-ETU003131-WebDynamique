<%@ page import="entities.Prevision" %>
<%@ page import="entities.Depense" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Récapitulatif des Prévisions et Dépenses</title>
    <link rel="stylesheet" href="css/sidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="css/forms.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .montant {
            text-align: right;
        }
        .reste-positif {
            color: green;
        }
        .reste-negatif {
            color: red;
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
            <a href="depenseForm">
                <i class="fas fa-money-bill-wave"></i>
                <span>Depenses</span>
            </a>
            <a href="recapitulatif" class="active-link">
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
        <h2 class="form-title">Recapitulatif des Previsions et Depenses</h2>
        
        <table class="recap-table">
            <thead>
                <tr>
                    <th>Description</th>
                    <th>Date Prevision</th>
                    <th>Montant Prevu</th>
                    <th>Total Depenses</th>
                    <th>Reste</th>
                </tr>
            </thead>
            <tbody>
                <% 
                Map<Prevision, Double> recapitulatif = (Map<Prevision, Double>) request.getAttribute("recapitulatif");
                if (recapitulatif != null) {
                    for (Map.Entry<Prevision, Double> entry : recapitulatif.entrySet()) {
                        Prevision prevision = entry.getKey();
                        Double totalDepenses = entry.getValue();
                        double reste = prevision.getMontant() - totalDepenses;
                %>
                    <tr>
                        <td><%= prevision.getDescription() %></td>
                        <td><%= prevision.getDatePrevision() %></td>
                        <td><%= String.format("%,.2f Ar", prevision.getMontant()) %></td>
                        <td><%= String.format("%,.2f Ar", totalDepenses) %></td>
                        <td>
                            <%= String.format("%,.2f Ar", reste) %>
                        </td>
                    </tr>
                <% 
                    }
                }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
