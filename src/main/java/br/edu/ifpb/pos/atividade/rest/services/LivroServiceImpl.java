package br.edu.ifpb.pos.atividade.rest.services;

import br.edu.ifpb.pos.atividade.rest.interfaces.LivroService;
import br.edu.ifpb.pos.atividade.rest.modelo.Autor;
import br.edu.ifpb.pos.atividade.rest.modelo.Livro;
import br.edu.ifpb.pos.atividade.rest.modelo.Reserva;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edilva
 */
@Stateless
public class LivroServiceImpl implements LivroService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Livro livro) {
        em.persist(livro);
    }

    @Override
    public void atualizar(Livro livro) {
        em.merge(livro);
    }

    @Override
    public Livro remover(int id) {
        Livro livro = getLivro(id);
        em.remove(livro);
        return livro;
    }

    @Override
    public Livro getLivro(int id) {
        return em.find(Livro.class, id);
    }

    @Override
    public List<Livro> listar() {
        return em.createQuery("FROM Livro l", Livro.class)
                .getResultList();
    }

    @Override
    public Livro novaReserva(int idLivro, LocalDate data) {
        Livro livro = getLivro(idLivro);
        Reserva reserva = new Reserva(data);
        em.persist(reserva);
        livro.addReserva(reserva);
        return livro;
    }

    @Override
    public Livro addAutor(int idLivro, Autor autor) {
        Livro livro = getLivro(idLivro);
        livro.addAutor(autor);
        return livro;
    }

    @Override
    public Livro removeAutor(int idLivro, Autor autor) {
        Livro livro = getLivro(idLivro);
        livro.removeAutor(autor);
        return livro;
    }

}
