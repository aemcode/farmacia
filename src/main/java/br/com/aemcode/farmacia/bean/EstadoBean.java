/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.bean;

import br.com.aemcode.farmacia.daos.EstadoDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.Estado;
import br.com.aemcode.farmacia.models.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.lang.reflect.Type;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andre
 */
@ManagedBean(name = "estadoBean")
@ViewScoped
public class EstadoBean implements Serializable {

    private Estado estado;

    private List<Estado> estados;

    @PostConstruct
    public void listar() {
        try {
            /**
             * Em um processo sem o uso de serviços, o managedBean chama a
             * camada DAO... Este não desacopla o front-end do back-end
             *
             * Quando se trabalha com serviços é responsável por invocar o
             * backend.
             */
            //EstadoDao estadoDAO = new EstadoDao();
            //estados = estadoDAO.listar("nome");

            /**
             * O processo abaixo faz a ligação do frontend com o backend
             */
            Client cliente = ClientBuilder.newClient();
            WebTarget caminho = cliente.target("http://localhost:8080/Farmacia/rest/estado");
            String json = caminho.request().get(String.class);

            Type listType = new TypeToken<ArrayList<Estado>>() {}.getType();

            List<Estado> jsonList = new Gson().fromJson(json, listType);

            estados = jsonList;

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao listar os registros.");
        }
    }

    public void novo() {
        estado = new Estado();
        resetInput();
    }

    public void salvar() {
        try {

            Long codigo = 1L;
            UsuarioDao usuarioDaoId = new UsuarioDao();
            Usuario usuarioId = usuarioDaoId.buscar(codigo);

            estado.setAtivo(true);
            estado.setCreated(new Date());
            estado.setCreatedBy(usuarioId);
            estado.setUpdated(new Date());
            estado.setUpdatedBy(usuarioId);

            EstadoDao estadoDAO = new EstadoDao();
            estadoDAO.merge(estado);

            Messages.addGlobalInfo("Registro salvo com sucesso");

            estados = estadoDAO.listar();
            novo();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void excluir(ActionEvent evento) {
        try {

            estado = (Estado) evento.getComponent().getAttributes().get("registroSelecionado");

            EstadoDao estadoDAO = new EstadoDao();
            estadoDAO.excluir(estado);

            Messages.addGlobalInfo("Registro removido com sucesso.");

            estados = estadoDAO.listar();
            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");
        }
    }

    public void editar(ActionEvent evento) {
        estado = (Estado) evento.getComponent().getAttributes().get("registroSelecionado");
        resetInput();
    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroEstado:pnlCadastroEstado");
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

}
