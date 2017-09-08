package br.com.aemcode.farmacia.test.daos;

import br.com.aemcode.farmacia.daos.PessoaDao;
import br.com.aemcode.farmacia.daos.UsuarioDao;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.aemcode.farmacia.models.Usuario;
import br.com.aemcode.farmacia.models.Pessoa;

public class UsuarioDaoTest {

    @Test
    @Ignore
    public void salvar() {
        PessoaDao pessoaDAO = new PessoaDao();
        Pessoa pessoa = pessoaDAO.buscar(1L);

        Usuario usuario = new Usuario();
        
        usuario.setSenha("a1b2c3");
        usuario.setTipo('A');

        UsuarioDao usuarioDAO = new UsuarioDao();

        usuarioDAO.salvar(usuario);

        System.out.println("Usuario salvo com sucesso.");

    }

    @Test
    @Ignore
    public void listar() {

        UsuarioDao usuarioDAO = new UsuarioDao();

        List<Usuario> usuarios = usuarioDAO.listar();

        if (usuarios == null) {
            System.out.println("Registro não encontrado.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println("Código da Usuario : " + usuario.getId());
                
                System.out.println("Senha : " + usuario.getSenha());
                System.out.println("Tipo : " + usuario.getTipo());

                System.out.println();
            }
        }

    }

    @Test
    @Ignore
    public void buscar() {

        Long codigoUsuario = 1L;

        UsuarioDao usuarioDAO = new UsuarioDao();

        Usuario usuario = usuarioDAO.buscar(codigoUsuario);

        if (usuario == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Código da Usuario : " + usuario.getId());
            
            System.out.println("Senha : " + usuario.getSenha());
            System.out.println("Tipo : " + usuario.getTipo());

            System.out.println();
        }
    }

    @Test
    @Ignore
    public void excluir() {

        Long codigoUsuario = 2L;

        UsuarioDao usuarioDAO = new UsuarioDao();

        Usuario usuario = usuarioDAO.buscar(codigoUsuario);

        if (usuario == null) {
            System.out.println("Registro não encontrado.");
        } else {
            System.out.println("Registro a excluir:");
            System.out.println("Código da Usuario : " + usuario.getId());
            
            System.out.println("Senha : " + usuario.getSenha());
            System.out.println("Tipo : " + usuario.getTipo());

            System.out.println();
            usuarioDAO.excluir(usuario);

            System.out.println("Registro excluído:");
            System.out.println("Código da Usuario : " + usuario.getId());
            
            System.out.println("Senha : " + usuario.getSenha());
            System.out.println("Tipo : " + usuario.getTipo());

            System.out.println();
        }

    }

    @Test
    @Ignore
    public void editar() {

        Long codigoUsuario = 2L;
        Long codigoPessoa = 2L;

        PessoaDao pessoaDAO = new PessoaDao();
        Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);

        System.out.println("Código do Pessoa: " + pessoa.getId());
        System.out.println("Sigla do Pessoa: " + pessoa.getNome());

        UsuarioDao usuarioDAO = new UsuarioDao();
        Usuario usuario = usuarioDAO.buscar(codigoUsuario);

        if (usuario == null) {
            System.out.println("Nenhum registro encontrado...");
        } else {

            System.out.println("Registro a ser editada:");

            System.out.println("Código da Usuario : " + usuario.getId());
            
            System.out.println("Senha : " + usuario.getSenha());
            System.out.println("Tipo : " + usuario.getTipo());

            System.out.println();

            

            usuarioDAO.editar(usuario);

            System.out.println("Registro editado:");

            System.out.println("Código da Usuario : " + usuario.getId());
            
            System.out.println("Senha : " + usuario.getSenha());
            System.out.println("Tipo : " + usuario.getTipo());

            System.out.println();
        }
    }

}
