/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.bean;

import br.com.aemcode.farmacia.daos.ProdutoClassificacaoDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.ProdutoClassificacao;
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
@ManagedBean(name = "produtoClassificacaoBean")
@ViewScoped
public class ProdutoClassificacaoBean implements Serializable {

    private ProdutoClassificacao produtoClassificacao;

    private List<ProdutoClassificacao> produtoClassificacoes;

    @PostConstruct
    public void listar() {
        try {

            ProdutoClassificacaoDao produtoClassificacaoDao = new ProdutoClassificacaoDao();
            produtoClassificacoes = produtoClassificacaoDao.listar("descricao");

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Ocorreu um erro ao listar os registros.");

        }
    }

    public void novo() {
        try {

            produtoClassificacao = new ProdutoClassificacao();
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

            produtoClassificacao.setAtivo(true);
            produtoClassificacao.setCreated(new Date());
            produtoClassificacao.setCreatedBy(usuarioId);
            produtoClassificacao.setUpdated(new Date());
            produtoClassificacao.setUpdatedBy(usuarioId);

            ProdutoClassificacaoDao produtoClassificacaoDao = new ProdutoClassificacaoDao();
            produtoClassificacaoDao.merge(produtoClassificacao);

            Messages.addGlobalInfo("Registro salvo com sucesso");

            produtoClassificacoes = produtoClassificacaoDao.listar();
            novo();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void excluir(ActionEvent evento) {
        try {

            produtoClassificacao = (ProdutoClassificacao) evento.getComponent().getAttributes().get("registroSelecionado");

            ProdutoClassificacaoDao produtoClassificacaoDao = new ProdutoClassificacaoDao();
            produtoClassificacaoDao.excluir(produtoClassificacao);

            Messages.addGlobalInfo("Registro removido com sucesso.");

            produtoClassificacoes = produtoClassificacaoDao.listar();
            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");
        }
    }

    public void editar(ActionEvent evento) {

        try {

            produtoClassificacao = (ProdutoClassificacao) evento.getComponent().getAttributes().get("registroSelecionado");
            resetInput();

        } catch (RuntimeException e) {

            Messages.addGlobalError("Erro ao editar o registro.");
            e.printStackTrace();

        }

    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroProdutoClassificacao:pnlCadastroProdutoClassificacao");
    }

    public ProdutoClassificacao getProdutoClassificacao() {
        return produtoClassificacao;
    }

    public void setProdutoClassificacao(ProdutoClassificacao produtoClassificacao) {
        this.produtoClassificacao = produtoClassificacao;
    }

    public List<ProdutoClassificacao> getProdutoClassificacoes() {
        return produtoClassificacoes;
    }

    public void setProdutoClassificacoes(List<ProdutoClassificacao> produtoClassificacoes) {
        this.produtoClassificacoes = produtoClassificacoes;
    }

}
