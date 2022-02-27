//Conexão com o banco de dados

package DAO;

import java.sql.*;

public class ConnectionFactory {

    public Connection getConnection() {
        
        try {
            
            Connection conexao = DriverManager.getConnection(
            "jdbc:derby://localhost:1527/supermark",
            "app",
            "app");
            return conexao;
            
        } catch (SQLException ex){
            
            System.out.println("Falha de conexão com o banco de dados"); 
            
        }
        
        return null;
    }
    
}
