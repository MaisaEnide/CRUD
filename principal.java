
package WS;

import DAO.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author maisa
 */
@Path("estoque")
public class principal {

    @Context
    private UriInfo context;
    
    //Métodos
    
    //Retorna lista de produtos da tabela Estoque
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("produtos")
    public String getProdutos() {
       EstoqueDao dao = new EstoqueDao();
       ArrayList <Estoque> listaProdutos = dao.getLista();
       Gson gson = new Gson();
       return gson.toJson(listaProdutos);   
    }
    
    //Retorna produto da tabela Estoque
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("produto/{codigo}")
    public String getEstoque(@PathParam("codigo") int codigo){
        EstoqueDao dao = new EstoqueDao();
        Estoque estoque = dao.consulta(codigo);
        if (estoque != null){
            Gson gson = new Gson();
            return gson.toJson(estoque);
        } else 
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    
    //Adiociona um novo produto na tabela Estoque       
    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("inserir")
    public Response adiciona(String content) {
        Gson gson = new Gson();
        Estoque estoque = (Estoque) gson.fromJson(content, Estoque.class);
        EstoqueDao dao = new EstoqueDao();
        try{
           dao.adiciona(estoque);
        }catch(RuntimeException e){
           return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();   
    }
    
    //Atualiza a tabela Estoque
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("atualizar")
    public Response setEstoque(String content) {
        Gson gson = new Gson();
        Estoque estoque = (Estoque) gson.fromJson(content, Estoque.class);
        EstoqueDao dao = new EstoqueDao();
        try{
            //Testa se conseguiu atualizar a tabela
            if(dao.atualiza(estoque))
                return Response.status(Response.Status.OK).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        }
        catch (RuntimeException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    //Deleta um produto da tebela Estoque
    @DELETE
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("deletaproduto/{codigo}")
    public Response delEstoque(@PathParam("codigo") int codigo){
        EstoqueDao dao = new EstoqueDao();
        if(dao.remove(codigo)){
            return Response.status(Response.Status.OK).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    } 
}
