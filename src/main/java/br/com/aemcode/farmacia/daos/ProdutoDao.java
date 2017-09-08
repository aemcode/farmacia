package br.com.aemcode.farmacia.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.aemcode.farmacia.models.Produto;
import br.com.aemcode.farmacia.models.ProdutoCategoria;
import br.com.aemcode.farmacia.models.ProdutoTipo;
import br.com.aemcode.farmacia.util.HibernateUtil;

public class ProdutoDao extends GenericDao<Produto> {

    public List<ProdutoCategoria> buscarPorCategoria(Long produtoCategoria_id) {
        
        Session sessao = HibernateUtil.getCriarSessao().openSession();
        
        try {
        
            Criteria consulta = sessao.createCriteria(ProdutoCategoria.class);
            
            consulta.add(Restrictions.eq("produtoCategoria.id", produtoCategoria_id));
            
            consulta.addOrder(Order.asc("nome"));
            
            List<ProdutoCategoria> resultado = consulta.list();
            
            return resultado;
            
        } catch (RuntimeException erro) {

            throw erro;

        } finally {

            sessao.close();

        }
    }
    
    public List<ProdutoTipo> buscarPorProdutoTipo(Long produtoTipo_id) {
        
        Session sessao = HibernateUtil.getCriarSessao().openSession();
        
        try {
        
            Criteria consulta = sessao.createCriteria(ProdutoTipo.class);
            
            consulta.add(Restrictions.eq("produtoTipo.id", produtoTipo_id));
            
            consulta.addOrder(Order.asc("nome"));
            
            List<ProdutoTipo> resultado = consulta.list();
            
            return resultado;
            
        } catch (RuntimeException erro) {

            throw erro;

        } finally {

            sessao.close();

        }
    }
}
