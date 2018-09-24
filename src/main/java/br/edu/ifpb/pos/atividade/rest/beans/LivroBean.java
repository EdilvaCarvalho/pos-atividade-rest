package br.edu.ifpb.pos.atividade.rest.beans;

import br.edu.ifpb.pos.atividade.rest.modelo.Autor;
import br.edu.ifpb.pos.atividade.rest.modelo.Livro;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
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
public class LivroBean implements Serializable {

    private Livro livro = new Livro();
    private Client client = ClientBuilder.newClient();
    private Gson gson = new Gson();
    private String uri = "http://localhost:8080/pos-atividade-rest/api/livros/";
    private List<Livro> livros = new ArrayList<>();
    private WebTarget wt = client.target(uri);
    private int autorId;

    private boolean editando = false;

    public LivroBean() {
        carregar();
    }

    public void carregar() {
        String json = wt.request().get(String.class);
        livros = gson.fromJson(json, new TypeToken<List<Livro>>() {
        }.getType());
    }

    public String salvarLivro() {
        wt.request().post(Entity.json(livro));
        livro = new Livro();
        carregar();
        return null;
    }

    public void removerLivro(int id) {
        wt.path("{id}").resolveTemplate("id", id).request().delete();
        carregar();
    }

    public String atualizarLivro() {
        wt.path("{id}").resolveTemplate("id", this.livro.getId()).
                request().put(Entity.json(this.livro));
        this.editando = false;
        livro = new Livro();
        carregar();
        return null;
    }

    public void editarLivro(Livro livroAtualizar) {
        this.livro = livroAtualizar;
        this.editando = true;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public String pegaLivro(Livro l) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("livro1", l);
        return "livro_autor";
    }

    public String adicionarAutor() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.livro = (Livro) session.getAttribute("livro1");
        Autor autor = new Autor();
        autor.setId(autorId);
        wt.path("{id}/autores").resolveTemplate("id", this.livro.getId()).
                request().put(Entity.json(autor));
        this.livro = new Livro();
        session.removeAttribute("livro1");
        carregar();
        return "livro";
    }

    public String removerAutor(int id) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.livro = (Livro) session.getAttribute("livro1");
        wt.path("{id}/autores/{idAutor}").resolveTemplate("id", this.livro.getId()).
                resolveTemplate("idAutor", id).
                request().delete();
        this.livro = new Livro();
        session.removeAttribute("livro1");
        carregar();
        return "livro";
    }

}
