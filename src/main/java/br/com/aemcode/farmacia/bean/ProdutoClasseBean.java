/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.bean;

import br.com.aemcode.farmacia.daos.ProdutoClasseDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.ProdutoClasse;
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
@ManagedBean(name = "produtoClasseBean")
@ViewScoped
public class ProdutoClasseBean implements Serializable {

    private ProdutoClasse produtoClasse;

    private List<ProdutoClasse> produtoClasses;

    @PostConstruct
    public void listar() {
        try {

            ProdutoClasseDao produtoClasseDao = new ProdutoClasseDao();
            produtoClasses = produtoClasseDao.listar("descricao");

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Ocorreu um erro ao listar os registros.");

        }
    }

    public void novo() {
        try {

            produtoClasse = new ProdutoClasse();
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

            produtoClasse.setAtivo(true);
            produtoClasse.setCreated(new Date());
            produtoClasse.setCreatedBy(usuarioId);
            produtoClasse.setUpdated(new Date());
            produtoClasse.setUpdatedBy(usuarioId);

            ProdutoClasseDao produtoClasseDao = new ProdutoClasseDao();
            produtoClasseDao.merge(produtoClasse);

            Messages.addGlobalInfo("Registro salvo com sucesso");

            produtoClasses = produtoClasseDao.listar();
            novo();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void excluir(ActionEvent evento) {
        try {

            produtoClasse = (ProdutoClasse) evento.getComponent().getAttributes().get("registroSelecionado");

            ProdutoClasseDao produtoClasseDao = new ProdutoClasseDao();
            produtoClasseDao.excluir(produtoClasse);

            Messages.addGlobalInfo("Registro removido com sucesso.");

            produtoClasses = produtoClasseDao.listar();
            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");
        }
    }

    public void editar(ActionEvent evento) {

        try {

            produtoClasse = (ProdutoClasse) evento.getComponent().getAttributes().get("registroSelecionado");
            resetInput();

        } catch (RuntimeException e) {

            Messages.addGlobalError("Erro ao editar o registro.");
            e.printStackTrace();

        }

    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroProdutoClasse:pnlCadastroProdutoClasse");
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
