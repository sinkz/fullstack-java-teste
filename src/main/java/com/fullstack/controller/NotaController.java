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
import com.fullstack.dao.NotaDAO;
import com.fullstack.model.AnexoNota;
import com.fullstack.model.Nota;

@Path("/nota")
public class NotaController {

	@POST
	@Path("/salvar")
	@Consumes("application/json")
	public Response salvarNota(Nota nota) {
		try {
			if (nota.getAnexos().size() != 0) {
				for (AnexoNota a : nota.getAnexos()) {
					a.setDataCriacao(new Date());
					a.setNota(nota);
				}
			}
			NotaDAO notaDAO = new NotaDAO();
			ClienteDAO dao = new ClienteDAO();
			notaDAO.salvar(nota);
			return Response.status(200)
					.entity(notaDAO.listarPorClientes(dao.buscarPorCodigo(nota.getCliente().getCnpj()))).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path("/editar")
	@Consumes("application/json")
	public Response editarNota(Nota nota) {
		try {
			NotaDAO notaDAO = new NotaDAO();
			notaDAO.alterar(nota);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DELETE
	@Path("/excluir")
	@Consumes("application/json")
	public Response excluirNota(Nota nota) {

		try {
			NotaDAO notaDAO = new NotaDAO();
			notaDAO.excluir(nota);
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
			NotaDAO notaDAO = new NotaDAO();
			List<Nota> notas = notaDAO.listAll();
			return Response.status(200).entity(notas).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@Path("/listarPorCliente/{cnpj}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarPorCliente(@PathParam("cnpj") Long cnpj) {
		try {
			ClienteDAO dao = new ClienteDAO();
			NotaDAO notaDAO = new NotaDAO();
			List<Nota> notas = notaDAO.listarPorClientes(dao.buscarPorCodigo(cnpj));
			return Response.status(200).entity(notas).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@Path("/buscarPorCodigo/{numeroNota}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorCodigo(@PathParam("numeroNota") Long numeroNota) {
		try {
			NotaDAO notaDAO = new NotaDAO();
			return Response.status(200).entity(notaDAO.buscarPorCodigo(numeroNota)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
