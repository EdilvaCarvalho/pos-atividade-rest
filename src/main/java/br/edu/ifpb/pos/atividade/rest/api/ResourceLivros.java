package br.edu.ifpb.pos.atividade.rest.api;

import br.edu.ifpb.pos.atividade.rest.interfaces.AutorService;
import br.edu.ifpb.pos.atividade.rest.interfaces.LivroService;
import br.edu.ifpb.pos.atividade.rest.modelo.Livro;
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
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Edilva
 */
@Path("livros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class ResourceLivros {

    @Inject
    private LivroService livroService;

    @Inject
    private AutorService autorService;

    @Context
    private ResourceContext context;

    @POST
    public Response novoLivro(JsonObject json) {
        Livro livro = new Livro(json.getString("edicao"), json.getString("descricao"), json.getString("titulo"));
        if (livro == null) {
            return Response.noContent().build();
        }
        livroService.salvar(livro);
        return Response.ok().entity(livro).build();
    }

    @GET
    @Path("{id}")
    public Response livroPorId(@PathParam("id") int id) {
        Livro livro = livroService.getLivro(id);
        if (livro == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(livro).build();
    }

    @GET
    public Response todosOsLivros() {
        List<Livro> livros = livroService.listar();
        GenericEntity<List<Livro>> entity = new GenericEntity<List<Livro>>(livros) {
        };
        return Response.ok().entity(entity).build();

    }

    @DELETE
    @Path("{id}")
    public Response removerLivro(@PathParam("id") int id) {
        Livro livro = livroService.remover(id);
        if (livro == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(livro).build();
    }

    @PUT
    @Path("{id}")
    public Response editarLivro(@PathParam("id") int id, JsonObject json) {
        Livro livro = livroService.getLivro(id);
        livro.setEdicao(json.getString("edicao"));
        livro.setTitulo(json.getString("titulo"));
        livro.setDescricao(json.getString("descricao"));
        //livroService.atualizar(livro);

        return Response.ok().entity(livro).build();
    }

    @Path("{id}/reservas")
    public SubResourceReserva exibirReserva(@PathParam("id") int id) {
        return this.context.initResource(new SubResourceReserva(livroService, id));
    }

    @Path("{id}/autores")
    public SubResourceAutor exibirAutor(@PathParam("id") int id) {
        return this.context.initResource(new SubResourceAutor(livroService, autorService, id));
    }

}
