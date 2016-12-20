package com.fullstack.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Imposto {

	@Id
	@GeneratedValue
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date vencimento;
	@Column(precision = 19, scale = 4)
	private BigDecimal valor;
	@Enumerated(EnumType.STRING)
	private TipoImposto tipoImposto;
	private boolean isPago;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "competencia_id", referencedColumnName = "id", nullable = false)
	private Competencia competencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoImposto getTipoImposto() {
		return tipoImposto;
	}

	public void setTipoImposto(TipoImposto tipoImposto) {
		this.tipoImposto = tipoImposto;
	}

	public boolean isPago() {
		return isPago;
	}

	public void setPago(boolean isPago) {
		this.isPago = isPago;
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

}
