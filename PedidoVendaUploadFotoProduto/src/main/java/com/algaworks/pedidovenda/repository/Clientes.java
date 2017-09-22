package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.repository.filter.ClienteFilter;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Cliente porId(Long id) {
		return this.manager.find(Cliente.class, id);
	}
	
	public List<Cliente> porNome(String nome) {
		return this.manager.createQuery("from Cliente " +
				"where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
	
	public List<Cliente> filtrados(ClienteFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = builder.createQuery(Cliente.class);
		List<Predicate> predicates = new ArrayList<>();
		
		Root<Cliente> clienteRoot = criteriaQuery.from(Cliente.class);
		
		if (StringUtils.isNotBlank(filtro.getDocumentoReceitaFederal())) {
			predicates.add(builder.equal(clienteRoot.get("documentoReceitaFederal"), filtro.getDocumentoReceitaFederal()));
		}
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			predicates.add(builder.like(builder.lower(clienteRoot.get("nome")), 
					"%" + filtro.getNome().toLowerCase() + "%"));
		}
		
		criteriaQuery.select(clienteRoot);
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		criteriaQuery.orderBy(builder.asc(clienteRoot.get("nome")));
		
		TypedQuery<Cliente> query = manager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public Cliente guardar(Cliente cliente) {
		return manager.merge(cliente);
	}	
	
	@Transactional
	public void remover(Cliente cliente) throws NegocioException {
		try {
			cliente = porId(cliente.getId());
			manager.remove(cliente);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Cliente não pode ser excluído.");
		}
	}
}