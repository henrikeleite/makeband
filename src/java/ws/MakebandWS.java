/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import modelo.Pesquisa;
import modelo.Usuario;

/**
 * REST Web Service
 *
 * @author 15245533
 */
@Path("makeband")
public class MakebandWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FazendaWS
     */
    public MakebandWS() {
    }

    /**
     * Retrieves representation of an instance of crud.FazendaWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/text")
    public String getJson() {
        return "meu primeiro WS RESTFUL";
    }
    @GET
    @Produces("application/json")
    @Path("Usuario/lista")
    public String getLista(){
        List<Usuario> lista = new ArrayList<Usuario>();
        
        Usuario u = new Usuario();
        u.setEmail("dfadfad@dfad.com");
        //u.setLogin("dfadfa");
        u.setSenha("123");
        //u.setPerfil("administrador");
        
        lista.add(u);
        
        u = new Usuario();
        u.setEmail("dfadfad@dfad.com");
        //u.setLogin("errou");
        u.setSenha("123");
        //u.setPerfil("administrador");
        
        lista.add(u);
        
        // Converter para Gson
        Gson g = new Gson();
        return g.toJson(lista);
    }
    @GET
    @Produces("application/json")
    @Path("Usuario/get/{email}/{senha}")
    public String getUsuario(@PathParam("email") String email, @PathParam("senha") String senha){
        
        Usuario u = new Usuario();
        
        u.setEmail(email);
        u.setSenha(senha);
        UsuarioDAO dao = new UsuarioDAO();
        
        u = dao.login(u);
        System.out.println(u.toString());
        // Converter para Gson
        Gson g = new Gson();
        return g.toJson(u);
    }

    /**
     * PUT method for updating or creating an instance of FazendaWS
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
        
    }
    @POST
    @Consumes({"application/json"})
    @Path("Usuario/inserir")
    public void inserir(String content){
        Gson g = new Gson();
        Usuario u = (Usuario) g.fromJson(content, Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();
        dao.inserir(u);
    }
    @DELETE
    @Path("Usuario/excluir/{nome}")
    public boolean excluir(@PathParam("nome") String nome){
        Usuario u = new Usuario();
        u.setNome(nome);
        UsuarioDAO dao = new UsuarioDAO();
        u = dao.buscar(u);
        System.out.println(u.getNome());
        return dao.excluir(u);
    }
    @GET
    @Produces("application/json")
    @Path("Pesquisa/filtro/get/{pesquisa}/{filtro}")
    public String getPesquisaFiltro(@PathParam("pesquisa") String pesquisa,@PathParam("filtro") String filtro){
        Pesquisa busca = new Pesquisa();
        busca.setPesquisa(pesquisa);
        busca.setFiltro(filtro);
        return "oi";
    }
}
