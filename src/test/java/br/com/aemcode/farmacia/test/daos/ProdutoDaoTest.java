package br.com.aemcode.farmacia.test.daos;

import br.com.aemcode.farmacia.daos.ProdutoDao;
import br.com.aemcode.farmacia.daos.ParceiroNegocioDao;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.aemcode.farmacia.models.Produto;
import br.com.aemcode.farmacia.models.ParceiroNegocio;

public class ProdutoDaoTest {

    @Test
    @Ignore
    public void salvar() {

        ParceiroNegocioDao parceiroNegocioDAO = new ParceiroNegocioDao();
        ParceiroNegocio parceiroNegocio = parceiroNegocioDAO.buscar(2L);

        Produto produto = new Produto();

        produto.setDescricao("Dipirona 50 mg");
        produto.setPreco(new BigDecimal("10.60"));

        ProdutoDao produtoDAO = new ProdutoDao();
        produtoDAO.salvar(produto);

        System.out.println("Produto inserido com sucesso.");
    }

    @Test
    @Ignore
    public void listar() {

        ProdutoDao produtoDAO = new ProdutoDao();

        List<Produto> produtos = produtoDAO.listar();

        if (produtos == null) {
            System.out.println("Registro não encontrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println("Código da produto: " + produto.getId());
                System.out.println("Nome da produto: " + produto.getDescricao());
                System.out.println("Código do parceiroNegocio: " + produto.getProdutoTipo().getDescricao());
                System.out.println("Nome do parceiroNegocio: " + produto.getPreco());
                System.out.println();
            }
        }

    }

    @Test
    @Ignore
    public void buscar() {

        Long codigoProduto = 1L;

        ProdutoDao produtoDAO = new ProdutoDao();

        Produto produto = produtoDAO.buscar(codigoProduto);

        if (produto == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Código da produto: " + produto.getId());
            System.out.println("Nome da produto: " + produto.getDescricao());
            System.out.println("Código do parceiroNegocio: " + produto.getProdutoTipo().getDescricao());
            System.out.println("Nome do parceiroNegocio: " + produto.getPreco());
            System.out.println();

        }
    }

    @Test
    @Ignore
    public void excluir() {

        Long codigoProduto = 2L;

        ProdutoDao produtoDAO = new ProdutoDao();

        Produto produto = produtoDAO.buscar(codigoProduto);

        if (produto == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Registro a excluir:");
            System.out.println("Código da produto: " + produto.getId());
            System.out.println("Nome da produto: " + produto.getDescricao());
            System.out.println("Código do parceiroNegocio: " + produto.getProdutoTipo().getDescricao());
            System.out.println("Nome do parceiroNegocio: " + produto.getPreco());
            System.out.println();

            produtoDAO.excluir(produto);

            System.out.println("Registro excluído:");
            System.out.println("Código da produto: " + produto.getId());
            System.out.println("Nome da produto: " + produto.getDescricao());
            System.out.println("Código do parceiroNegocio: " + produto.getProdutoTipo().getDescricao());
            System.out.println("Nome do parceiroNegocio: " + produto.getPreco());
        }

    }

    @Test
    @Ignore
    public void editar() {

        Long codigoProduto = 3L;
        Long codigoParceiroNegocio = 1L;

        ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();
        ParceiroNegocio parceiroNegocio = parceiroNegocioDao.buscar(codigoParceiroNegocio);

        ProdutoDao produtoDAO = new ProdutoDao();
        Produto produto = produtoDAO.buscar(codigoProduto);

        if (produto == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {

            System.out.println("Registro a ser editada:");

            System.out.println("Código da produto: " + produto.getId());
            System.out.println("Nome da produto: " + produto.getDescricao());
            System.out.println("Código do parceiroNegocio: " + produto.getProdutoTipo().getDescricao());
            System.out.println("Nome do parceiroNegocio: " + produto.getPreco());

            produto.setDescricao("Metiolate");

            produtoDAO.editar(produto);

            System.out.println("Registro editado:");

            System.out.println("Código da produto: " + produto.getId());
            System.out.println("Nome da produto: " + produto.getDescricao());
            System.out.println("Código do parceiroNegocio: " + produto.getProdutoTipo().getDescricao());
            System.out.println("Nome do parceiroNegocio: " + produto.getPreco());
        }
    }

}
