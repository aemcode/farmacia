package br.com.aemcode.farmacia.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.aemcode.farmacia.models.ItemVenda;
import br.com.aemcode.farmacia.models.Produto;
import br.com.aemcode.farmacia.util.HibernateUtil;

public class ItemVendaDao extends GenericDao<ItemVenda>{

    public List<Produto> buscarPorProduto(Long produto_id) {
        
        Session sessao = HibernateUtil.getCriarSessao().openSession();
        
        try {
        
            Criteria consulta = sessao.createCriteria(Produto.class);
            
            consulta.add(Restrictions.eq("produto.id", produto_id));
            
            consulta.addOrder(Order.asc("nome"));
            
            List<Produto> resultado = consulta.list();
            
            return resultado;
            
        } catch (RuntimeException erro) {

            throw erro;

        } finally {

            sessao.close();

        }
    }
}
