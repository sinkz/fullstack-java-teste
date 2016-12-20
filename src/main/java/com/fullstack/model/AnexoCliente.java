package com.fullstack.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AnexoCliente {

	@Id
	@GeneratedValue
	private Long id;
	private String nomeAnexo;
	private String caminhoAnexo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_cnpj", referencedColumnName = "cnpj", nullable = false)
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeAnexo() {
		return nomeAnexo;
	}

	public void setNomeAnexo(String nomeAnexo) {
		this.nomeAnexo = nomeAnexo;
	}

	public String getCaminhoAnexo() {
		return caminhoAnexo;
	}

	public void setCaminhoAnexo(String caminhoAnexo) {
		this.caminhoAnexo = caminhoAnexo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
