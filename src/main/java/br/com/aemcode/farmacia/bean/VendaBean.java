/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.bean;

import br.com.aemcode.farmacia.daos.FilialDao;
import br.com.aemcode.farmacia.daos.FuncionarioDao;
import br.com.aemcode.farmacia.daos.MatrizDao;
import br.com.aemcode.farmacia.daos.ParceiroNegocioDao;
import br.com.aemcode.farmacia.daos.PessoaDao;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.aemcode.farmacia.daos.VendaDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.Filial;
import br.com.aemcode.farmacia.models.Funcionario;
import br.com.aemcode.farmacia.models.Matriz;
import br.com.aemcode.farmacia.models.ParceiroNegocio;
import br.com.aemcode.farmacia.models.Venda;
import br.com.aemcode.farmacia.models.Usuario;
import java.util.Date;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andre
 */
@ManagedBean(name = "vendaBean")
@ViewScoped
public class VendaBean implements Serializable {

    private Venda venda;
    private Matriz matriz;
    private Filial filial;
    private ParceiroNegocio parceiroNegocio;
    private Funcionario funcionario;

    private List<Venda> vendas;
    private List<Matriz> matrizes;
    private List<Filial> filiais;
    private List<ParceiroNegocio> parceiroNegocios;
    private List<Funcionario> funcionarios;

    @PostConstruct
    public void listar() {

        try {

            VendaDao vendaDao = new VendaDao();
            vendas = vendaDao.listar();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao listar os registros");
            erro.printStackTrace();

        }
    }

    public void novo() {

        try {

            venda = new Venda();

            MatrizDao matrizDao = new MatrizDao();
            FilialDao filialDao = new FilialDao();
            FuncionarioDao funcionarioDao = new FuncionarioDao();
            PessoaDao pessoaDao = new PessoaDao();
            ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();

            matrizes = matrizDao.listar("nome");
            filiais = filialDao.listar("nome");
            parceiroNegocios = parceiroNegocioDao.listar("nome");
            funcionarios = funcionarioDao.listar();

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

            venda.setAtivo(true);
            venda.setCreated(new Date());
            venda.setCreatedBy(usuarioId);
            venda.setUpdated(new Date());
            venda.setUpdatedBy(usuarioId);

            VendaDao vendaDAO = new VendaDao();
            vendaDAO.merge(venda);

            Messages.addGlobalInfo("Registro salvo com sucesso");

            vendas = vendaDAO.listar();
            novo();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void editar(ActionEvent evento) {

        try {

            venda = (Venda) evento.getComponent().getAttributes().get("registroSelecionado");

            MatrizDao matrizDao = new MatrizDao();
            FilialDao filialDao = new FilialDao();
            FuncionarioDao funcionarioDao = new FuncionarioDao();
            ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();

            matrizes = matrizDao.listar("nome");
            filiais = filialDao.listar("nome");
            funcionarios = funcionarioDao.listar("nome");
            parceiroNegocios = parceiroNegocioDao.listar("nome");

            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o registro.");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {

        try {

            venda = (Venda) evento.getComponent().getAttributes().get("registroSelecionado");

            VendaDao vendaDAO = new VendaDao();
            vendaDAO.excluir(venda);

            Messages.addGlobalInfo("Registro removido com sucesso.");

            vendas = vendaDAO.listar();
            resetInput();

        } catch (RuntimeException erro) {

            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");

        }

    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroVenda:pnlCadastroVenda");
    }

    /*public void fillFields() {

        try {
            if (matriz != null) {

                VendaDao vendaDao = new VendaDao();
                vendas = vendaDao.buscarPorMatriz(matriz.getId());

            } else {

                vendas = new ArrayList<>();

            }
        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao filtrar as vendas.");
            erro.printStackTrace();

        }
    }
     */
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
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

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}
