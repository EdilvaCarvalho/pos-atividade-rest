package br.edu.ifpb.pos.atividade.rest.modelo;

import br.edu.ifpb.pos.atividade.rest.modelo.Autor;
import br.edu.ifpb.pos.atividade.rest.modelo.Reserva;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-15T05:20:57")
@StaticMetamodel(Livro.class)
public class Livro_ { 

    public static volatile ListAttribute<Livro, Reserva> reservas;
    public static volatile SingularAttribute<Livro, String> titulo;
    public static volatile SingularAttribute<Livro, Integer> id;
    public static volatile ListAttribute<Livro, Autor> autores;
    public static volatile SingularAttribute<Livro, String> edicao;
    public static volatile SingularAttribute<Livro, String> descricao;

}