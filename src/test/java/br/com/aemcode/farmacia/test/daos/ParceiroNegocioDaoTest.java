package br.com.aemcode.farmacia.test.daos;

import br.com.aemcode.farmacia.daos.PessoaDao;
import br.com.aemcode.farmacia.daos.ParceiroNegocioDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.aemcode.farmacia.models.ParceiroNegocio;
import br.com.aemcode.farmacia.models.Pessoa;

public class ParceiroNegocioDaoTest {

    @Test
    @Ignore
    public void salvar() throws ParseException {
        PessoaDao pessoaDao = new PessoaDao();

        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

        //parceiroNegocio.setCreated(new SimpleDateFormat("dd/MM/yyyy").parse("17/08/2017"));

        //parceiroNegocio.setAtivo(true);

        ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();

        parceiroNegocioDao.salvar(parceiroNegocio);

        System.out.println("ParceiroNegocio salvo com sucesso.");

    }

    @Test
    @Ignore
    public void listar() {

        ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();

        List<ParceiroNegocio> parceiroNegocios = parceiroNegocioDao.listar();

        if (parceiroNegocios == null) {
            System.out.println("Registro não encontrado.");
        } else {
            for (ParceiroNegocio parceiroNegocio : parceiroNegocios) {
                System.out.println("Código da ParceiroNegocio: " + parceiroNegocio.getId());
                System.out.println("Nome do parceiroNegocio: " + parceiroNegocio.getNome());
                //System.out.println("Data de cadastro: " + parceiroNegocio.getCreated());
                //System.out.println("Status: " + parceiroNegocio.getAtivo());
                System.out.println();
            }
        }

    }

    @Test
    @Ignore
    public void buscar() {

        Long codigoParceiroNegocio = 1L;

        ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();

        ParceiroNegocio parceiroNegocio = parceiroNegocioDao.buscar(codigoParceiroNegocio);

        if (parceiroNegocio == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Código da ParceiroNegocio: " + parceiroNegocio.getId());
            System.out.println("Nome do parceiroNegocio: " + parceiroNegocio.getNome());
//            System.out.println("Data de cadastro: " + parceiroNegocio.getCreated());
  //          System.out.println("Status: " + parceiroNegocio.getAtivo());

            System.out.println();

        }
    }

    @Test
    @Ignore
    public void excluir() {

        Long codigoParceiroNegocio = 2L;

        ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();

        ParceiroNegocio parceiroNegocio = parceiroNegocioDao.buscar(codigoParceiroNegocio);

        if (parceiroNegocio == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Registro a excluir:");
            System.out.println("Código da ParceiroNegocio: " + parceiroNegocio.getId());
            System.out.println("Nome do parceiroNegocio: " + parceiroNegocio.getNome());
//            System.out.println("Data de cadastro: " + parceiroNegocio.getCreated());
//            System.out.println("Status: " + parceiroNegocio.getAtivo());

            System.out.println();

            parceiroNegocioDao.excluir(parceiroNegocio);

            System.out.println("Registro excluído:");
            System.out.println("Código da ParceiroNegocio: " + parceiroNegocio.getId());
            System.out.println("Nome do parceiroNegocio: " + parceiroNegocio.getNome());
//            System.out.println("Data de cadastro: " + parceiroNegocio.getCreated());
//            System.out.println("Status: " + parceiroNegocio.getAtivo());

        }

    }

    @Test
    @Ignore
    public void editar() {

        Long codigoParceiroNegocio = 2L;
        Long codigoPessoa = 2L;

        PessoaDao pessoaDao = new PessoaDao();
        Pessoa pessoa = pessoaDao.buscar(codigoPessoa);

        System.out.println("Código do Pessoa: " + pessoa.getId());
        System.out.println("Sigla do Pessoa: " + pessoa.getNome());

        ParceiroNegocioDao parceiroNegocioDao = new ParceiroNegocioDao();
        ParceiroNegocio parceiroNegocio = parceiroNegocioDao.buscar(codigoParceiroNegocio);

        if (parceiroNegocio == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {

            System.out.println("Registro a ser editada:");

            System.out.println("Código da ParceiroNegocio: " + parceiroNegocio.getId());
            System.out.println("Nome do parceiroNegocio: " + parceiroNegocio.getNome());
  //          System.out.println("Data de cadastro: " + parceiroNegocio.getCreated());
  //          System.out.println("Status: " + parceiroNegocio.getAtivo());

//            parceiroNegocio.setAtivo(false);

            parceiroNegocioDao.editar(parceiroNegocio);

            System.out.println("Registro editado:");

            System.out.println("Código da ParceiroNegocio: " + parceiroNegocio.getId());
            System.out.println("Nome do parceiroNegocio: " + parceiroNegocio.getNome());
  //          System.out.println("Data de cadastro: " + parceiroNegocio.getCreated());
  //          System.out.println("Status: " + parceiroNegocio.getAtivo());

        }
    }

}
