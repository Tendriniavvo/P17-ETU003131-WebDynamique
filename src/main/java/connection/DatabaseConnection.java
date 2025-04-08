package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_s2_ETU003131";
    private static final String USER = "ETU003131";
    private static final String PASSWORD = "JupysPHH";
    private Connection connection;

    public DatabaseConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

    public void closeConnexion(){
        try{
            if(this.connection != null && !this.connection.isClosed()){
                this.connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
   
}