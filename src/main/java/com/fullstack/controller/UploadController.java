package com.fullstack.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/anexo")
public class UploadController {

	@Context
	private ServletContext context;

	@POST
	@Path("/anexoCliente")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadCliente(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		try {

			String dir = "C:\\fullstack\\clientes\\";
			String uploadedFileLocation = "C:\\fullstack\\clientes\\" + fileDetail.getFileName();
			writeToFile(uploadedInputStream, uploadedFileLocation, dir);
			String output = "File uploaded to : " + uploadedFileLocation;
			return Response.status(200).entity(output).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@POST
	@Path("/anexoNota")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadNota(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		try {
			String dir = "C:\\fullstack\\notas\\";
			String uploadedFileLocation = "C:\\fullstack\\notas\\" + fileDetail.getFileName();
			writeToFile(uploadedInputStream, uploadedFileLocation, dir);
			String output = "File uploaded to : " + uploadedFileLocation;
			return Response.status(200).entity(output).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation, String dir) {
		try {
			File file = new File(dir);
			if (file.exists()) {
				System.out.println("diretorio já existe!");
			} else {
				new File(dir).mkdirs();
			}
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
