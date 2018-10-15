package br.edu.ifpb.pos.atividade.rest.beans;

import br.edu.ifpb.pos.atividade.rest.modelo.Autor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Edilva
 */
@Named
@SessionScoped
public class AutorBean implements Serializable {

    private Autor autor = new Autor();
    private Client client = ClientBuilder.newClient();
    private Gson gson = new Gson();
    private String uri = "http://localhost:8080/pos-atividade-rest/api/autores/";
    private List<Autor> autores = new ArrayList<>();
    private WebTarget wt = client.target(uri);

    private boolean editando = false;

    public AutorBean() {
        carregar();
    }

    public void carregar() {
        String json = wt.request().get(String.class);
        autores = gson.fromJson(json, new TypeToken<List<Autor>>() {
        }.getType());
    }

    public String salvarAutor() {
        wt.request().post(Entity.json(autor));
        autor = new Autor();
        return null;
    }

    public void removerAutor(int id) {
        wt.path("{id}").resolveTemplate("id", id).request().delete();
    }

    public String atualizarAutor() {
        wt.path("{id}").resolveTemplate("id", this.autor.getId()).
                request().put(Entity.json(this.autor));
        this.editando = false;
        autor = new Autor();
        return null;
    }

    public void editarAutor(Autor autorAtualizar) {
        this.autor = autorAtualizar;
        this.editando = true;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Autor> getAutores() {
        carregar();
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

}
