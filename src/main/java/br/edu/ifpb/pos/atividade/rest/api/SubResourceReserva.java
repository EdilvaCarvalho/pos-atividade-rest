package br.edu.ifpb.pos.atividade.rest.api;

import br.edu.ifpb.pos.atividade.rest.interfaces.LivroService;
import br.edu.ifpb.pos.atividade.rest.modelo.Livro;
import br.edu.ifpb.pos.atividade.rest.modelo.Reserva;
import java.time.LocalDate;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 *
 * @author Edilva ❤❤❤
 */
public class SubResourceReserva {

    private LivroService livroService;
    private int idLivro;

    public SubResourceReserva(LivroService livroService, int idLivro) {
        this.livroService = livroService;
        this.idLivro = idLivro;
    }

    @GET
    public Response exibirReservas() {
        Livro livro = this.livroService.getLivro(this.idLivro);

        if (livro == null) {
            return Response.noContent().build();
        }

        GenericEntity<List<Reserva>> entity
                = new GenericEntity<List<Reserva>>(
                        livro.getReservas()) {
        };

        return Response.ok().entity(entity).build();
    }
    
    @PUT
    public Response adicionarReserva(JsonObject json) {
        LocalDate data = LocalDate.parse(json.getString("data"));
        Livro livro = this.livroService.novaReserva(this.idLivro, data);
        if (livro == null) {
            return Response.noContent().build();
        }

        return Response.ok().entity(livro).build();
    }
}
