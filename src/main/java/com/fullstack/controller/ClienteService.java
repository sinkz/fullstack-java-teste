package com.fullstack.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fullstack.dao.ClienteDAO;
import com.fullstack.model.Cliente;

@Path("/cliente")
public class ClienteService {
	 

	@Path("/getAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> clientes = clienteDAO.listAll();
		return Response.status(200).entity(clientes).build();
	}

}
