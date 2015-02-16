package com.algaworks;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

public class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank @CreditCardNumber
	private String numeroCartao;
	
	@NotNull @Max(12) @Min(1)
	private Integer mesValidade;
	
	@NotNull @Min(2000)
	private Integer anoValidade;
	
	@NotNull
	private Integer codigoSeguranca;
	
	public String getNumeroCartaoProtegido() {
		String numero = numeroCartao.substring(numeroCartao.length() - 4, numeroCartao.length());
		numero = numeroCartao.substring(0, numeroCartao.length() - 4).replaceAll(".", "#") + numero;
		return numero;
	}
	
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public Integer getMesValidade() {
		return mesValidade;
	}
	public void setMesValidade(Integer mesValidade) {
		this.mesValidade = mesValidade;
	}
	public Integer getAnoValidade() {
		return anoValidade;
	}
	public void setAnoValidade(Integer anoValidade) {
		this.anoValidade = anoValidade;
	}
	public Integer getCodigoSeguranca() {
		return codigoSeguranca;
	}
	public void setCodigoSeguranca(Integer codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}
	
}