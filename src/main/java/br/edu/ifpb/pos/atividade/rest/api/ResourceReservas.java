
package br.edu.ifpb.pos.atividade.rest.api;

import br.edu.ifpb.pos.atividade.rest.interfaces.ReservaService;
import br.edu.ifpb.pos.atividade.rest.modelo.Reserva;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("reservas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class ResourceReservas {
    
    @Inject
    private ReservaService reservaService;
    
//    @POST
//    public Response novaReserva(JsonObject json) {
//        LocalDate data = LocalDate.parse(json.getString("data"));
//        Livro livro = 
//        Reserva reserva = new Reserva(data, json.getString("descricao"));
//        if (reserva == null) {
//            return Response.noContent().build();
//        }
//        reservaService.salvar(reserva);
//        return Response.ok().entity(reserva).build();
//    }

    @GET
    @Path("{id}")
    public Response reservaPorId(@PathParam("id") int id) {
        Reserva reserva = reservaService.getReserva(id);
        if (reserva == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(reserva).build();
    }

    @GET
    public Response todasAsReservas() {
        List<Reserva> reservas = reservaService.listar();
        GenericEntity<List<Reserva>> entity = new GenericEntity<List<Reserva>>(reservas) {
        };
        return Response.ok().entity(entity).build();

    }

    @DELETE
    @Path("{id}")
    public Response removerReserva(@PathParam("id") int id) {
        Reserva reserva = reservaService.remover(id);
        if (reserva == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(reserva).build();
    }
}
