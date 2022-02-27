
package DAO;

import java.sql.*;
import java.util.*;



public class EstoqueDao {
    
    //Atributos
    private Connection connection;
    
    //Construtor
    public EstoqueDao(){
        
        this.connection = new ConnectionFactory().getConnection();
        
    }
    
    //Metodos
    
    //Retorna produtos da tabela Estoque
    public ArrayList <Estoque> getLista(){
       String sql = "SELECT * from Estoque";
       ArrayList<Estoque> produtos = new ArrayList<Estoque>();
       
       try {
           PreparedStatement stmt = this.connection.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while (rs.next()) {
               
               Estoque estoque = new Estoque();
               
               estoque.setCodigo(rs.getInt("codigo"));
               estoque.setDescricao(rs.getString("descricao"));
               estoque.setTipo(rs.getString("tipo"));
               estoque.setValor(rs.getDouble("valor"));
               
               produtos.add(estoque);
                  
           }
           
           rs.close();
           stmt.close();
           
       } catch (SQLException e) {
           
           throw new RuntimeException (e);
           
       }
       
       return produtos;
    } 
    
    //Retorna um produto da tabela Estoque conforme ID informado
    public Estoque consulta(int codigo){
        String sql = "SELECT * FROM Estoque WHERE codigo = ?";
        Estoque estoque = null;
        try{
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                estoque = new Estoque();
                estoque.setCodigo(rs.getInt("codigo"));
                estoque.setDescricao(rs.getString("descricao"));
                estoque.setTipo(rs.getString("tipo"));
                estoque.setValor(rs.getDouble("valor"));   
            }
            rs.close();
            stmt.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return estoque;
    }
    
    //Adiciona novo produto na tabela Estoque
    public void adiciona (Estoque estoque){
        String sql = "INSERT INTO Estoque VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            
            stmt.setInt (1, estoque.getCodigo());
            stmt.setString (2, estoque.getDescricao());
            stmt.setString (3, estoque.getTipo());
            stmt.setDouble (4, estoque.getValor());
        
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //Atualiza a tabela Estoque
    public boolean atualiza(Estoque estoque){
        String sql = "UPDATE Estoque SET codigo = ?," +
                "descricao = ?, tipo = ?, valor = ?";
        int produtosAtualizados = 0;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, estoque.getCodigo());
            stmt.setString(2, estoque.getDescricao());
            stmt.setString(3, estoque.getTipo());
            stmt.setDouble(4, estoque.getValor());
            produtosAtualizados = stmt.executeUpdate();
            stmt.close();
            
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return produtosAtualizados == 1;
    }
    
    //Deleta um produto da tabela Estoque
    public boolean remove(int codigo){
        String sql = "DELETE FROM Estoque WHERE codigo = ?";
        int produtosRemovidos = 0;
        try{
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, codigo);
            produtosRemovidos = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return produtosRemovidos == 1;
    }
   
}
