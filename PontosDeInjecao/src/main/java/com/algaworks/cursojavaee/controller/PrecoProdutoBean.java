package com.algaworks.cursojavaee.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.service.CalculadoraPreco;

@Named("meuBean")
@RequestScoped
public class PrecoProdutoBean {

	//@Inject
	private CalculadoraPreco calculadora;
	
	public PrecoProdutoBean() {
	}
	
	@Inject
	public PrecoProdutoBean(CalculadoraPreco calculadora) {
		System.out.println("Construtor: " + calculadora);
		this.calculadora = calculadora;
	}
	
	public double getPreco() {
		return calculadora.calcularPreco(12, 45.32);
	}
	
	//@Inject
	//public void setCalculadora(CalculadoraPreco calculadora) {
	//	System.out.println("setCalculadora: " + calculadora);
	//	this.calculadora = calculadora;
	//}
	
}