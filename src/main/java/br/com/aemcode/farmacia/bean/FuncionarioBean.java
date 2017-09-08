/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.bean;

import br.com.aemcode.farmacia.daos.FilialDao;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.aemcode.farmacia.daos.PessoaDao;
import br.com.aemcode.farmacia.daos.FuncionarioDao;
import br.com.aemcode.farmacia.daos.MatrizDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.Filial;
import br.com.aemcode.farmacia.models.Pessoa;
import br.com.aemcode.farmacia.models.Funcionario;
import br.com.aemcode.farmacia.models.Matriz;
import br.com.aemcode.farmacia.models.Usuario;
import java.util.Date;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andre
 */
@ManagedBean(name = "funcionarioBean")
@ViewScoped
public class FuncionarioBean implements Serializable {

    private Funcionario funcionario;
    private Matriz Matriz;
    private Filial filial;
    private Pessoa pessoa;

    private List<Funcionario> funcionarios;
    private List<Matriz> matrizes;
    private List<Filial> filiais;
    private List<Pessoa> pessoas;

    @PostConstruct
    public void listar() {

        try {

            FuncionarioDao funcionarioDao = new FuncionarioDao();
            funcionarios = funcionarioDao.listar();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao listar os registros");
            erro.printStackTrace();

        }
    }

    public void novo() {

        try {

            funcionario = new Funcionario();

            MatrizDao matrizDao = new MatrizDao();
            FilialDao filialDao = new FilialDao();
            PessoaDao pessoaDao = new PessoaDao();

            matrizes = matrizDao.listar("nome");
            filiais = filialDao.listar("nome");
            pessoas = pessoaDao.listar("nome");

            /*A linha abaixo é para limpar a lista de funcionarios*/
            //funcionarios = new ArrayList<>();
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

            funcionario.setAtivo(true);
            funcionario.setCreated(new Date());
            funcionario.setCreatedBy(usuarioId);
            funcionario.setUpdated(new Date());
            funcionario.setUpdatedBy(usuarioId);

            FuncionarioDao funcionarioDao = new FuncionarioDao();
            funcionarioDao.merge(funcionario);

            Messages.addGlobalInfo("Registro salvo com sucesso");

            funcionarios = funcionarioDao.listar();

            novo();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void editar(ActionEvent evento) {
        
        try {

            funcionario = (Funcionario) evento.getComponent().getAttributes().get("registroSelecionado");

            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o registro.");
            erro.printStackTrace();
        }
        
    }

    public void excluir(ActionEvent evento) {

        try {

            funcionario = (Funcionario) evento.getComponent().getAttributes().get("registroSelecionado");

            FuncionarioDao funcionarioDao = new FuncionarioDao();
            funcionarioDao.excluir(funcionario);

            Messages.addGlobalInfo("Registro removido com sucesso.");

            funcionarios = funcionarioDao.listar();

            resetInput();

        } catch (RuntimeException erro) {

            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");

        }

    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroFuncionario:pnlCadastroFuncionario");
    }

    /*public void fillFields() {

        try {
            if (pessoa != null) {

                FuncionarioDao funcionarioDao = new FuncionarioDao();
                funcionarios = funcionarioDao.buscarPorPessoa(pessoa.getId());

            } else {

                funcionarios = new ArrayList<>();

            }
        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao filtrar as funcionarios.");
            erro.printStackTrace();

        }
    }*/

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
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

    public Matriz getMatriz() {
        return Matriz;
    }

    public void setMatriz(Matriz Matriz) {
        this.Matriz = Matriz;
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
