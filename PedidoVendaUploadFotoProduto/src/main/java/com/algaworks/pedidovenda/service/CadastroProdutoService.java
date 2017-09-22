package com.algaworks.pedidovenda.service;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.repository.Produtos;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;
	
	@Inject
	private FotoService fotoService;
	
	@Transactional
	public Produto salvar(Produto produto) throws NegocioException {
		Produto produtoExistente = produtos.porSku(produto.getSku());
		
		if (produtoExistente != null && !produtoExistente.equals(produto)) {
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}
		
		
		if (produtoExistente != null && StringUtils.isNotEmpty(produtoExistente.getFoto()) 
				&& !produtoExistente.getFoto().equals(produto.getFoto())) {
			try {
				fotoService.deletar(produtoExistente.getFoto());
			} catch (IOException e) {				
				throw new NegocioException("Problemas ao tentar remover foto antiga.", e);
			}
		}
		
		try {
			fotoService.recuperarFotoTemporaria(produto.getFoto());
		} catch (IOException e) {
			throw new NegocioException("Não foi possível recuperar foto temporária.", e);
		}
		
		return produtos.guardar(produto);
	}	
}
