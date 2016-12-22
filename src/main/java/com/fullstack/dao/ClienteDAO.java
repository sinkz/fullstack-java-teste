package com.fullstack.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.fullstack.model.Cliente;
import com.fullstack.util.HibernateUtil;

public class ClienteDAO extends GenericDAO<Cliente> {
	
	public Cliente buscarPorCodigo(String cnpj) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = session.beginTransaction();
			Criteria consulta = session.createCriteria(Cliente.class);
			consulta.add(Restrictions.idEq(cnpj));
			Cliente resultado = (Cliente) consulta.uniqueResult();
			transacao.commit();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

}
