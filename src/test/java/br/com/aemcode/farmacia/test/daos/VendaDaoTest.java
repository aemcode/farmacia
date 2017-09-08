package br.com.aemcode.farmacia.test.daos;

import br.com.aemcode.farmacia.daos.ParceiroNegocioDao;
import br.com.aemcode.farmacia.daos.FuncionarioDao;
import br.com.aemcode.farmacia.daos.VendaDao;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.aemcode.farmacia.models.Venda;
import br.com.aemcode.farmacia.models.ParceiroNegocio;
import br.com.aemcode.farmacia.models.Funcionario;

public class VendaDaoTest {

    @Test
    @Ignore
    public void salvar() throws ParseException {

        FuncionarioDao funcionarioDAO = new FuncionarioDao();
        Funcionario funcionario = funcionarioDAO.buscar(1L);

        ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();
        ParceiroNegocio parceiroNegocio = parceiroNegocioDao.buscar(1L);

        Venda venda = new Venda();

        venda.setParceiroNegocio(parceiroNegocio);
        venda.setFuncionario(funcionario);
//        venda.setCreated(new SimpleDateFormat("dd/MM/yyyy").parse("17/08/2017"));
        venda.setValorTotal(new BigDecimal("150.00"));

        VendaDao vendaDAO = new VendaDao();
        vendaDAO.salvar(venda);

        System.out.println("Ítem salvo com sucesso.");

    }

    @Test
    @Ignore
    public void listar() {

        VendaDao vendaDAO = new VendaDao();

        List<Venda> vendas = vendaDAO.listar();

        if (vendas == null) {
            System.out.println("Registro não encontrado.");
        } else {
            for (Venda venda : vendas) {
                System.out.println("Código da Venda: " + venda.getId());
//                System.out.println("Data de cadastro: " + venda.getCreated());
                System.out.println("Status: " + venda.getValorTotal());

                System.out.println("Nome do ParceiroNegocio: " + venda.getParceiroNegocio().getNome());
                System.out.println("Nome do Funcionário: " + venda.getFuncionario().getPessoa().getNome());

                System.out.println();
            }
        }

    }

    @Test
    @Ignore
    public void buscar() {

        Long codigoVenda = 1L;

        VendaDao vendaDAO = new VendaDao();

        Venda venda = vendaDAO.buscar(codigoVenda);

        if (venda == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Código da Venda: " + venda.getId());
//            System.out.println("Data de cadastro: " + venda.getCreated());
            System.out.println("Status: " + venda.getValorTotal());

            System.out.println("Nome do ParceiroNegocio: " + venda.getParceiroNegocio().getNome());
            System.out.println("Nome do Funcionário: " + venda.getFuncionario().getPessoa().getNome());

            System.out.println();
        }
    }

    @Test
    @Ignore
    public void excluir() {

        Long codigoVenda = 2L;

        VendaDao vendaDAO = new VendaDao();

        Venda venda = vendaDAO.buscar(codigoVenda);

        if (venda == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Registro a excluir:");
            System.out.println("Código da Venda: " + venda.getId());
//            System.out.println("Data de cadastro: " + venda.getCreated());
            System.out.println("Status: " + venda.getValorTotal());

            System.out.println("Nome do ParceiroNegocio: " + venda.getParceiroNegocio().getNome());
            System.out.println("Nome do Funcionário: " + venda.getFuncionario().getPessoa().getNome());

            System.out.println();
            vendaDAO.excluir(venda);

            System.out.println("Registro excluído:");
            System.out.println("Código da Venda: " + venda.getId());
//            System.out.println("Data de cadastro: " + venda.getCreated());
            System.out.println("Status: " + venda.getValorTotal());

            System.out.println("Nome do ParceiroNegocio: " + venda.getParceiroNegocio().getNome());
            System.out.println("Nome do Funcionário: " + venda.getFuncionario().getPessoa().getNome());

            System.out.println();
        }

    }

    @Test
    @Ignore
    public void editar() {

        Long codigoVenda = 2L;
        Long codigoFuncionario = 2L;

        VendaDao vendaDAO = new VendaDao();
        Venda venda = vendaDAO.buscar(codigoVenda);

        FuncionarioDao funcionarioDAO = new FuncionarioDao();
        Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario);

        if (venda == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {

            System.out.println("Registro a ser editada:");

            System.out.println("Código da Venda: " + venda.getId());
//            System.out.println("Data de cadastro: " + venda.getCreated());
            System.out.println("Status: " + venda.getValorTotal());

            System.out.println("Nome do ParceiroNegocio: " + venda.getParceiroNegocio().getNome());
            System.out.println("Nome do Funcionário: " + venda.getFuncionario().getPessoa().getNome());

            System.out.println();

            venda.setValorTotal(new BigDecimal("11.50"));

            venda.setFuncionario(funcionario);

            vendaDAO.editar(venda);

            System.out.println("Registro editado:");

            System.out.println("Código da Venda: " + venda.getId());
//            System.out.println("Data de cadastro: " + venda.getCreated());
            System.out.println("Status: " + venda.getValorTotal());

            System.out.println("Nome do ParceiroNegocio: " + venda.getParceiroNegocio().getNome());
            System.out.println("Nome do Funcionário: " + venda.getFuncionario().getPessoa().getNome());

            System.out.println();

        }
    }

}
