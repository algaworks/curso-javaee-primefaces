package com.algaworks;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@RequestScoped
public class Listas {

	@Named
	@Produces
	private List<String> todosServicos = Arrays.asList("Lavagem simples", 
			"Lavagem completa", "Ducha rápida", "Polimento");
	
}