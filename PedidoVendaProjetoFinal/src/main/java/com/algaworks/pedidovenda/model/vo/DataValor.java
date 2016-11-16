package com.algaworks.pedidovenda.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DataValor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date data;
	private BigDecimal valor;

	public DataValor() {
	}
	
	public DataValor(Date data, BigDecimal valor) {
		super();
		this.data = data;
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}