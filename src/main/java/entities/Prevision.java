package entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException; // Import the ServletException class
import connection.*;

public class Prevision extends BaseObject {
    private String description;
    private double montant;
    private Date datePrevision;

    public Prevision() {
        super();
    }

    public Prevision(String description, double montant, Date datePrevision) {
        super();
        this.description = description;
        this.montant = montant;
        this.datePrevision = datePrevision;
    }

    // Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDatePrevision() {
        return datePrevision;
    }

    public void setDatePrevision(Date datePrevision) {
        this.datePrevision = datePrevision;
    }

    @Override
    public void save() throws SQLException, ServletException {
        String query = "INSERT INTO Prevision (description, montant, date_prevision) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, this.description);
            pstmt.setDouble(2, this.montant);
            pstmt.setDate(3, this.datePrevision);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                this.setId(rs.getInt(1));
            }
            System.out.println("Prevision saved successfully");
        } catch (SQLException e) {
            throw new ServletException("Error saving prevision", e);
        }
    }

    @Override
    public void update() throws SQLException, ServletException {
        String query = "UPDATE Prevision SET description = ?, montant = ?, date_prevision = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, this.description);
            pstmt.setDouble(2, this.montant);
            pstmt.setDate(3, this.datePrevision);
            pstmt.setInt(4, this.getId());
            pstmt.executeUpdate();
            System.out.println("Prevision updated successfully");
        } catch (SQLException e) {
            throw new ServletException("Error updating prevision", e);
        }
    }

    @Override
    public void delete() throws SQLException, ServletException {
        String query = "DELETE FROM Prevision WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, this.getId());
            pstmt.executeUpdate();
            System.out.println("Prevision deleted successfully");
        } catch (SQLException e) {
            throw new ServletException("Error deleting prevision", e);
        }
    }

    public static Prevision getById(int id) throws SQLException, ServletException {
        String query = "SELECT * FROM Prevision WHERE id = ?";
        DatabaseConnection db = new DatabaseConnection();
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Prevision prevision = new Prevision(
                        rs.getString("description"),
                        rs.getDouble("montant"),
                        rs.getDate("date_prevision")
                    );
                    prevision.setId(rs.getInt("id"));
                    return prevision;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Error retrieving prevision by ID", e);
        }
    }

    public static double getMontantById(int id) throws SQLException, ServletException {
        String query = "SELECT montant FROM Prevision WHERE id = ?";
        DatabaseConnection db = new DatabaseConnection();
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("montant");
                } else {
                    throw new SQLException("No prevision found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Error retrieving montant by ID", e);
        }
    }

    public static List<Prevision> findAll() throws SQLException, ServletException {
        List<Prevision> previsions = new ArrayList<>();
        String query = "SELECT * FROM Prevision";
        DatabaseConnection db = new DatabaseConnection();
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Prevision prevision = new Prevision(
                    rs.getString("description"),
                    rs.getDouble("montant"),
                    rs.getDate("date_prevision")
                );
                prevision.setId(rs.getInt("id"));
                previsions.add(prevision);
            }
        } catch (SQLException e) {
            throw new ServletException("Error retrieving all previsions", e);
        }
        return previsions;
    }

    public static void main(String[] args) {
        try {
            // Test de la méthode findAll()
            System.out.println("=== Test de la méthode findAll() ===");
            List<Prevision> previsions = Prevision.findAll();
            
            if (previsions.isEmpty()) {
                System.out.println("Aucune prévision trouvée dans la base de données.");
            } else {
                System.out.println("Liste des prévisions :");
                System.out.println("---------------------");
                for (Prevision prev : previsions) {
                    System.out.println("ID : " + prev.getId());
                    System.out.println("Description : " + prev.getDescription());
                    System.out.println("Montant : " + prev.getMontant() + " Ar");
                    System.out.println("Date : " + prev.getDatePrevision());
                    System.out.println("---------------------");
                }
                System.out.println("Total : " + previsions.size() + " prévision(s)");
            }

           
            int testId = 1;
            double montant = Prevision.getMontantById(testId);
            System.out.println("Montant de la prévision avec l'ID " + testId + " : " + montant + " Ar");

        } catch (SQLException | ServletException e) {
            System.err.println("Erreur lors du test :");
            e.printStackTrace();
        }
    }
}