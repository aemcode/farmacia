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

import br.com.aemcode.farmacia.daos.ProdutoCategoriaDao;
import br.com.aemcode.farmacia.daos.ProdutoClasseDao;
import br.com.aemcode.farmacia.daos.ProdutoClassificacaoDao;
import br.com.aemcode.farmacia.daos.ProdutoDao;
import br.com.aemcode.farmacia.daos.ProdutoTipoDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.ProdutoCategoria;
import br.com.aemcode.farmacia.models.Produto;
import br.com.aemcode.farmacia.models.ProdutoClasse;
import br.com.aemcode.farmacia.models.ProdutoClassificacao;
import br.com.aemcode.farmacia.models.ProdutoTipo;
import br.com.aemcode.farmacia.models.Usuario;
import java.util.Date;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andre
 */
@ManagedBean(name = "produtoBean")
@ViewScoped
public class ProdutoBean implements Serializable {

    private Produto produto;
    private ProdutoClassificacao produtoClassificacao;
    private ProdutoCategoria produtoCategoria;
    private ProdutoTipo produtoTipo;
    private ProdutoClasse produtoClasse;

    private List<Produto> produtos;
    private List<ProdutoClassificacao> produtoClassificacoes;
    private List<ProdutoCategoria> produtoCategorias;
    private List<ProdutoClasse> produtoClasses;
    private List<ProdutoTipo> produtoTipos;

    @PostConstruct
    public void listar() {

        try {

            ProdutoDao produtoDao = new ProdutoDao();
            produtos = produtoDao.listar("descricao");

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao listar os registros");
            erro.printStackTrace();

        }
    }

    public void novo() {

        try {

            produto = new Produto();

            ProdutoClassificacaoDao produtoClassificacaoDao = new ProdutoClassificacaoDao();
            ProdutoCategoriaDao produtoCategoriaDao = new ProdutoCategoriaDao();
            ProdutoTipoDao produtoTipoDao = new ProdutoTipoDao();
            ProdutoClasseDao produtoClasseDao = new ProdutoClasseDao();

            produtoClassificacoes = produtoClassificacaoDao.listar("descricao");
            produtoCategorias = produtoCategoriaDao.listar("descricao");
            produtoClasses = produtoClasseDao.listar("descricao");
            produtoTipos = produtoTipoDao.listar("descricao");

            /*A linha abaixo é para limpar a lista de registros*/
            //produtoTipos = new ArrayList<>();
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

            produto.setAtivo(true);
            produto.setCreated(new Date());
            produto.setCreatedBy(usuarioId);
            produto.setUpdated(new Date());
            produto.setUpdatedBy(usuarioId);

            ProdutoDao produtoDao = new ProdutoDao();
            produtoDao.merge(produto);

            Messages.addGlobalInfo("Registro salvo com sucesso");

            produtos = produtoDao.listar();

            novo();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void editar(ActionEvent evento) {

        try {

            produto = (Produto) evento.getComponent().getAttributes().get("registroSelecionado");

            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o registro.");
            erro.printStackTrace();
        }

    }

    public void excluir(ActionEvent evento) {

        try {

            produto = (Produto) evento.getComponent().getAttributes().get("registroSelecionado");

            ProdutoDao produtoDao = new ProdutoDao();
            produtoDao.excluir(produto);

            Messages.addGlobalInfo("Registro removido com sucesso.");

            produtos = produtoDao.listar();

            resetInput();

        } catch (RuntimeException erro) {

            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");

        }

    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroProduto:pnlCadastroProduto");
    }

    /*public void fillFields() {

        try {
            if (produtoCategoria != null) {

                ProdutoDao produtoDao = new ProdutoDao();
                produtos = produtoDao.buscarPorProdutoCategoria(produtoCategoria.getId());

            } else {

                produtos = new ArrayList<>();

            }
        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao filtrar as produtos.");
            erro.printStackTrace();

        }
    }*/
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
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

    public ProdutoClasse getProdutoClasse() {
        return produtoClasse;
    }

    public void setProdutoClasse(ProdutoClasse produtoClasse) {
        this.produtoClasse = produtoClasse;
    }

    public List<ProdutoClasse> getProdutoClasses() {
        return produtoClasses;
    }

    public void setProdutoClasses(List<ProdutoClasse> produtoClasses) {
        this.produtoClasses = produtoClasses;
    }

}
