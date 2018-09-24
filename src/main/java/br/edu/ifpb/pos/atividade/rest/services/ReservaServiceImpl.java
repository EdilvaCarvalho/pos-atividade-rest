package br.edu.ifpb.pos.atividade.rest.services;

import br.edu.ifpb.pos.atividade.rest.interfaces.ReservaService;
import br.edu.ifpb.pos.atividade.rest.modelo.Reserva;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edilva
 */
@Stateless
public class ReservaServiceImpl implements ReservaService {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Reserva reserva) {
        em.persist(reserva);
    }

    @Override
    public void atualizar(Reserva reserva) {
        em.merge(reserva);
    }

    @Override
    public Reserva remover(int id) {
        Reserva reserva = getReserva(id);
        em.remove(reserva);
        return reserva;
    }

    @Override
    public Reserva getReserva(int id) {
        return em.find(Reserva.class, id);
    }

    @Override
    public List<Reserva> listar() {
        return em.createQuery("FROM Reserva r", Reserva.class)
                .getResultList();
    }

}
