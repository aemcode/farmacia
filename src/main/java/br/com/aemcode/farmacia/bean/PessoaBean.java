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

import br.com.aemcode.farmacia.daos.CidadeDao;
import br.com.aemcode.farmacia.daos.EstadoDao;
import br.com.aemcode.farmacia.daos.PessoaDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.Cidade;
import br.com.aemcode.farmacia.models.Estado;
import br.com.aemcode.farmacia.models.Pessoa;
import br.com.aemcode.farmacia.models.Usuario;
import java.util.Date;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andre
 */
@ManagedBean(name = "pessoaBean")
@ViewScoped
public class PessoaBean implements Serializable {

    private Pessoa pessoa;
    private Cidade cidade;
    private Estado estado;
    private Usuario usuario;

    private List<Pessoa> pessoas;
    private List<Estado> estados;
    private List<Cidade> cidades;
    private List<Usuario> usuarios;

    @PostConstruct
    public void listar() {

        try {

            PessoaDao pessoaDao = new PessoaDao();
            pessoas = pessoaDao.listar();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao listar os registros");
            erro.printStackTrace();

        }
    }

    public void novo() {
        try {
            pessoa = new Pessoa();

            EstadoDao estadoDao = new EstadoDao();
            estados = estadoDao.listar("nome");

            /*A linha abaixo é para limpar a lista de registros*/
            cidades = new ArrayList<>();

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

            pessoa.setAtivo(true);
            pessoa.setCreated(new Date());
            pessoa.setCreatedBy(usuarioId);
            pessoa.setUpdated(new Date());
            pessoa.setUpdatedBy(usuarioId);

            PessoaDao pessoaDao = new PessoaDao();
            pessoaDao.merge(pessoa);

            Messages.addGlobalInfo("Registro salvo com sucesso");

            pessoas = pessoaDao.listar();

            novo();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void editar(ActionEvent evento) {

        try {

            pessoa = (Pessoa) evento.getComponent().getAttributes().get("registroSelecionado");

            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o registro.");
            erro.printStackTrace();
        }

    }

    public void excluir(ActionEvent evento) {

        try {

            pessoa = (Pessoa) evento.getComponent().getAttributes().get("registroSelecionado");

            PessoaDao pessoaDao = new PessoaDao();
            pessoaDao.excluir(pessoa);

            Messages.addGlobalInfo("Registro removido com sucesso.");

            pessoas = pessoaDao.listar();

            resetInput();

        } catch (RuntimeException erro) {

            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");

        }

    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroPessoa:pnlCadastroPessoa");
    }

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
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
