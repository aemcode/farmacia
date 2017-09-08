/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.bean;

import br.com.aemcode.farmacia.daos.ProdutoTipoDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.ProdutoTipo;
import br.com.aemcode.farmacia.models.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andre
 */
@ManagedBean(name = "produtoTipoBean")
@ViewScoped
public class ProdutoTipoBean implements Serializable {

    private ProdutoTipo produtoTipo;

    private List<ProdutoTipo> produtoTipos;

    @PostConstruct
    public void listar() {
        try {

            ProdutoTipoDao produtoTipoDao = new ProdutoTipoDao();
            produtoTipos = produtoTipoDao.listar("descricao");

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Ocorreu um erro ao listar os registros.");

        }
    }

    public void novo() {

        try {

            produtoTipo = new ProdutoTipo();
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

            produtoTipo.setAtivo(true);
            produtoTipo.setCreated(new Date());
            produtoTipo.setCreatedBy(usuarioId);
            produtoTipo.setUpdated(new Date());
            produtoTipo.setUpdatedBy(usuarioId);

            ProdutoTipoDao produtoTipoDao = new ProdutoTipoDao();
            produtoTipoDao.merge(produtoTipo);

            Messages.addGlobalInfo("Registro salvo com sucesso.");

            produtoTipos = produtoTipoDao.listar();
            novo();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");

        }
    }

    public void excluir(ActionEvent evento) {
        try {

            produtoTipo = (ProdutoTipo) evento.getComponent().getAttributes().get("registroSelecionado");

            ProdutoTipoDao produtoTipoDao = new ProdutoTipoDao();
            produtoTipoDao.excluir(produtoTipo);

            Messages.addGlobalInfo("Registro removido com sucesso.");

            produtoTipos = produtoTipoDao.listar();
            resetInput();

        } catch (RuntimeException erro) {

            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");

        }
    }

    public void editar(ActionEvent evento) {
        try {

            produtoTipo = (ProdutoTipo) evento.getComponent().getAttributes().get("registroSelecionado");
            resetInput();

        } catch (RuntimeException e) {

            Messages.addGlobalError("Erro ao editar o registro.");
            e.printStackTrace();

        }
    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroProdutoTipo:pnlCadastroProdutoTipo");
    }

    public ProdutoTipo getProdutoTipo() {
        return produtoTipo;
    }

    public void setProdutoTipo(ProdutoTipo produtoTipo) {
        this.produtoTipo = produtoTipo;
    }

    public List<ProdutoTipo> getProdutoTipos() {
        return produtoTipos;
    }

    public void setProdutoTipos(List<ProdutoTipo> produtoTipos) {
        this.produtoTipos = produtoTipos;
    }

}
