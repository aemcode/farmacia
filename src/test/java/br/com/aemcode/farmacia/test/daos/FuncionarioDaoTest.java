package br.com.aemcode.farmacia.test.daos;

import br.com.aemcode.farmacia.daos.PessoaDao;
import br.com.aemcode.farmacia.daos.FuncionarioDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.aemcode.farmacia.models.Funcionario;
import br.com.aemcode.farmacia.models.Pessoa;

public class FuncionarioDaoTest {

    @Test
    @Ignore
    public void salvar() throws ParseException {

        PessoaDao pessoaDAO = new PessoaDao();
        Pessoa pessoa = pessoaDAO.buscar(1L);

        Funcionario funcionario = new Funcionario();

        funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("21/08/2017"));
        funcionario.setPessoa(pessoa);

        FuncionarioDao funcionarioDAO = new FuncionarioDao();
        funcionarioDAO.salvar(funcionario);

        System.out.println("Funcionario salvo com sucesso.");

    }

    @Test
    @Ignore
    public void listar() {

        FuncionarioDao funcionarioDAO = new FuncionarioDao();

        List<Funcionario> funcionarios = funcionarioDAO.listar();

        if (funcionarios == null) {
            System.out.println("Registro não encontrado.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println("Código da Funcionario: " + funcionario.getId());
                System.out.println("Data de admissao: " + funcionario.getDataAdmissao());

                System.out.println("Código da pessoa: " + funcionario.getPessoa().getId());
                System.out.println("Nome do funcionario: " + funcionario.getPessoa().getNome());

                System.out.println();
            }
        }

    }

    @Test
    @Ignore
    public void buscar() {

        Long codigoFuncionario = 1L;

        FuncionarioDao funcionarioDAO = new FuncionarioDao();

        Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario);

        if (funcionario == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Código da Funcionario: " + funcionario.getId());
            System.out.println("Data de admissao: " + funcionario.getDataAdmissao());

            System.out.println("Código da pessoa: " + funcionario.getPessoa().getId());
            System.out.println("Nome do funcionario: " + funcionario.getPessoa().getNome());

            System.out.println();

        }
    }

    @Test
    @Ignore
    public void excluir() {

        Long codigoFuncionario = 2L;

        FuncionarioDao funcionarioDAO = new FuncionarioDao();

        Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario);

        if (funcionario == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Registro a excluir:");

            System.out.println("Código da Funcionario: " + funcionario.getId());
            System.out.println("Data de admissao: " + funcionario.getDataAdmissao());

            System.out.println("Código da pessoa: " + funcionario.getPessoa().getId());
            System.out.println("Nome do funcionario: " + funcionario.getPessoa().getNome());

            System.out.println();

            funcionarioDAO.excluir(funcionario);

            System.out.println("Registro excluído:");

            System.out.println("Código da Funcionario: " + funcionario.getId());
            System.out.println("Data de admissao: " + funcionario.getDataAdmissao());

            System.out.println("Código da pessoa: " + funcionario.getPessoa().getId());
            System.out.println("Nome do funcionario: " + funcionario.getPessoa().getNome());

        }

    }

    @Test
    @Ignore
    public void editar() {

        Long codigoFuncionario = 2L;
        Long codigoPessoa = 2L;

        PessoaDao pessoaDAO = new PessoaDao();
        Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);

        System.out.println("Código do Pessoa: " + pessoa.getId());
        System.out.println("Sigla do Pessoa: " + pessoa.getNome());

        FuncionarioDao funcionarioDAO = new FuncionarioDao();
        Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario);

        if (funcionario == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {

            System.out.println("Registro a ser editada:");

            System.out.println("Código da Funcionario: " + funcionario.getId());
            System.out.println("Data de admissao: " + funcionario.getDataAdmissao());

            System.out.println("Código da pessoa: " + funcionario.getPessoa().getId());
            System.out.println("Nome do funcionario: " + funcionario.getPessoa().getNome());

            funcionario.setPessoa(pessoa);

            funcionarioDAO.editar(funcionario);

            System.out.println("Registro editado:");

            System.out.println("Código da Funcionario: " + funcionario.getId());
            System.out.println("Data de admissao: " + funcionario.getDataAdmissao());

            System.out.println("Código da pessoa: " + funcionario.getPessoa().getId());
            System.out.println("Nome do funcionario: " + funcionario.getPessoa().getNome());

        }
    }

}
