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
import br.com.aemcode.farmacia.daos.FilialDao;
import br.com.aemcode.farmacia.daos.MatrizDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.Cidade;
import br.com.aemcode.farmacia.models.Estado;
import br.com.aemcode.farmacia.models.Filial;
import br.com.aemcode.farmacia.models.Matriz;
import br.com.aemcode.farmacia.models.Usuario;
import java.util.Date;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andre
 */
@ManagedBean(name = "filialBean")
@ViewScoped
public class FilialBean implements Serializable {

    private Matriz matriz;
    private Filial filial;
    private Cidade cidade;
    private Estado estado;

    private List<Matriz> matrizes;
    private List<Filial> filiais;
    private List<Cidade> cidades;
    private List<Estado> estados;

    @PostConstruct
    public void listar() {

        try {

            FilialDao filialDao = new FilialDao();
            filiais = filialDao.listar("nome");

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao listar os registros");
            erro.printStackTrace();

        }
    }

    public void novo() {

        try {

            filial = new Filial();

            MatrizDao matrizDao = new MatrizDao();
            CidadeDao cidadeDao = new CidadeDao();
            EstadoDao estadoDao = new EstadoDao();

            matrizes = matrizDao.listar("nome");
            cidades = cidadeDao.listar("nome");
            estados = estadoDao.listar("nome");

            /*A linha abaixo é para limpar a lista de registros*/
            //matrizes = new ArrayList<>();
            cidades = new ArrayList<>();
            //estados = new ArrayList<>();
            
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

            filial.setAtivo(true);
            filial.setCreated(new Date());
            filial.setCreatedBy(usuarioId);
            filial.setUpdated(new Date());
            filial.setUpdatedBy(usuarioId);

            FilialDao filialDao = new FilialDao();
            filialDao.merge(filial);

            Messages.addGlobalInfo("Registro salvo com sucesso");

            filiais = filialDao.listar();

            novo();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void editar(ActionEvent evento) {

        try {

            filial = (Filial) evento.getComponent().getAttributes().get("registroSelecionado");

            EstadoDao estadoDao = new EstadoDao();
            estados = estadoDao.listar();

            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o registro.");
            erro.printStackTrace();
        }

    }

    public void excluir(ActionEvent evento) {
        try {

            filial = (Filial) evento.getComponent().getAttributes().get("registroSelecionado");

            FilialDao filialDao = new FilialDao();
            filialDao.excluir(filial);

            Messages.addGlobalInfo("Registro removido com sucesso.");

            filiais = filialDao.listar();

            resetInput();

        } catch (RuntimeException erro) {

            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");

        }
    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroFilial:pnlCadastroFilial");
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

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public List<Filial> getFilials() {
        return filiais;
    }

    public void setFilials(List<Filial> filiais) {
        this.filiais = filiais;
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

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
