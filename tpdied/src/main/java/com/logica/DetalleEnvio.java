package com.logica;

public class DetalleEnvio {
	private int id_envio;
	private double costo;
	private Camion camion;
	private Ruta ruta;
	
	public DetalleEnvio(Camion camion, Ruta ruta) {
		this.camion = camion;
		this.ruta = ruta;
		costo = (ruta.getDistanciaTotal()*camion.getCostoPorKilometro()) + ruta.getDuracionTotal()*camion.getCostoPorHora();
	}
	
	
	public Ruta getRuta() {
		return this.ruta;
	}
	
	public Camion getCamion() {
		return this.camion;
	}
	
	public double getCosto() {
		return this.costo;
	}
	
	public int getId() {
		return this.id_envio;
	}
}

