package com.algaworks.pedidovenda.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean {

	private CartesianChartModel model;

	public void preRender() {
		this.model = new CartesianChartModel();
		
		adicionarSerie("Todos os pedidos");
		adicionarSerie("Meus pedidos");
	}
	
	private void adicionarSerie(String rotulo) {
		ChartSeries series = new ChartSeries(rotulo);
		
		series.set("1", Math.random() * 1000);
		series.set("2", Math.random() * 1000);
		series.set("3", Math.random() * 1000);
		series.set("4", Math.random() * 1000);
		series.set("5", Math.random() * 1000);
		
		this.model.addSeries(series);
	}

	public CartesianChartModel getModel() {
		return model;
	}
	
}
