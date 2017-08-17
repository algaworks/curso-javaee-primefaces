package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.pedidovenda.model.Grupo;

public class Grupos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public List<Grupo> todos() {
		return this.manager.createQuery("from Grupo", Grupo.class)
				.getResultList();
	}
	
	public Grupo porId(Long id) {
		return this.manager.find(Grupo.class, id);
	}
}