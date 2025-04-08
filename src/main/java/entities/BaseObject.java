package entities;

import java.sql.*;
import connection.DatabaseConnection;
import jakarta.servlet.ServletException; 

public abstract class BaseObject {
    protected int id;
    protected DatabaseConnection db;

    public BaseObject() {
        this.db = new DatabaseConnection();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected Connection getConnection() throws SQLException {
        return db.getConnection();
    }


    public abstract void save() throws SQLException, ServletException;
    public abstract void update() throws SQLException, ServletException;
    public abstract void delete() throws SQLException, ServletException;
}