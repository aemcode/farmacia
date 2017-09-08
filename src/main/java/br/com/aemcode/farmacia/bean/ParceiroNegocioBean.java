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

import br.com.aemcode.farmacia.daos.ParceiroNegocioDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import br.com.aemcode.farmacia.models.ParceiroNegocio;
import br.com.aemcode.farmacia.models.Usuario;
import java.util.Date;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andre
 */
@ManagedBean(name = "parceiroNegocioBean")
@ViewScoped
public class ParceiroNegocioBean implements Serializable {

    private ParceiroNegocio parceiroNegocio;

    private List<ParceiroNegocio> parceiroNegocios;

    @PostConstruct
    public void listar() {

        try {

            ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();
            parceiroNegocios = parceiroNegocioDao.listar("nome");

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao listar os registros");
            erro.printStackTrace();

        }
    }

    public void novo() {

        try {

            parceiroNegocio = new ParceiroNegocio();

            /*A linha abaixo é para limpar a lista de parceiroNegocios*/
            //parceiroNegocios = new ArrayList<>();
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

            parceiroNegocio.setAtivo(true);
            parceiroNegocio.setCreated(new Date());
            parceiroNegocio.setCreatedBy(usuarioId);
            parceiroNegocio.setUpdated(new Date());
            parceiroNegocio.setUpdatedBy(usuarioId);

            ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();
            parceiroNegocioDao.merge(parceiroNegocio);

            Messages.addGlobalInfo("Registro salvo com sucesso");
            parceiroNegocios = parceiroNegocioDao.listar();
            novo();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o registro");
        }
    }

    public void editar(ActionEvent evento) {

        try {

            parceiroNegocio = (ParceiroNegocio) evento.getComponent().getAttributes().get("registroSelecionado");
            resetInput();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o registro");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {

        try {

            parceiroNegocio = (ParceiroNegocio) evento.getComponent().getAttributes().get("registroSelecionado");

            ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();
            parceiroNegocioDao.excluir(parceiroNegocio);

            Messages.addGlobalInfo("Registro removido com sucesso.");

            parceiroNegocios = parceiroNegocioDao.listar();
            resetInput();

        } catch (RuntimeException erro) {

            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o registro.");

        }

    }

    public void resetInput() {
        RequestContext.getCurrentInstance().reset("formCadastroParceiroNegocio:pnlCadastroParceiroNegocio");
    }

    /*public void fillFields() {

        try {
            if (estado != null) {

                ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();
                parceiroNegocios = parceiroNegocioDao.buscarPorEstado(estado.getId());

            } else {

                parceiroNegocios = new ArrayList<>();

            }
        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao filtrar as parceiroNegocios.");
            erro.printStackTrace();

        }
    }
     */
    public ParceiroNegocio getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        this.parceiroNegocio = parceiroNegocio;
    }

    public List<ParceiroNegocio> getParceiroNegocios() {
        return parceiroNegocios;
    }

    public void setParceiroNegocios(List<ParceiroNegocio> parceiroNegocios) {
        this.parceiroNegocios = parceiroNegocios;
    }

}
