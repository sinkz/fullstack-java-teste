package com.fullstack.controller;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fullstack.dao.ClienteDAO;
import com.fullstack.model.AnexoCliente;
import com.fullstack.model.Cliente;

@Path("/cliente")
public class ClienteController {

	@POST
	@Path("/salvar")
	@Consumes("application/json")
	public Response salvarCliente(Cliente cliente) {
		try {
			if (cliente.getAnexos().size() != 0) {
				for (AnexoCliente a : cliente.getAnexos()) {
					System.out.println(a.getNomeAnexo());
					a.setDataCriacao(new Date());
					a.setCliente(cliente);
				}
			}
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.salvar(cliente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/login")
	@Consumes("application/json")
	public Response login(Cliente cliente) {
		try {

			ClienteDAO clienteDAO = new ClienteDAO();
			cliente = clienteDAO.buscarPorCodigo(cliente.getCnpj());
			if (cliente != null) {
				return Response.status(200).entity(cliente).build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path("/editar")
	@Consumes("application/json")
	public Response editarCliente(Cliente cliente) {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.alterar(cliente);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DELETE
	@Path("/excluir")
	@Consumes("application/json")
	public Response excluirCliente(Cliente cliente) {

		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@Path("/listarTodos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarTodos() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			List<Cliente> clientes = clienteDAO.listAll();
			return Response.status(200).entity(clientes).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@Path("/buscarPorCodigo/{cnpj}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorCodigo(@PathParam("cnpj") Long cnpj) {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			return Response.status(200).entity(clienteDAO.buscarPorCodigo(cnpj)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
