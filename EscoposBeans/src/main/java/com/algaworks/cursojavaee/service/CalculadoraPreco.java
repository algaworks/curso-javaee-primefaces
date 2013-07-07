package com.algaworks.cursojavaee.service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CalculadoraPreco {

	@PostConstruct
	public void init() {
		System.out.println("Init CalculadoraPreco");
	}
	
	public double calcularPreco(int quantidade, double precoUnitario) {
		return quantidade * precoUnitario;
	}
	
}