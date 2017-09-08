package br.com.aemcode.farmacia.test.daos;

import br.com.aemcode.farmacia.daos.EstadoDao;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.aemcode.farmacia.models.Estado;

public class EstadoDaoTest {

    @Test
    @Ignore
    public void salvar() {

        Estado estado = new Estado();
        estado.setNome("São Paulo");
        estado.setSigla("SP");

        EstadoDao estadoDAO = new EstadoDao();
        estadoDAO.salvar(estado);
    }

    @Test
    @Ignore
    public void listar() {
        EstadoDao estadoDAO = new EstadoDao();
        List<Estado> resultado = estadoDAO.listar();

        System.out.println("total de registros: " + resultado.size());

        for (Estado estado : resultado) {
            System.out.println(estado.getSigla() + " - " + estado.getNome());
        }
    }

    @Test
    @Ignore
    public void buscar() {

        Long codigo = 2L;

        EstadoDao estadoDAO = new EstadoDao();

        Estado estado = estadoDAO.buscar(codigo);

        if (estado == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {
            System.out.println("Registro encontrado:");
            System.out.println(estado.getSigla() + " - " + estado.getNome());
        }
    }

    @Test
    @Ignore
    public void excluir() {

        Long codigo = 2L;

        EstadoDao estadoDAO = new EstadoDao();

        Estado estado = estadoDAO.buscar(codigo);

        if (estado == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {
            estadoDAO.excluir(estado);
            System.out.println("Registro excluido:");
            System.out.println(estado.getSigla() + " - " + estado.getNome());
        }

    }

    @Test
    @Ignore
    public void editar() {

        Long codigo = 1L;

        EstadoDao estadoDAO = new EstadoDao();

        Estado estado = estadoDAO.buscar(codigo);

        if (estado == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {

            System.out.println("Registro editado - Antes.");
            System.out.println(estado.getSigla() + " - " + estado.getNome());

            estado.setSigla("SP");
            estadoDAO.editar(estado);

            System.out.println("Registro editado - Depois");
            System.out.println(estado.getSigla() + " - " + estado.getNome());
        }

    }
}
