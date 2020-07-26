package com.logica;

public class DetalleEnvio {
	private double costo;
	private Camion camion;
	private Ruta ruta;
	
	public DetalleEnvio(Camion camion, Ruta ruta) {
		this.camion = camion;
		this.ruta = ruta;
		costo = (ruta.getDistanciaTotal()*camion.getCostoPorKilometro()) + ruta.getDuracionTotal()*camion.getCostoPorHora();
		
	}

}

