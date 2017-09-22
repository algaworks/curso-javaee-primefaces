package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Endereco;
import com.algaworks.pedidovenda.model.TipoPessoa;
import com.algaworks.pedidovenda.service.CadastroClienteService;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	
	private Endereco endereco;
	
	private boolean editandoEndereco;
	
	@Inject
	private CadastroClienteService cadastroClienteService;
	
	public void inicializar(){
		if (cliente == null) {
			limpar();
		}
	}
	
	public void limpar() {
		this.cliente = new Cliente();
		this.cliente.setTipo(TipoPessoa.FISICA);
	}
	
	public void salvar() {
		try {
			cadastroClienteService.salvar(cliente);
			limpar();
			
			FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
		} catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void novoEndereco() {
		this.endereco = new Endereco();
		this.endereco.setCliente(this.cliente);
		this.editandoEndereco = false;
	}
	
	public void editarEndereco(Endereco endereco) {
		this.endereco = endereco;
		this.editandoEndereco = true;
	}
	
	public void excluirEndereco(Endereco endereco) {
		this.cliente.getEnderecos().remove(endereco);
	}
	
	public void confirmarEndereco() {
		if (!this.cliente.getEnderecos().contains(this.endereco)) {
			this.cliente.getEnderecos().add(this.endereco);
		}
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public boolean isEditando() {
		return cliente != null && cliente.getId() == null;
	}
	
	public boolean isEditandoEndereco() {
		return editandoEndereco;
	}
	
	public TipoPessoa[] getTipos(){
		return TipoPessoa.values();
	}
}
