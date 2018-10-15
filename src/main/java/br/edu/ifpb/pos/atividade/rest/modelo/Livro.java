package br.edu.ifpb.pos.atividade.rest.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edilva
 */
@Entity
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String edicao;
    public String descricao;
    public String titulo;
    @OneToMany
    public List<Autor> autores = new ArrayList<>();
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
    public List<Reserva> reservas = new ArrayList<>();

    public Livro() {
    }

    public Livro(String edicao, String descricao, String titulo) {
        this.edicao = edicao;
        this.descricao = descricao;
        this.titulo = titulo;
    }

    public Livro(int id, String edicao, String descricao, String titulo, List<Autor> autores, List<Reserva> reservas) {
        this.id = id;
        this.edicao = edicao;
        this.descricao = descricao;
        this.titulo = titulo;
        this.autores = autores;
        this.reservas = reservas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    public void addAutor(Autor autor) {
        this.autores.add(autor);
    }
    
    public void removeAutor(Autor autor) {
        this.autores.remove(autor);
    }
    
    public void addReserva(Reserva reserva) {
        this.reservas.add(reserva);
    }
    
    public void removeReserva(Reserva reserva) {
        this.reservas.remove(reserva);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.edicao);
        hash = 79 * hash + Objects.hashCode(this.descricao);
        hash = 79 * hash + Objects.hashCode(this.titulo);
        hash = 79 * hash + Objects.hashCode(this.autores);
        hash = 79 * hash + Objects.hashCode(this.reservas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Livro other = (Livro) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.edicao, other.edicao)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.autores, other.autores)) {
            return false;
        }
        if (!Objects.equals(this.reservas, other.reservas)) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "Livro{" + "id=" + id + ", edicao=" + edicao + ", descricao=" + descricao + ", titulo=" + titulo + ", autores=" + autores + ", reservas=" + reservas + '}';
//    }

}
