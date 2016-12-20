package com.fullstack.teste;

 
import org.junit.Test;

import com.fullstack.dao.ClienteDAO;
import com.fullstack.model.Cliente;
import com.fullstack.model.RegimeTributario;

public class ClienteDAOTeste {
	
	@Test
	public void salvar(){
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente();
		cliente.setCnpj("13.421.123/0001-44");
		cliente.setRazaoSocial("Unimed");
		cliente.setRegimeTributario(RegimeTributario.Simples_Nacional);
		clienteDAO.salvar(cliente);
	}

}
