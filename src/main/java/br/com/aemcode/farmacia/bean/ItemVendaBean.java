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

import br.com.aemcode.farmacia.daos.VendaDao;
import br.com.aemcode.farmacia.daos.ItemVendaDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.Filial;
import br.com.aemcode.farmacia.models.Venda;
import br.com.aemcode.farmacia.models.ItemVenda;
import br.com.aemcode.farmacia.models.Matriz;
import br.com.aemcode.farmacia.models.Produto;
import br.com.aemcode.farmacia.models.Usuario;
import java.util.Date;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andre
 */
@ManagedBean(name="itemVendaBean")
@ViewScoped
public class ItemVendaBean implements Serializable {

    private ItemVenda itemVenda;
    private Matriz matriz;
    private Filial filial;
    private Venda venda;
    private Produto produto;

    private List<ItemVenda> itemVendas;
    private List<Matriz> matrizes;
    private List<Filial> filiais;
    private List<Venda> vendas;
    private List<Produto> produtos;

    @PostConstruct
    public void listar() {

        try {

            ItemVendaDao itemVendaDao = new ItemVendaDao();

            itemVendas = itemVendaDao.listar();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao listar os registros");
            erro.printStackTrace();

        }
    }

    public void novo() {

        try {

            itemVenda = new ItemVenda();

            resetInput();

            VendaDao vendaDao = new VendaDao();

            vendas = vendaDao.listar("id");

            /*A linha abaixo é para limpar a lista de itemVendas*/
            itemVendas = new ArrayList<>();

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

            itemVenda.setAtivo(true);
            itemVenda.setCreated(new Date());
            itemVenda.setCreatedBy(usuarioId);
            itemVenda.setUpdated(new Date());
            itemVenda.setUpdatedBy(usuarioId);

            ItemVendaDao itemVendaDAO = new ItemVendaDao();

            itemVendaDAO.merge(itemVenda);

            novo();

            itemVendas = itemVendaDAO.listar();

            Messages.addGlobalInfo("Registro salvo com sucesso");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void excluir(ActionEvent evento) {

        try {

            itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("registroSelecionado");

            ItemVendaDao itemVendaDAO = new ItemVendaDao();

            itemVendaDAO.excluir(itemVenda);

            itemVendas = itemVendaDAO.listar();

            Messages.addGlobalInfo("Registro removido com sucesso.");

        } catch (RuntimeException erro) {

            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");

        }

    }

    public void editar(ActionEvent evento) {
        resetInput();
        venda = (Venda) evento.getComponent().getAttributes().get("registroSelecionado");
    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroItemVenda:pnlCadastroItemVenda");
    }

    public void fillFields() {

        try {
            if (venda != null) {

                ItemVendaDao itemVendaDao = new ItemVendaDao();
                itemVendas = itemVendaDao.listar("nome");

            } else {

                itemVendas = new ArrayList<>();

            }
        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao filtrar as itemVendas.");
            erro.printStackTrace();

        }
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }

    public List<ItemVenda> getItemVendas() {
        return itemVendas;
    }

    public void setItemVendas(List<ItemVenda> itemVendas) {
        this.itemVendas = itemVendas;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
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

}
