package br.com.aemcode.farmacia.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.aemcode.farmacia.models.Cidade;
import br.com.aemcode.farmacia.util.HibernateUtil;

public class CidadeDao extends GenericDao<Cidade> {

    public List<Cidade> buscarPorEstado(Long estado_id) {
        
        Session sessao = HibernateUtil.getCriarSessao().openSession();
        
        try {
        
            Criteria consulta = sessao.createCriteria(Cidade.class);
            
            consulta.add(Restrictions.eq("estado.id", estado_id));
            
            consulta.addOrder(Order.asc("nome"));
            
            List<Cidade> resultado = consulta.list();
            
            return resultado;
            
        } catch (RuntimeException erro) {

            throw erro;

        } finally {

            sessao.close();

        }
    }
}
