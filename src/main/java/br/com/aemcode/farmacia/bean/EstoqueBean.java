/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.aemcode.farmacia.daos.ProdutoDao;
import br.com.aemcode.farmacia.daos.EstoqueDao;
import br.com.aemcode.farmacia.daos.FilialDao;
import br.com.aemcode.farmacia.daos.MatrizDao;
import br.com.aemcode.farmacia.daos.ParceiroNegocioDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.Produto;
import br.com.aemcode.farmacia.models.Estoque;
import br.com.aemcode.farmacia.models.Filial;
import br.com.aemcode.farmacia.models.Matriz;
import br.com.aemcode.farmacia.models.ParceiroNegocio;
import br.com.aemcode.farmacia.models.Usuario;
import java.util.Date;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andre
 */
@ManagedBean(name="estoqueBean")
@ViewScoped
public class EstoqueBean implements Serializable {

    private Matriz matriz;
    private Filial filial;
    private Estoque estoque;
    private ParceiroNegocio parceiroNegocio;
    private Produto produto;

    private List<Matriz> matrizes;
    private List<Filial> filiais;
    private List<Estoque> estoques;
    private List<ParceiroNegocio> parceiroNegocios;
    private List<Produto> produtos;

    @PostConstruct
    public void listar() {

        try {

            EstoqueDao estoqueDao = new EstoqueDao();
            estoques = estoqueDao.listar();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao listar os registros");
            erro.printStackTrace();

        }
    }

    public void novo() {

        try {

            resetInput();

            estoque = new Estoque();

            MatrizDao matrizDao = new MatrizDao();
            matrizes = matrizDao.listar("nome");

            FilialDao filialDao = new FilialDao();
            filiais = filialDao.listar("nome");

            ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();
            parceiroNegocios = parceiroNegocioDao.listar("nome");

            ProdutoDao produtoDao = new ProdutoDao();
            produtos = produtoDao.listar("nome");

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

            estoque.setAtivo(true);
            estoque.setCreated(new Date());
            estoque.setCreatedBy(usuarioId);
            estoque.setUpdated(new Date());
            estoque.setUpdatedBy(usuarioId);

            EstoqueDao estoqueDao = new EstoqueDao();
            estoqueDao.merge(estoque);

            novo();

            estoques = estoqueDao.listar();

            Messages.addGlobalInfo("Registro salvo com sucesso.");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void excluir(ActionEvent evento) {

        try {

            estoque = (Estoque) evento.getComponent().getAttributes().get("registroSelecionado");

            EstoqueDao estoqueDao = new EstoqueDao();

            estoqueDao.excluir(estoque);

            estoques = estoqueDao.listar();

            Messages.addGlobalInfo("Registro removido com sucesso.");

        } catch (RuntimeException erro) {

            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");

        }
    }

    public void editar(ActionEvent evento) {
        resetInput();
        estoque = (Estoque) evento.getComponent().getAttributes().get("registroSelecionado");
    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroEstoque:pnlCadastroEstoque");
    }

    public void fillFields() {

        try {
            if (produto != null) {

                EstoqueDao estoqueDao = new EstoqueDao();

                //estoques = estoqueDao.buscarPorProduto(produto.getId());
            } else {

                estoques = new ArrayList<>();

            }
        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao filtrar as estoques.");
            erro.printStackTrace();

        }
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

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

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public ParceiroNegocio getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        this.parceiroNegocio = parceiroNegocio;
    }

    public List<Matriz> getMatrizes() {
        return matrizes;
    }

    public void setMatrizes(List<Matriz> matrizes) {
        this.matrizes = matrizes;
    }

    public List<Filial> getFiliais() {
        return filiais;
    }

    public void setFiliais(List<Filial> filiais) {
        this.filiais = filiais;
    }

    public List<ParceiroNegocio> getParceiroNegocios() {
        return parceiroNegocios;
    }

    public void setParceiroNegocios(List<ParceiroNegocio> parceiroNegocios) {
        this.parceiroNegocios = parceiroNegocios;
    }

}
