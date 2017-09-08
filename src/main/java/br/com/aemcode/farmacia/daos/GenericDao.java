package br.com.aemcode.farmacia.daos;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.aemcode.farmacia.util.HibernateUtil;
import org.hibernate.criterion.Order;

public class GenericDao<Entidade> {

    private Class<Entidade> classe;

    public GenericDao() {
        this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void salvar(Entidade entidade) {

        Session sessao = HibernateUtil.getCriarSessao().openSession();

        Transaction transacao = null;

        try {

            transacao = sessao.beginTransaction();
            sessao.save(entidade);
            transacao.commit();

        } catch (RuntimeException erro) {

            if (transacao != null) {
                transacao.rollback();
            }
            throw erro;

        } finally {
            sessao.close();
        }

    }

    public void merge(Entidade entidade) {

        Session sessao = HibernateUtil.getCriarSessao().openSession();

        Transaction transacao = null;

        try {

            transacao = sessao.beginTransaction();
            sessao.merge(entidade);
            transacao.commit();

        } catch (RuntimeException erro) {

            if (transacao != null) {
                transacao.rollback();
            }
            throw erro;

        } finally {
            sessao.close();
        }

    }

    public List<Entidade> listar() {

        Session sessao = HibernateUtil.getCriarSessao().openSession();

        try {

            Criteria consulta = sessao.createCriteria(classe);

            List<Entidade> resultado = consulta.list();
            return resultado;

        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }

    public List<Entidade> listar(String order) {

        Session sessao = HibernateUtil.getCriarSessao().openSession();

        try {

            Criteria consulta = sessao.createCriteria(classe);
            
            consulta.addOrder(Order.asc(order));
            
            List<Entidade> resultado = consulta.list();
            
            return resultado;

        } catch (RuntimeException erro) {

            throw erro;

        } finally {

            sessao.close();

        }
    }

    public Entidade buscar(Long codigo) {

        Session sessao = HibernateUtil.getCriarSessao().openSession();

        try {

            Criteria consulta = sessao.createCriteria(classe);
            consulta.add(Restrictions.idEq(codigo));
            Entidade resultado = (Entidade) consulta.uniqueResult();

            return resultado;

        } catch (RuntimeException erro) {

            throw erro;

        } finally {

            sessao.close();

        }

    }

    public void excluir(Entidade entidade) {

        Session sessao = HibernateUtil.getCriarSessao().openSession();

        Transaction transacao = null;

        try {

            transacao = sessao.beginTransaction();
            sessao.delete(entidade);
            transacao.commit();

        } catch (RuntimeException erro) {

            if (transacao != null) {
                transacao.rollback();
            }
            throw erro;

        } finally {
            sessao.close();
        }

    }

    public void editar(Entidade entidade) {

        Session sessao = HibernateUtil.getCriarSessao().openSession();

        Transaction transacao = null;

        try {

            transacao = sessao.beginTransaction();
            sessao.update(entidade);
            transacao.commit();

        } catch (RuntimeException erro) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw erro;

        } finally {
            sessao.close();
        }

    }
}
