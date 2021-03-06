package br.com.aemcode.farmacia.daos;

import br.com.aemcode.farmacia.models.Estado;
import br.com.aemcode.farmacia.models.Filial;
import br.com.aemcode.farmacia.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class FilialDao extends GenericDao<Filial> {

    public List<Estado> buscarPorEstado(Long estado_id) {

        Session sessao = HibernateUtil.getCriarSessao().openSession();

        try {

            Criteria consulta = sessao.createCriteria(Estado.class);

            consulta.add(Restrictions.eq("estado.id", estado_id));

            consulta.addOrder(Order.asc("nome"));

            List<Estado> resultado = consulta.list();

            return resultado;

        } catch (RuntimeException erro) {

            throw erro;

        } finally {

            sessao.close();

        }
    }
}
