/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.bean;

import br.com.aemcode.farmacia.daos.ProdutoCategoriaDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.ProdutoCategoria;
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
@ManagedBean(name = "produtoCategoriaBean")
@ViewScoped
public class ProdutoCategoriaBean implements Serializable {

    private ProdutoCategoria produtoCategoria;

    private List<ProdutoCategoria> produtoCategorias;

    @PostConstruct
    public void listar() {
        try {

            ProdutoCategoriaDao produtoCategoriaDAO = new ProdutoCategoriaDao();
            produtoCategorias = produtoCategoriaDAO.listar("descricao");

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Ocorreu um erro ao listar os registros.");

        }
    }

    public void novo() {

        try {

            produtoCategoria = new ProdutoCategoria();
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

            produtoCategoria.setAtivo(true);
            produtoCategoria.setCreated(new Date());
            produtoCategoria.setCreatedBy(usuarioId);
            produtoCategoria.setUpdated(new Date());
            produtoCategoria.setUpdatedBy(usuarioId);

            ProdutoCategoriaDao produtoCategoriaDAO = new ProdutoCategoriaDao();
            produtoCategoriaDAO.merge(produtoCategoria);

            Messages.addGlobalInfo("Registro salvo com sucesso");

            produtoCategorias = produtoCategoriaDAO.listar();
            novo();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");

        }
    }

    public void excluir(ActionEvent evento) {
        try {

            produtoCategoria = (ProdutoCategoria) evento.getComponent().getAttributes().get("registroSelecionado");

            ProdutoCategoriaDao produtoCategoriaDAO = new ProdutoCategoriaDao();
            produtoCategoriaDAO.excluir(produtoCategoria);

            Messages.addGlobalInfo("Registro removido com sucesso.");

            produtoCategorias = produtoCategoriaDAO.listar();
            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");
        }
    }

    public void editar(ActionEvent evento) {

        try {

            produtoCategoria = (ProdutoCategoria) evento.getComponent().getAttributes().get("registroSelecionado");
            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o registro.");
            erro.printStackTrace();
        }

    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroProdutoCategoria:pnlCadastroProdutoCategoria");
    }

    public ProdutoCategoria getProdutoCategoria() {
        return produtoCategoria;
    }

    public void setProdutoCategoria(ProdutoCategoria produtoCategoria) {
        this.produtoCategoria = produtoCategoria;
    }

    public List<ProdutoCategoria> getProdutoCategorias() {
        return produtoCategorias;
    }

    public void setProdutoCategorias(List<ProdutoCategoria> produtoCategorias) {
        this.produtoCategorias = produtoCategorias;
    }

}
