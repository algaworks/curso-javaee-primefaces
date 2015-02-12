package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.util.mail.Mailer;
import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	public void enviarPedido() {
		MailMessage message = mailer.novaMensagem();
		
		message.to(this.pedido.getCliente().getEmail())
			.subject("Pedido " + this.pedido.getId())
			.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/pedido.template")))
			.put("pedido", this.pedido)
			.put("numberTool", new NumberTool())
			.put("locale", new Locale("pt", "BR"))
			.send();
		
		FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
	}
	
	
}
