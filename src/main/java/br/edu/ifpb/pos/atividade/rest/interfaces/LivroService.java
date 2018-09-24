package br.edu.ifpb.pos.atividade.rest.interfaces;

import br.edu.ifpb.pos.atividade.rest.modelo.Autor;
import br.edu.ifpb.pos.atividade.rest.modelo.Livro;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Edilva
 */
public interface LivroService {

    void salvar(Livro livro);

    void atualizar(Livro livro);

    Livro remover(int id);

    Livro getLivro(int id);

    List<Livro> listar();

    Livro novaReserva(int idLivro, LocalDate data);

    Livro addAutor(int idLivro, Autor autor);

    Livro removeAutor(int idLivro, Autor autor);
}
