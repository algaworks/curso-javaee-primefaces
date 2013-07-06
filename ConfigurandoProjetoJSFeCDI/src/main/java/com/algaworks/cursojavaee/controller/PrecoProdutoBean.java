package com.algaworks.cursojavaee.controller;

import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.service.CalculadoraPreco;

@Named("meuBean")
public class PrecoProdutoBean {

	@Inject
	private CalculadoraPreco calculadora;
	
	public double getPreco() {
		return calculadora.calcularPreco(12, 44.55);
	}
	
}