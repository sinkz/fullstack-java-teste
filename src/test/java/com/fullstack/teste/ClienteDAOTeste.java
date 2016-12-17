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
		cliente.setCnpj("18.923.586/0001-21");
		cliente.setRazaoSocial("UnionDev");
		cliente.setRegimeTributario(RegimeTributario.Lucro_Presumido);
		clienteDAO.salvar(cliente);
	}

}
