package com.algaworks.pedidovenda.service;

import static java.nio.file.FileSystems.getDefault;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

public class FotoService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Path diretorioRaiz;
	
	private Path diretorioRaizTemp;
	
	@PostConstruct
	void init(){
		Path raizAplicacao = getDefault().getPath(System.getProperty("user.home"), ".pedidovenda");
		
		diretorioRaiz = raizAplicacao.resolve("fotos-produto");
		diretorioRaizTemp = getDefault().getPath(System.getProperty("java.io.tmpdir"), "pedidovenda-fotos-temp");
		
		try {
			Files.createDirectories(diretorioRaiz);
			Files.createDirectories(diretorioRaizTemp);
		} catch (IOException e) {
			throw new RuntimeException("Problemas ao tentar criar diretórios.", e);
		}
	}
	
	public String salvarFotoTemp(String nome, byte[] conteudo) throws IOException {
		String novoNome = renomearArquivo(nome);
		Path fotoTemp = diretorioRaizTemp.resolve(novoNome);
		
		Files.write(fotoTemp, conteudo);
		
		return novoNome;
	}
	
	private String renomearArquivo(String original) {
		return UUID.randomUUID().toString() + "___" + original;
	}
	
	public void deletar(String nome) throws IOException {
		deletar(diretorioRaiz, nome);
	}
	
	public void deletarTemp(String nome) throws IOException {
		deletar(diretorioRaizTemp, nome);
	}
	
	private void deletar(Path raiz, String nome) throws IOException {
		if (StringUtils.isEmpty(nome)) {
			return;
		}
		
		Path foto = raiz.resolve(nome);
		
		if (Files.exists(foto)) {
			Files.delete(foto);
		}
	}
	
	public void recuperarFotoTemporaria(String nome) throws IOException {
		if (StringUtils.isEmpty(nome)) {
			return;
		}
		
		Path fotoTemp = diretorioRaizTemp.resolve(nome);		
		if (!Files.exists(fotoTemp)) {
			return;
		}
		
		byte[] conteudo = Files.readAllBytes(fotoTemp);		
		Path foto = diretorioRaiz.resolve(nome);
		Files.write(foto, conteudo);
		
		Files.delete(fotoTemp);
	}
	
	public byte[] recuperar(String nome) throws IOException {
		Path foto = this.diretorioRaizTemp.resolve(nome);		
		if (Files.exists(foto)) {
			return Files.readAllBytes(foto);
		} 
		
		foto = this.diretorioRaiz.resolve(nome);		
		if (Files.exists(foto)) {
			return Files.readAllBytes(foto);
		}
		
		throw new RuntimeException("Não encontrada imagem " + nome);
	}
}