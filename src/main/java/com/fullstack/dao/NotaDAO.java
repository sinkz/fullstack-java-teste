package com.fullstack.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.fullstack.model.Cliente;
import com.fullstack.model.Nota;
import com.fullstack.util.HibernateUtil;

public class NotaDAO extends GenericDAO<Nota> {

	@SuppressWarnings("unchecked")
	public List<Nota> listarPorClientes(Cliente cliente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = session.beginTransaction();
			Criteria consulta = session.createCriteria(Nota.class);
			consulta.add(Restrictions.and(Restrictions.eq("cliente.cnpj", cliente.getCnpj())));
			List<Nota> result = new ArrayList<Nota>();
			// add elements to al, including duplicates
			Set<Nota> notas = new HashSet<>();

			result = consulta.list();
			notas.addAll(result);
			result.clear();
			result.addAll(notas);
			for (Nota n : result) {
				System.out.println(n.getNumeroNota());
			}
			transacao.commit();
			return result;
		} catch (RuntimeException e) {
			if (transacao != null)
				transacao.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

}
