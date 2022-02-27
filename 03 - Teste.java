
package DAO;

import java.util.*;

public class Teste {
    
    public static void main (String[] args){
    
    EstoqueDao dao = new EstoqueDao();
    ArrayList <Estoque> listaProdutos = dao.getLista();
    
    for (Estoque estoque : listaProdutos)
        System.out.println ("Codigo: " + estoque.getCodigo()+ ", "+
                "Descricao: " + estoque.getDescricao()+", "+
                "Tipo: " + estoque.getTipo()+", "+
                "Valor: " + estoque.getValor());
    }
}
