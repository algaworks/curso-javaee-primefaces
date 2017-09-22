package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.Usuarios;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Transactional
	public Usuario salvar(Usuario usuario) throws NegocioException {
		if (usuario.getId() != null && StringUtils.isBlank(usuario.getSenha())) {
			usuario.setSenha(usuarios.buscarSenha(usuario.getId()));
		}
		
		return usuarios.guardar(usuario);
	}
}
