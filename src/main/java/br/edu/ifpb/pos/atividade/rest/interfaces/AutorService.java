package br.edu.ifpb.pos.atividade.rest.interfaces;

import br.edu.ifpb.pos.atividade.rest.modelo.Autor;
import java.util.List;

/**
 *
 * @author Edilva
 */
public interface AutorService {

    void salvar(Autor autor);

    void atualizar(Autor autor);

    Autor remover(int id);

    Autor getAutor(int id);

    List<Autor> listar();
}
