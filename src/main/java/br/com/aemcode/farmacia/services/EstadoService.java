/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.services;

import br.com.aemcode.farmacia.daos.EstadoDao;
import br.com.aemcode.farmacia.models.Estado;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author Andre http://localhost:8080/Farmacia/rest/estado
 *
 * Get quando quero consultar algo Exemplo: quando faço um acesso a um serviço
 * da internet o get retorna coisas pra nós
 *
 * Post
 *
 * Put
 *
 * Delete
 */
@Path("estado")
public class EstadoService {

    /**
     *
     * @author Andre
     *
     * URL para o Listar http://localhost:8080/Farmacia/rest/estado
     */
    @GET
    public String listar() {

        EstadoDao estadoDao = new EstadoDao();
        List<Estado> estado = estadoDao.listar();

        Gson gson = new Gson();
        String json = gson.toJson(estado);

        return json;

    }

    /**
     *
     * @param id
     * @return
     *
     * http://localhost:8080/Farmacia/rest/estado/id
     *
     * Exemplo: http://localhost:8080/Farmacia/rest/estado/3
     */
    @GET
    @Path("{id}")
    public String Buscar(@PathParam("id") Long id) {

        EstadoDao estadoDao = new EstadoDao();
        Estado estado = estadoDao.buscar(id);

        Gson gson = new Gson();
        String json = gson.toJson(estado);

        return json;

    }

    /**
     *
     * @param json
     *
     * http://localhost:8080/Farmacia/rest/estado Os dois ficam iguais porém
     * pelos metodos de envio, é possivel saber se será um post ou get
     *
     * Para gravar os dados no banco via POST é necessário montar uma linha
     * igual ao enviado via get
     *
     * Tem pessoas que gostam de retornar verdadeiro ou falso, porém, é possível
     * retornar o proprio objeto salvo via POST
     * @return
     */
    @POST
    public String salvar(String json) {

        //try {
        Gson gson = new Gson();
        Estado estado = gson.fromJson(json, Estado.class);

        EstadoDao estadoDao = new EstadoDao();
        estadoDao.salvar(estado);

        String jsonSaida = gson.toJson(estado);
        return jsonSaida;

        /**
         * Alguns colocam como retorno o status dos protocolos HTTP por exemplo:
         * 200 - sucesso
         */
        //} catch (JsonSyntaxException e) {
        //    e.getMessage();
        //}
        //return json;
    }

    @PUT
    public String editar(String json) {

        Gson gson = new Gson();
        Estado estado = gson.fromJson(json, Estado.class);

        EstadoDao estadoDao = new EstadoDao();
        estadoDao.editar(estado);

        String jsonSaida = gson.toJson(estado);
        return jsonSaida;

    }

    /**
     * 
     * @param json
     * @return 
     * 
     * No caso do DELETE, enviar somente a estrutura de IDs que será excluído... exemplo: {"id": "28"}
     * 
     * objeto TRANSIENTE não faz parte do hibernate
     * objeto PERSISTENTE faz parte do hibernate
     * 
     * Para deletar, é necessário pesquisar antes
     */
    @DELETE
    public String excluir(String json) {

        Gson gson = new Gson();
        Estado estado = gson.fromJson(json, Estado.class);

        EstadoDao estadoDao = new EstadoDao();
        estado = estadoDao.buscar(estado.getId());
        estadoDao.excluir(estado);

        String jsonSaida = gson.toJson(estado);
        return jsonSaida;

    }

}
