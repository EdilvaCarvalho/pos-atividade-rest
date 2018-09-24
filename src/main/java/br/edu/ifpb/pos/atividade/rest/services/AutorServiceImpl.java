package br.edu.ifpb.pos.atividade.rest.services;

import br.edu.ifpb.pos.atividade.rest.interfaces.AutorService;
import br.edu.ifpb.pos.atividade.rest.modelo.Autor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edilva
 */
@Stateless
public class AutorServiceImpl implements AutorService {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Autor autor) {
        em.persist(autor);
    }

    @Override
    public void atualizar(Autor autor) {
        em.merge(autor);
    }

    @Override
    public Autor remover(int id) {
        Autor autor = getAutor(id);
        em.remove(autor);
        return autor;
    }

    @Override
    public Autor getAutor(int id) {
        return em.find(Autor.class, id);
    }

    @Override
    public List<Autor> listar() {
        return em.createQuery("FROM Autor a", Autor.class)
                .getResultList();
    }

}
