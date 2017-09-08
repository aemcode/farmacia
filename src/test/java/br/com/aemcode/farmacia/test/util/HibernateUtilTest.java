package br.com.aemcode.farmacia.test.util;

import br.com.aemcode.farmacia.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;

public class HibernateUtilTest {

	@Test
        @Ignore
	public void conectar() {
		Session sessao = HibernateUtil.getCriarSessao().openSession();
		sessao.close();
		HibernateUtil.getCriarSessao().close();
	}
}
