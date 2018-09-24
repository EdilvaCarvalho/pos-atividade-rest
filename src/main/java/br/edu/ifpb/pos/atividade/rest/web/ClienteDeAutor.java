
package br.edu.ifpb.pos.atividade.rest.web;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Edilva
 */
public class ClienteDeAutor {
    
    private String uri = "http://localhost:8080/pos-atividade-rest/api/vendas/";
    private Client client = ClientBuilder.newClient();
    private WebTarget root = client.target(uri);
    
    
    
}
