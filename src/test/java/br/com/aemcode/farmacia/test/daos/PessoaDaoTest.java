package br.com.aemcode.farmacia.test.daos;

import br.com.aemcode.farmacia.daos.PessoaDao;
import br.com.aemcode.farmacia.daos.CidadeDao;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.aemcode.farmacia.models.Cidade;
import br.com.aemcode.farmacia.models.Pessoa;

public class PessoaDaoTest {

    @Test
    @Ignore
    public void salvar() {

        CidadeDao cidadeDAO = new CidadeDao();
        Cidade cidade = cidadeDAO.buscar(1L);

        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Pessoa 1");
        pessoa.setCpf("00000000001");
        pessoa.setRg("12345601");
        pessoa.setRua("Rua 1");
        pessoa.setNumero(new Short("01"));
        pessoa.setComplemento("");
        pessoa.setBairro("Bairro 1");
        pessoa.setCidade(cidade);
        pessoa.setCep("07100-001");
        pessoa.setEmail("email1@gmail.com");
        pessoa.setCelular("912345601");
        pessoa.setTelefone("999999901");

        PessoaDao pessoaDAO = new PessoaDao();

        pessoaDAO.salvar(pessoa);

        System.out.println("Ítem salvo com sucesso.");

    }

    @Test
    @Ignore
    public void listar() {

        PessoaDao pessoaDAO = new PessoaDao();

        List<Pessoa> pessoas = pessoaDAO.listar();

        if (pessoas == null) {
            System.out.println("Registro não encontrado.");
        } else {
            for (Pessoa pessoa : pessoas) {

                System.out.println("Código da Pessoa: " + pessoa.getNome());
                System.out.println("CPF : " + pessoa.getCpf());
                System.out.println("RG : " + pessoa.getRg());
                System.out.println("Rua : " + pessoa.getRua());
                System.out.println("Número : " + pessoa.getNumero());
                System.out.println("Complemento : " + pessoa.getComplemento());
                System.out.println("Bairro : " + pessoa.getBairro());
                System.out.println("Codigo da cidade : " + pessoa.getCidade().getId());
                System.out.println("Nome da cidade : " + pessoa.getCidade().getNome());
                System.out.println("Cep : " + pessoa.getCep());
                System.out.println("Email : " + pessoa.getEmail());
                System.out.println("Celular : " + pessoa.getCelular());
                System.out.println("Telefone : " + pessoa.getTelefone());

                System.out.println();
            }
        }

    }

    @Test
    @Ignore
    public void buscar() {

        Long codigoPessoa = 1L;

        PessoaDao pessoaDAO = new PessoaDao();

        Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);

        if (pessoa == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Código da Pessoa: " + pessoa.getNome());
            System.out.println("CPF : " + pessoa.getCpf());
            System.out.println("RG : " + pessoa.getRg());
            System.out.println("Rua : " + pessoa.getRua());
            System.out.println("Número : " + pessoa.getNumero());
            System.out.println("Complemento : " + pessoa.getComplemento());
            System.out.println("Bairro : " + pessoa.getBairro());
            System.out.println("Codigo da cidade : " + pessoa.getCidade().getId());
            System.out.println("Nome da cidade : " + pessoa.getCidade().getNome());
            System.out.println("Cep : " + pessoa.getCep());
            System.out.println("Email : " + pessoa.getEmail());
            System.out.println("Celular : " + pessoa.getCelular());
            System.out.println("Telefone : " + pessoa.getTelefone());

        }
    }

    @Test
    @Ignore
    public void excluir() {

        Long codigoPessoa = 2L;

        PessoaDao pessoaDAO = new PessoaDao();

        Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);

        if (pessoa == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Registro a excluir:");
            System.out.println("Código da Pessoa: " + pessoa.getNome());
            System.out.println("CPF : " + pessoa.getCpf());
            System.out.println("RG : " + pessoa.getRg());
            System.out.println("Rua : " + pessoa.getRua());
            System.out.println("Número : " + pessoa.getNumero());
            System.out.println("Complemento : " + pessoa.getComplemento());
            System.out.println("Bairro : " + pessoa.getBairro());
            System.out.println("Codigo da cidade : " + pessoa.getCidade().getId());
            System.out.println("Nome da cidade : " + pessoa.getCidade().getNome());
            System.out.println("Cep : " + pessoa.getCep());
            System.out.println("Email : " + pessoa.getEmail());
            System.out.println("Celular : " + pessoa.getCelular());
            System.out.println("Telefone : " + pessoa.getTelefone());

            pessoaDAO.excluir(pessoa);

            System.out.println("Registro excluído:");
            System.out.println("Código da Pessoa: " + pessoa.getNome());
            System.out.println("CPF : " + pessoa.getCpf());
            System.out.println("RG : " + pessoa.getRg());
            System.out.println("Rua : " + pessoa.getRua());
            System.out.println("Número : " + pessoa.getNumero());
            System.out.println("Complemento : " + pessoa.getComplemento());
            System.out.println("Bairro : " + pessoa.getBairro());
            System.out.println("Codigo da cidade : " + pessoa.getCidade().getId());
            System.out.println("Nome da cidade : " + pessoa.getCidade().getNome());
            System.out.println("Cep : " + pessoa.getCep());
            System.out.println("Email : " + pessoa.getEmail());
            System.out.println("Celular : " + pessoa.getCelular());
            System.out.println("Telefone : " + pessoa.getTelefone());

        }

    }

    @Test
    @Ignore
    public void editar() {

        Long codigoPessoa = 2L;
        Long codigoCidade = 2L;

        CidadeDao cidadeDAO = new CidadeDao();
        Cidade cidade = cidadeDAO.buscar(codigoCidade);

        PessoaDao pessoaDAO = new PessoaDao();
        Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);

        if (pessoa == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {

            System.out.println("Registro a ser editada:");

            System.out.println("Código da Pessoa: " + pessoa.getNome());
            System.out.println("CPF : " + pessoa.getCpf());
            System.out.println("RG : " + pessoa.getRg());
            System.out.println("Rua : " + pessoa.getRua());
            System.out.println("Número : " + pessoa.getNumero());
            System.out.println("Complemento : " + pessoa.getComplemento());
            System.out.println("Bairro : " + pessoa.getBairro());
            System.out.println("Codigo da cidade : " + pessoa.getCidade().getId());
            System.out.println("Nome da cidade : " + pessoa.getCidade().getNome());
            System.out.println("Cep : " + pessoa.getCep());
            System.out.println("Email : " + pessoa.getEmail());
            System.out.println("Celular : " + pessoa.getCelular());
            System.out.println("Telefone : " + pessoa.getTelefone());

            pessoa.setNome("Pessoa 1a");

            pessoa.setCidade(cidade);

            pessoaDAO.editar(pessoa);

            System.out.println("Registro editado:");

            System.out.println("Código da Pessoa: " + pessoa.getNome());
            System.out.println("CPF : " + pessoa.getCpf());
            System.out.println("RG : " + pessoa.getRg());
            System.out.println("Rua : " + pessoa.getRua());
            System.out.println("Número : " + pessoa.getNumero());
            System.out.println("Complemento : " + pessoa.getComplemento());
            System.out.println("Bairro : " + pessoa.getBairro());
            System.out.println("Codigo da cidade : " + pessoa.getCidade().getId());
            System.out.println("Nome da cidade : " + pessoa.getCidade().getNome());
            System.out.println("Cep : " + pessoa.getCep());
            System.out.println("Email : " + pessoa.getEmail());
            System.out.println("Celular : " + pessoa.getCelular());
            System.out.println("Telefone : " + pessoa.getTelefone());

        }
    }

}
