package com.fullstack.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Nota {

	@Id
	private Long numeroNota;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmissao;
	private String descricao;
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal valor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_cnpj", referencedColumnName = "cnpj", nullable = false)
	private Cliente cliente;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "nota", cascade = CascadeType.ALL)

	private List<AnexoNota> anexos = new ArrayList<>();

	public Long getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(Long numeroNota) {
		this.numeroNota = numeroNota;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<AnexoNota> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<AnexoNota> anexos) {
		this.anexos = anexos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroNota == null) ? 0 : numeroNota.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		if (numeroNota == null) {
			if (other.numeroNota != null)
				return false;
		} else if (!numeroNota.equals(other.numeroNota))
			return false;
		return true;
	}

}
