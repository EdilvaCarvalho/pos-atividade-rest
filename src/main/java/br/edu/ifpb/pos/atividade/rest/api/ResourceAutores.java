package br.edu.ifpb.pos.atividade.rest.api;

import br.edu.ifpb.pos.atividade.rest.interfaces.AutorService;
import br.edu.ifpb.pos.atividade.rest.modelo.Autor;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Edilva
 */
@Path("autores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class ResourceAutores {

    @Inject
    private AutorService autorService;

    @POST
    public Response novoAutor(JsonObject json) {
        Autor autor = new Autor(json.getString("nome"), json.getString("email"), json.getString("abreviacao"));
        if (autor == null) {
            return Response.noContent().build();
        }
        autorService.salvar(autor);
        return Response.ok().entity(autor).build();
    }

    @GET
    @Path("{id}")
    public Response autorPorId(@PathParam("id") int id) {
        Autor autor = autorService.getAutor(id);
        if (autor == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(autor).build();
    }

    @GET
    public Response todosOsAutores() {
        List<Autor> autores = autorService.listar();
        GenericEntity<List<Autor>> entity = new GenericEntity<List<Autor>>(autores) {
        };
        return Response.ok().entity(entity).build();

    }

    @DELETE
    @Path("{id}")
    public Response removerAutor(@PathParam("id") int id) {
        Autor autor = autorService.remover(id);
        if (autor == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(autor).build();
    }
    
    @PUT
    @Path("{id}")
    public Response editarAutor(@PathParam("id") int id, JsonObject json) {
        Autor autor = autorService.getAutor(id);
        autor.setEmail(json.getString("email"));
        autor.setNome(json.getString("nome"));
        autor.setAbreviacao(json.getString("abreviacao"));
        
        return Response.ok().entity(autor).build();
    }
}
