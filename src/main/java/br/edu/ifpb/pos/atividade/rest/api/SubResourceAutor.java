package br.edu.ifpb.pos.atividade.rest.api;

import br.edu.ifpb.pos.atividade.rest.interfaces.AutorService;
import br.edu.ifpb.pos.atividade.rest.interfaces.LivroService;
import br.edu.ifpb.pos.atividade.rest.modelo.Autor;
import br.edu.ifpb.pos.atividade.rest.modelo.Livro;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubResourceAutor {

    private LivroService livroService;
    private AutorService autorService;
    private int idLivro;

    public SubResourceAutor(LivroService livroService, AutorService autorService, int idLivro) {
        this.livroService = livroService;
        this.autorService = autorService;
        this.idLivro = idLivro;
    }

    @PUT
    public Response addAutor(JsonObject json) {
       
        int idAutor = json.getInt("id");
        Autor autor = this.autorService.getAutor(idAutor);
        Livro livro = this.livroService.addAutor(this.idLivro, autor);
        if (livro == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(livro).build();
    }

    @PUT
    @Path("{idAutor}")
    public Response removeAutor(@PathParam("idAutor") int id) {
        Autor autor = autorService.getAutor(id);

        Livro livro = this.livroService.removeAutor(this.idLivro, autor);
        if (livro == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(livro).build();
    }

    @GET
    public Response exibirAutores() {
        Livro livro = this.livroService.getLivro(idLivro);

        if (livro == null) {
            return Response.noContent().build();
        }

        GenericEntity<List<Autor>> entity
                = new GenericEntity<List<Autor>>(
                        livro.getAutores()
                ) {
        };

        return Response.ok()
                .entity(entity)
                .build();
    }

    @GET
    @Path("{idAutor}")
    public Response exibirAutor(@PathParam("idAutor") int id) {

        Livro livro = this.livroService.getLivro(this.idLivro);

        if (livro == null) {
            return Response.noContent().build();
        }

        Autor autor = autorService.getAutor(id);

        if (!livro.getAutores().contains(autor)) {
            return Response.noContent().build();
        }

        return Response.ok().entity(autor).build();
    }

}
