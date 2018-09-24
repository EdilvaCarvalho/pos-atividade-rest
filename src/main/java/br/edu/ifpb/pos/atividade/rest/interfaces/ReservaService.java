package br.edu.ifpb.pos.atividade.rest.interfaces;

import br.edu.ifpb.pos.atividade.rest.modelo.Reserva;
import java.util.List;

/**
 *
 * @author Edilva
 */
public interface ReservaService {

    void salvar(Reserva reserva);

    void atualizar(Reserva reserva);

    Reserva remover(int id);

    Reserva getReserva(int id);

    List<Reserva> listar();
    
}
