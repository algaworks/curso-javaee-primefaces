package com.algaworks.pedidovenda.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algaworks.pedidovenda.service.FotoService;

@WebServlet("/produto-foto")
public class FotoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FotoService fotoService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		byte[] bytes = fotoService.recuperar(nome);
		
		resp.setHeader("Content-Type", "image/*");
		resp.setContentLength(bytes.length);
		
		resp.getOutputStream().write(bytes);
		resp.getOutputStream().flush();
	}
}