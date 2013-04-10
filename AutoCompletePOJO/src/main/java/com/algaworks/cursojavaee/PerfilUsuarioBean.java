package com.algaworks.cursojavaee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class PerfilUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final List<Pais> PAISES = new ArrayList<>();

	static {
        PAISES.add(new Pais(1, "Alemanha"));
        PAISES.add(new Pais(2, "Argélia"));
        PAISES.add(new Pais(3, "Argentina"));
        PAISES.add(new Pais(4, "Bélgica"));
        PAISES.add(new Pais(5, "Bolívia"));
        PAISES.add(new Pais(6, "Brasil"));
        PAISES.add(new Pais(7, "Bulgaria"));
        PAISES.add(new Pais(8, "Espanha"));
        PAISES.add(new Pais(9, "Estados Unidos"));
	}
	
	private String nome;
	private Pais pais;

	public List<Pais> sugerirPaises(String consulta) {
        List<Pais> paisesSugeridos = new ArrayList<>();
        
        for (Pais pais : PAISES) {
            if (pais.getNome().toLowerCase().startsWith(consulta.toLowerCase())) {
                paisesSugeridos.add(pais);
            }
        }
        
        return paisesSugeridos;
	}
	
    public void atualizar() {
    	//System.out.println("Atualizar");
    	if (this.pais == null) {
    		adicionarMensagem("Perfil atualizado sem país.");
    	} else {
    		adicionarMensagem("Perfil atualizado com país " + this.pais.getNome()
    				+ " (" + this.pais.getCodigo() + ").");
    	}
    }
    
    private void adicionarMensagem(String msg) {
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
    }
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}