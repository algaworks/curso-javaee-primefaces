package com.algaworks;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("checkout")
@ConversationScoped
public class CheckoutBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private OrdemServico ordemServico = new OrdemServico();
	
	@Inject
	private Conversation conversacao;
	
	public String validar() {
		if (conversacao.isTransient()) {
			return "Home?faces-redirect=true";
		}
		
		return null;
	}
	
	public String iniciar() {
		if (conversacao.isTransient()) {
			conversacao.begin();
		}
		
		return "SelecaoServico?faces-redirect=true";
	}
	
	public void finalizar() {
		if (!conversacao.isTransient()) {
			conversacao.end();
		}
	}
	
	public String emitirRecibo() {
		return "Recibo?faces-redirect=true";
	}
	
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

}