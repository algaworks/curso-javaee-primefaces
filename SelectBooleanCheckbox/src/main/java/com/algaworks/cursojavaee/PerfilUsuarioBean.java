package com.algaworks.cursojavaee;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class PerfilUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Boolean aceito;

    public void atualizar() {
    	System.out.println("Aceito: " + this.aceito);
    	
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Perfil atualizado!"));
    }
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAceito() {
		return aceito;
	}

	public void setAceito(Boolean aceito) {
		this.aceito = aceito;
	}

}