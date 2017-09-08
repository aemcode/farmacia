/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.bean;

import br.com.aemcode.farmacia.daos.UsuarioDao;
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
@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

    private Usuario usuario;

    private List<Usuario> usuarios;

    @PostConstruct
    public void listar() {
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarios = usuarioDao.listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao listar os registros.");
        }
    }

    public void novo() {
        try {

            usuario = new Usuario();
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

            usuario.setAtivo(true);
            usuario.setCreated(new Date());
            usuario.setCreatedBy(usuarioId);
            usuario.setUpdated(new Date());
            usuario.setUpdatedBy(usuarioId);

            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.merge(usuario);

            Messages.addGlobalInfo("Registro salvo com sucesso");

            usuarios = usuarioDao.listar();
            novo();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void editar(ActionEvent evento) {

        try {

            usuario = (Usuario) evento.getComponent().getAttributes().get("registroSelecionado");
            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o registro.");
            erro.printStackTrace();
        }

    }

    public void excluir(ActionEvent evento) {
        try {
            usuario = (Usuario) evento.getComponent().getAttributes().get("registroSelecionado");

            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.excluir(usuario);

            Messages.addGlobalInfo("Registro removido com sucesso.");

            usuarios = usuarioDao.listar();
            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");
        }
    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroUsuario:pnlCadastroUsuario");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
