package br.com.aemcode.farmacia.test.daos;

import br.com.aemcode.farmacia.daos.CidadeDao;
import br.com.aemcode.farmacia.daos.EstadoDao;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.aemcode.farmacia.models.Cidade;
import br.com.aemcode.farmacia.models.Estado;

public class CidadeDaoTest {

    @Test
    @Ignore
    public void salvar() {

        Long codigoEstado = 1L;

        EstadoDao estadoDAO = new EstadoDao();

        Estado estado = estadoDAO.buscar(codigoEstado);

        Cidade cidade = new Cidade();
        cidade.setNome("Guarulhos");
        cidade.setEstado(estado);

        CidadeDao cidadeDAO = new CidadeDao();
        cidadeDAO.salvar(cidade);

    }

    @Test
    @Ignore
    public void listar() {

        CidadeDao cidadeDAO = new CidadeDao();

        List<Cidade> cidades = cidadeDAO.listar();

        if (cidades == null) {
            System.out.println("Registros não encontrado");
        } else {
            for (Cidade cidade : cidades) {
                System.out.println("Código da cidade: " + cidade.getId());
                System.out.println("Nome da cidade: " + cidade.getNome());
                System.out.println("Código do estado: " + cidade.getEstado().getId());
                System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
                System.out.println("Nome do estado: " + cidade.getEstado().getNome());
                System.out.println();
            }
        }
    }

    @Test
    @Ignore
    public void buscar() {
        Long codigo = 4L;

        CidadeDao cidadeDAO = new CidadeDao();

        Cidade cidade = cidadeDAO.buscar(codigo);

        if (cidade == null) {
            System.out.println("Registros não encontrado");
        } else {
            System.out.println("Código da cidade: " + cidade.getId());
            System.out.println("Nome da cidade: " + cidade.getNome());
            System.out.println("Código do estado: " + cidade.getEstado().getId());
            System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
            System.out.println("Nome do estado: " + cidade.getEstado().getNome());
            System.out.println();

        }
    }

    @Test
    @Ignore
    public void excluir() {
        Long codigo = 2L;

        CidadeDao cidadeDAO = new CidadeDao();

        Cidade cidade = cidadeDAO.buscar(codigo);

        if (cidade == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {
            cidadeDAO.excluir(cidade);
            System.out.println("Registro excluido:");
            System.out.println("Código da cidade: " + cidade.getId());
            System.out.println("Nome da cidade: " + cidade.getNome());
            System.out.println("Código do estado: " + cidade.getEstado().getId());
            System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
            System.out.println("Nome do estado: " + cidade.getEstado().getNome());

        }
    }

    @Test
    @Ignore
    public void editar() {

        Long codigoCidade = 4L;
        Long codigoEstado = 4L;

        EstadoDao estadoDAO = new EstadoDao();
        Estado estado = estadoDAO.buscar(codigoEstado);

        System.out.println("Código do estado: " + estado.getId());
        System.out.println("Sigla do estado: " + estado.getSigla());
        System.out.println("Nome do estado: " + estado.getNome());

        CidadeDao cidadeDAO = new CidadeDao();
        Cidade cidade = cidadeDAO.buscar(codigoCidade);

        if (cidade == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {
            System.out.println("Registro a ser editada:");
            System.out.println("Código da cidade: " + cidade.getId());
            System.out.println("Nome da cidade: " + cidade.getNome());
            System.out.println("Código do estado: " + cidade.getEstado().getId());
            System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
            System.out.println("Nome do estado: " + cidade.getEstado().getNome());

            cidade.setNome("Curitiba");
            cidade.setEstado(estado);

            cidadeDAO.editar(cidade);
            System.out.println("Registro editada:");
            System.out.println("Código da cidade: " + cidade.getId());
            System.out.println("Nome da cidade: " + cidade.getNome());
            System.out.println("Código do estado: " + cidade.getEstado().getId());
            System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
            System.out.println("Nome do estado: " + cidade.getEstado().getNome());
        }
    }
}
