package entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException; // Import the ServletException class
import connection.*;

public class Depense extends BaseObject {
    private int previsionId;
    private double montant;
    private Date dateDepense;

    public Depense() {
        super();
    }

    public Depense(int previsionId, double montant, Date dateDepense) {
        super();
        this.previsionId = previsionId;
        this.montant = montant;
        this.dateDepense = dateDepense;
    }

    // Getters and Setters
    public int getPrevisionId() {
        return previsionId;
    }

    public void setPrevisionId(int previsionId) {
        this.previsionId = previsionId;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateDepense() {
        return dateDepense;
    }

    public void setDateDepense(Date dateDepense) {
        this.dateDepense = dateDepense;
    }

    @Override
    public void save() throws SQLException, ServletException {
        String query = "INSERT INTO Depense (id_prevision, montant, date_depense) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, this.previsionId);
            pstmt.setDouble(2, this.montant);
            pstmt.setDate(3, this.dateDepense);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                this.setId(rs.getInt(1));
            }
            System.out.println("Depense saved successfully");
        } catch (SQLException e) {
            throw new ServletException("Error saving depense", e);
        }
    }

    @Override
    public void update() throws SQLException, ServletException {
        String query = "UPDATE Depense SET prevision_id = ?, montant = ?, date_depense = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, this.previsionId);
            pstmt.setDouble(2, this.montant);
            pstmt.setDate(3, this.dateDepense);
            pstmt.setInt(4, this.getId());
            pstmt.executeUpdate();
            System.out.println("Depense updated successfully");
        } catch (SQLException e) {
            throw new ServletException("Error updating depense", e);
        }
    }

    @Override
    public void delete() throws SQLException, ServletException {
        String query = "DELETE FROM Depense WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, this.getId());
            pstmt.executeUpdate();
            System.out.println("Depense deleted successfully");
        } catch (SQLException e) {
            throw new ServletException("Error deleting depense", e);
        }
    }

    public static Depense getById(int id) throws SQLException, ServletException {
        String query = "SELECT * FROM Depense WHERE id = ?";
        DatabaseConnection db = new DatabaseConnection();
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Depense depense = new Depense(
                        rs.getInt("prevision_id"),
                        rs.getDouble("montant"),
                        rs.getDate("date_depense")
                    );
                    depense.setId(rs.getInt("id"));
                    return depense;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Error retrieving depense by ID", e);
        }
    }

    public static List<Depense> findAll() throws SQLException, ServletException {
        List<Depense> depenses = new ArrayList<>();
        String query = "SELECT * FROM Depense";
        DatabaseConnection db = new DatabaseConnection();
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Depense depense = new Depense(
                    rs.getInt("prevision_id"),
                    rs.getDouble("montant"),
                    rs.getDate("date_depense")
                );
                depense.setId(rs.getInt("id"));
                depenses.add(depense);
            }
        } catch (SQLException e) {
            throw new ServletException("Error retrieving all depenses", e);
        }
        return depenses;
    }

    public static double getTotalDepensesByPrevisionId(int previsionId) throws SQLException, ServletException {
        String query = "SELECT SUM(montant) as total FROM Depense WHERE id_prevision = ?";
        DatabaseConnection db = new DatabaseConnection();
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, previsionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
                return 0.0;
            }
        } catch (SQLException e) {
            throw new ServletException("Error calculating total depenses", e);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("=== Test de la méthode save() ===");
            
            
            int previsionId = 1; 
            double montant = 500.0;
            Date dateDepense = Date.valueOf("2024-02-15");
            
            Depense depense = new Depense(previsionId, montant, dateDepense);
            
            // Test de la sauvegarde
            System.out.println("Tentative de sauvegarde d'une nouvelle dépense...");
            System.out.println("Prévision ID : " + previsionId);
            System.out.println("Montant : " + montant + " Ar");
            System.out.println("Date : " + dateDepense);
            
            depense.save();
            
            System.out.println("Sauvegarde réussie !");
            System.out.println("ID généré : " + depense.getId());
            
            // Vérifier si la dépense a été sauvegardée en la récupérant
            Depense savedDepense = Depense.getById(depense.getId());
            if (savedDepense != null) {
                System.out.println("\nVérification de la sauvegarde :");
                System.out.println("ID : " + savedDepense.getId());
                System.out.println("Prévision ID : " + savedDepense.getPrevisionId());
                System.out.println("Montant : " + savedDepense.getMontant() + " Ar");
                System.out.println("Date : " + savedDepense.getDateDepense());
            }
            
        } catch (SQLException | ServletException e) {
            System.err.println("Erreur lors du test de save() :");
            e.printStackTrace();
        }
    }
}