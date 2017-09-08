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

import br.com.aemcode.farmacia.daos.FilialDao;
import br.com.aemcode.farmacia.daos.MatrizDao;
import br.com.aemcode.farmacia.daos.UsuarioFilialDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.Filial;
import br.com.aemcode.farmacia.models.Matriz;
import br.com.aemcode.farmacia.models.UsuarioFilial;
import br.com.aemcode.farmacia.models.Usuario;
import java.util.Date;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andre
 */
@ManagedBean(name = "usuarioFilialBean")
@ViewScoped
public class UsuarioFilialBean implements Serializable {

    private UsuarioFilial usuarioFilial;
    private Matriz matriz;
    private Filial filial;
    private Usuario usuario;

    private List<UsuarioFilial> usuarioFiliais;
    private List<Matriz> matrizes;
    private List<Filial> filiais;
    private List<Usuario> usuarios;

    @PostConstruct
    public void listar() {

        try {

            UsuarioFilialDao usuarioFilialDao = new UsuarioFilialDao();
            usuarioFiliais = usuarioFilialDao.listar();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao listar os registros");
            erro.printStackTrace();

        }
    }

    public void novo() {

        try {

            usuarioFilial = new UsuarioFilial();

            MatrizDao matrizDao = new MatrizDao();
            FilialDao filialDao = new FilialDao();
            UsuarioDao usuarioDao = new UsuarioDao();
            
            matrizes = matrizDao.listar("nome");
            filiais = filialDao.listar("nome");
            usuarios = usuarioDao.listar("nome");
            
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

            usuarioFilial.setAtivo(true);
            usuarioFilial.setCreated(new Date());
            usuarioFilial.setCreatedBy(usuarioId);
            usuarioFilial.setUpdated(new Date());
            usuarioFilial.setUpdatedBy(usuarioId);

            UsuarioFilialDao usuarioFilialDao = new UsuarioFilialDao();
            usuarioFilialDao.merge(usuarioFilial);

            Messages.addGlobalInfo("Registro salvo com sucesso.");

            usuarioFiliais = usuarioFilialDao.listar();
            novo();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void editar(ActionEvent evento) {

        try {

            usuarioFilial = (UsuarioFilial) evento.getComponent().getAttributes().get("registroSelecionado");
            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar o registro.");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {

        try {

            usuarioFilial = (UsuarioFilial) evento.getComponent().getAttributes().get("registroSelecionado");

            UsuarioFilialDao usuarioFilialDao = new UsuarioFilialDao();
            usuarioFilialDao.excluir(usuarioFilial);

            Messages.addGlobalInfo("Registro removido com sucesso.");

            usuarioFiliais = usuarioFilialDao.listar();
            resetInput();

        } catch (RuntimeException erro) {

            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");

        }
    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroUsuarioFilial:pnlCadastroUsuarioFilial");
    }

    /*public void fillFields() {

        try {
            if (produto != null) {

                UsuarioFilialDao usuarioFilialDao = new UsuarioFilialDao();

                //usuarioFilials = usuarioFilialDao.buscarPorProduto(produto.getId());
            } else {

                usuarioFilials = new ArrayList<>();

            }
        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao filtrar as usuarioFilials.");
            erro.printStackTrace();

        }
    }
     */
    public UsuarioFilial getUsuarioFilial() {
        return usuarioFilial;
    }

    public void setUsuarioFilial(UsuarioFilial usuarioFilial) {
        this.usuarioFilial = usuarioFilial;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<UsuarioFilial> getUsuarioFiliais() {
        return usuarioFiliais;
    }

    public void setUsuarioFiliais(List<UsuarioFilial> usuarioFiliais) {
        this.usuarioFiliais = usuarioFiliais;
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
