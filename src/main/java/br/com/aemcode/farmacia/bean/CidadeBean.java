/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.aemcode.farmacia.daos.EstadoDao;
import br.com.aemcode.farmacia.daos.CidadeDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.Estado;
import br.com.aemcode.farmacia.models.Cidade;
import br.com.aemcode.farmacia.models.Usuario;
import java.util.Date;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andre
 */
@ManagedBean(name="cidadeBean")
@ViewScoped
public class CidadeBean implements Serializable {

    private Cidade cidade;

    private List<Cidade> cidades;
    private List<Estado> estados;

    @PostConstruct
    public void listar() {

        try {

            CidadeDao cidadeDao = new CidadeDao();
            cidades = cidadeDao.listar("nome");

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao listar os registros");
            erro.printStackTrace();

        }
    }

    public void novo() {

        try {

            cidade = new Cidade();

            EstadoDao estadoDao = new EstadoDao();
            estados = estadoDao.listar("nome");
            
            resetInput();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao criar um novo registro.");
            erro.printStackTrace();

        }
    }

    public void salvar() {

        try {

            Long codigo = 1L;
            UsuarioDao usuarioDaoId = new UsuarioDao();
            Usuario usuarioId = usuarioDaoId.buscar(codigo);

            cidade.setAtivo(true);
            cidade.setCreated(new Date());
            cidade.setCreatedBy(usuarioId);
            cidade.setUpdated(new Date());
            cidade.setUpdatedBy(usuarioId);

            CidadeDao cidadeDAO = new CidadeDao();
            cidadeDAO.merge(cidade);

            cidades = cidadeDAO.listar();

            Messages.addGlobalInfo("Registro salvo com sucesso");

            novo();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void excluir(ActionEvent evento) {

        try {

            cidade = (Cidade) evento.getComponent().getAttributes().get("registroSelecionado");

            CidadeDao cidadeDAO = new CidadeDao();
            cidadeDAO.excluir(cidade);

            Messages.addGlobalInfo("Registro removido com sucesso.");
            
            cidades = cidadeDAO.listar();

        } catch (RuntimeException erro) {

            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");

        }

    }

    public void editar(ActionEvent evento) {

        try {

            cidade = (Cidade) evento.getComponent().getAttributes().get("registroSelecionado");

            EstadoDao estadoDAO = new EstadoDao();
            estados = estadoDAO.listar();
            
            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
            erro.printStackTrace();
        }

    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroCidade:pnlgCadastroCidade");
    }

    /*
    public void fillFields() {

        try {
            if (estado != null) {

                CidadeDao cidadeDao = new CidadeDao();
                cidades = cidadeDao.buscarPorEstado(estado.getId());

            } else {

                cidades = new ArrayList<>();

            }
        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao filtrar as cidades.");
            erro.printStackTrace();

        }
    }*/
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

}
