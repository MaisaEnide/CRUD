
package DAO;

public class Estoque {
    
    private int codigo;
    private String descricao;
    private String tipo;
    private double valor;
    
    public int getCodigo(){
        return this.codigo;
    }
    
    public void setCodigo (int codigo){
        this.codigo = codigo;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public double getValor(){
        return this.valor;
    }
    
    public void setValor(double valor){
        this.valor = valor;
    }
    
}
