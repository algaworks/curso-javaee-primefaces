package com.algaworks.cursojavaee.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.cursojavaee.service.CalculadoraPreco;

@Named("meuBean")
@SessionScoped
public class PrecoProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CalculadoraPreco calculadora;
	
	@PostConstruct
	public void init() {
		System.out.println("Init PrecoProdutoBean");
	}
	
	public double getPreco() {
		//System.out.println(calculadora.getClass());
		return calculadora.calcularPreco(12, 45.32);
	}
	
}