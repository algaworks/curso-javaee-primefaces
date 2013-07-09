package com.algaworks.cursojavaee.service;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CalculadoraPreco {

	public double calcularPreco(int quantidade, double precoUnitario) {
		return quantidade * precoUnitario;
	}
	
}