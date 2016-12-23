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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AnexoNota {

	@Id
	@GeneratedValue
	private Long id;
	private String nomeAnexo;
	private String caminhoAnexo;
	private int tipoAnexo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "nota_numeroNota", referencedColumnName = "numeroNota", nullable = false)
	private Nota nota;

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

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public int getTipoAnexo() {
		return tipoAnexo;
	}

	public void setTipoAnexo(int tipoAnexo) {
		this.tipoAnexo = tipoAnexo;
	}

}
