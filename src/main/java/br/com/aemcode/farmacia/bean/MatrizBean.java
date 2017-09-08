/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.bean;

import br.com.aemcode.farmacia.daos.CidadeDao;
import br.com.aemcode.farmacia.daos.EstadoDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.aemcode.farmacia.daos.MatrizDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.Cidade;
import br.com.aemcode.farmacia.models.Estado;
import br.com.aemcode.farmacia.models.Matriz;
import br.com.aemcode.farmacia.models.Usuario;
import java.util.Date;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andre
 */
@ManagedBean(name="matrizBean")
@ViewScoped
public class MatrizBean implements Serializable {

    private Matriz matriz;
    private Cidade cidade;
    private Estado estado;

    private List<Matriz> matrizes;
    private List<Cidade> cidades;
    private List<Estado> estados;

    @PostConstruct
    public void listar() {

        try {

            MatrizDao matrizDao = new MatrizDao();
            matrizes = matrizDao.listar("nome");

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao listar os registros");
            erro.printStackTrace();

        }
    }

    public void novo() {

        try {

            matriz = new Matriz();

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

            matriz.setAtivo(true);
            matriz.setCreated(new Date());
            matriz.setCreatedBy(usuarioId);
            matriz.setUpdated(new Date());
            matriz.setUpdatedBy(usuarioId);

            MatrizDao matrizDAO = new MatrizDao();
            matrizDAO.merge(matriz);

            Messages.addGlobalInfo("Registro salvo com sucesso");

            matrizes = matrizDAO.listar();

            novo();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void excluir(ActionEvent evento) {

        try {

            matriz = (Matriz) evento.getComponent().getAttributes().get("registroSelecionado");

            MatrizDao matrizDAO = new MatrizDao();
            matrizDAO.excluir(matriz);

            Messages.addGlobalInfo("Registro removido com sucesso.");
            
            matrizes = matrizDAO.listar();
            resetInput();

        } catch (RuntimeException erro) {

            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");

        }

    }

    public void editar(ActionEvent evento) {
        
         try {
            
            matriz = (Matriz) evento.getComponent().getAttributes().get("registroSelecionado");

            EstadoDao estadoDAO = new EstadoDao();
            estados = estadoDAO.listar();
            
            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o registro.");
            erro.printStackTrace();
        }
    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroMatriz:pnlCadastroMatriz");
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

            Messages.addGlobalError("Erro ao filtrar as matrizes.");
            erro.printStackTrace();

        }
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

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
