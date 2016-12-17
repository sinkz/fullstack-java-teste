package com.fullstack.teste;

import com.fullstack.util.HibernateUtil;

public class TableGenerate {

	public static void main(String[] args) {

		HibernateUtil.getSessionFactory().openSession();

		HibernateUtil.getSessionFactory().close();

	}

}
