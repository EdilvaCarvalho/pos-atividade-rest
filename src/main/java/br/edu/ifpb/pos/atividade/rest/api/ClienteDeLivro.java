package br.edu.ifpb.pos.atividade.rest.api;

import br.edu.ifpb.pos.atividade.rest.modelo.Livro;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Edilva
 */
public class ClienteDeLivro {

    private String uri = "http://localhost:8080/pos-atividade-rest/api/livros/";
    private Client client = ClientBuilder.newClient();
    private WebTarget root = client.target(uri);

    public List<Livro> todosOsLivros() {
        List<Livro> livros = root.request().get(List.class);
        return livros;
    }

//    private String url = "http://localhost:8080/pos-atividade-rest/api/autores";
//    private Client client = ClientBuilder.newClient();
//    private WebTarget target = client.target(url);
//    
//    public boolean exists(String autorEmail) {
//        
//        Response getResponse = target.path(autorEmail)
//                .request()
//                .get();
//        
//        return getResponse.getStatus() != 404;
//    }
}
