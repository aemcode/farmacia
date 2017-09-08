package br.com.aemcode.farmacia.test.daos;

import br.com.aemcode.farmacia.daos.ProdutoDao;
import br.com.aemcode.farmacia.daos.ItemVendaDao;
import br.com.aemcode.farmacia.daos.VendaDao;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.aemcode.farmacia.models.ItemVenda;
import br.com.aemcode.farmacia.models.Produto;
import br.com.aemcode.farmacia.models.Venda;

public class ItemVendaDaoTest {

    @Test
    @Ignore
    public void salvar() throws ParseException {

        ProdutoDao produtoDao = new ProdutoDao();
        Produto produto = produtoDao.buscar(1L);

        VendaDao vendaDao = new VendaDao();
        Venda venda = vendaDao.buscar(1L);

        ItemVenda itemVenda = new ItemVenda();

        itemVenda.setQuantidade(new Short("10"));
        itemVenda.setValor(new BigDecimal("15.00"));
        itemVenda.setProduto(produto);
        itemVenda.setVenda(venda);

        ItemVendaDao itemVendaDao = new ItemVendaDao();

        itemVendaDao.salvar(itemVenda);

        System.out.println("Ítem salvo com sucesso.");

    }

    @Test
    @Ignore
    public void listar() {

        ItemVendaDao itemVendaDao = new ItemVendaDao();

        List<ItemVenda> itemVendas = itemVendaDao.listar();

        if (itemVendas == null) {
            System.out.println("Registro não encontrado.");
        } else {
            for (ItemVenda itemVenda : itemVendas) {
                System.out.println("Código da ItemVenda: " + itemVenda.getId());
                System.out.println("Data de cadastro: " + itemVenda.getQuantidade());
                System.out.println("Status: " + itemVenda.getValor());

                System.out.println("Codigo Venda: " + itemVenda.getVenda().getId());
                System.out.println("Nome do Cliente: " + itemVenda.getVenda().getParceiroNegocio().getId());
                System.out.println("Nome do Funcionário: " + itemVenda.getVenda().getFuncionario().getPessoa().getNome());

                System.out.println("Codigo do produto: " + itemVenda.getProduto().getId());
                System.out.println("Descrição do produto: " + itemVenda.getProduto().getDescricao());

                System.out.println();
            }
        }

    }

    @Test
    @Ignore
    public void buscar() {

        Long codigoItemVenda = 1L;

        ItemVendaDao itemVendaDao = new ItemVendaDao();

        ItemVenda itemVenda = itemVendaDao.buscar(codigoItemVenda);

        if (itemVenda == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Código da ItemVenda: " + itemVenda.getId());
            System.out.println("Data de cadastro: " + itemVenda.getQuantidade());
            System.out.println("Status: " + itemVenda.getValor());

            System.out.println("Codigo Venda: " + itemVenda.getVenda().getId());
            System.out.println("Nome do Cliente: " + itemVenda.getVenda().getParceiroNegocio().getId());
            System.out.println("Nome do Funcionário: " + itemVenda.getVenda().getFuncionario().getPessoa().getNome());

            System.out.println("Codigo do produto: " + itemVenda.getProduto().getId());
            System.out.println("Descrição do produto: " + itemVenda.getProduto().getDescricao());

            System.out.println();

        }
    }

    @Test
    @Ignore
    public void excluir() {

        Long codigoItemVenda = 2L;

        ItemVendaDao itemVendaDao = new ItemVendaDao();

        ItemVenda itemVenda = itemVendaDao.buscar(codigoItemVenda);

        if (itemVenda == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Registro a excluir:");
            System.out.println("Código da ItemVenda: " + itemVenda.getId());
            System.out.println("Data de cadastro: " + itemVenda.getQuantidade());
            System.out.println("Status: " + itemVenda.getValor());

            System.out.println("Codigo Venda: " + itemVenda.getVenda().getId());
            System.out.println("Nome do Cliente: " + itemVenda.getVenda().getParceiroNegocio().getId());
            System.out.println("Nome do Funcionário: " + itemVenda.getVenda().getFuncionario().getPessoa().getNome());

            System.out.println("Codigo do produto: " + itemVenda.getProduto().getId());
            System.out.println("Descrição do produto: " + itemVenda.getProduto().getDescricao());

            System.out.println();

            itemVendaDao.excluir(itemVenda);

            System.out.println("Registro excluído:");
            System.out.println("Código da ItemVenda: " + itemVenda.getId());
            System.out.println("Data de cadastro: " + itemVenda.getQuantidade());
            System.out.println("Status: " + itemVenda.getValor());

            System.out.println("Codigo Venda: " + itemVenda.getVenda().getId());
            System.out.println("Nome do Cliente: " + itemVenda.getVenda().getParceiroNegocio().getId());
            System.out.println("Nome do Funcionário: " + itemVenda.getVenda().getFuncionario().getPessoa().getNome());

            System.out.println("Codigo do produto: " + itemVenda.getProduto().getId());
            System.out.println("Descrição do produto: " + itemVenda.getProduto().getDescricao());

            System.out.println();

        }

    }

    @Test
    @Ignore
    public void editar() {

        Long codigoItemVenda = 2L;
        Long codigoVenda = 2L;

        VendaDao vendaDao = new VendaDao();
        Venda venda = vendaDao.buscar(codigoVenda);

        ItemVendaDao itemVendaDao = new ItemVendaDao();
        ItemVenda itemVenda = itemVendaDao.buscar(codigoItemVenda);

        if (itemVenda == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {

            System.out.println("Registro a ser editada:");

            System.out.println("Código da ItemVenda: " + itemVenda.getId());
            System.out.println("Data de cadastro: " + itemVenda.getQuantidade());
            System.out.println("Status: " + itemVenda.getValor());

            System.out.println("Codigo Venda: " + itemVenda.getVenda().getId());
            System.out.println("Nome do Cliente: " + itemVenda.getVenda().getParceiroNegocio().getId());
            System.out.println("Nome do Funcionário: " + itemVenda.getVenda().getFuncionario().getPessoa().getNome());

            System.out.println("Codigo do produto: " + itemVenda.getProduto().getId());
            System.out.println("Descrição do produto: " + itemVenda.getProduto().getDescricao());

            System.out.println();

            itemVenda.setQuantidade(new Short("11"));

            itemVenda.setVenda(venda);

            itemVendaDao.editar(itemVenda);

            System.out.println("Registro editado:");

            System.out.println("Código da ItemVenda: " + itemVenda.getId());
            System.out.println("Data de cadastro: " + itemVenda.getQuantidade());
            System.out.println("Status: " + itemVenda.getValor());

            System.out.println("Codigo Venda: " + itemVenda.getVenda().getId());
            System.out.println("Nome do Cliente: " + itemVenda.getVenda().getParceiroNegocio().getId());
            System.out.println("Nome do Funcionário: " + itemVenda.getVenda().getFuncionario().getPessoa().getNome());

            System.out.println("Codigo do produto: " + itemVenda.getProduto().getId());
            System.out.println("Descrição do produto: " + itemVenda.getProduto().getDescricao());

            System.out.println();

        }
    }

}
