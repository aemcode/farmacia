package br.com.aemcode.farmacia.test.daos;

import br.com.aemcode.farmacia.daos.ParceiroNegocioDao;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.aemcode.farmacia.models.ParceiroNegocio;

public class EstoqueDaoTest {

    @Test
    @Ignore
    public void salvar() {

        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setNome("Laboratórios Roche S/A");

        ParceiroNegocioDao parceiroNegocioDAO = new ParceiroNegocioDao();
        parceiroNegocioDAO.salvar(parceiroNegocio);
    }

    @Test
    @Ignore
    public void listar() {
        ParceiroNegocioDao parceiroNegocioDAO = new ParceiroNegocioDao();

        List<ParceiroNegocio> resultado = parceiroNegocioDAO.listar();

        System.out.println("total de registros: " + resultado.size());

        for (ParceiroNegocio parceiroNegocio : resultado) {
            System.out.println(parceiroNegocio.getNome());
        }

        /*resultado.forEach((parceiroNegocio) -> {
            System.out.println(parceiroNegocio.getNome());
        });*/

    }

    @Test
    @Ignore
    public void buscar() {

        Long codigo = 2L;

        ParceiroNegocioDao parceiroNegocioDAO = new ParceiroNegocioDao();

        ParceiroNegocio parceiroNegocio = parceiroNegocioDAO.buscar(codigo);

        if (parceiroNegocio == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {
            System.out.println("Registro encontrado:");
            System.out.println(parceiroNegocio.getId() + " - " + parceiroNegocio.getNome());
        }
    }

    @Test
    @Ignore
    public void excluir() {

        Long codigo = 2L;

        ParceiroNegocioDao parceiroNegocioDAO = new ParceiroNegocioDao();

        ParceiroNegocio parceiroNegocio = parceiroNegocioDAO.buscar(codigo);

        if (parceiroNegocio == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {
            parceiroNegocioDAO.excluir(parceiroNegocio);
            System.out.println("Registro excluido:");
            System.out.println(parceiroNegocio.getId() + " - " + parceiroNegocio.getNome());
        }

    }

    @Test
    @Ignore
    public void editar() {

        Long codigo = 1L;

        ParceiroNegocioDao parceiroNegocioDAO = new ParceiroNegocioDao();

        ParceiroNegocio parceiroNegocio = parceiroNegocioDAO.buscar(codigo);

        if (parceiroNegocio == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {

            System.out.println("Registro editado - Antes.");
            System.out.println(parceiroNegocio.getId() + " - " + parceiroNegocio.getNome());

            parceiroNegocio.setNome("Achê Laboratório");
            parceiroNegocioDAO.editar(parceiroNegocio);

            System.out.println("Registro editado - Depois");
            System.out.println(parceiroNegocio.getId() + " - " + parceiroNegocio.getNome());
        }

    }

}
