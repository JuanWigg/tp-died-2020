package com.logica;

public class DetalleEnvio {
	private int id_envio;
	private double costo;
	private Camion camion;
	private Ruta ruta;
	private int nro_orden;
	
	public DetalleEnvio(Camion camion, Ruta ruta) {
		this.camion = camion;
		this.ruta = ruta;
		costo = (ruta.getDistanciaTotal()*camion.getCostoPorKilometro()) + ruta.getDuracionTotal()*camion.getCostoPorHora();
	}
	
	public DetalleEnvio(Camion camion, Ruta ruta, int nro_orden) {
		this(camion,ruta);
		this.nro_orden = nro_orden;
	}
	
	
	public DetalleEnvio() {
		// TODO Auto-generated constructor stub
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

	public int getNroOrden() {
		return nro_orden;
	}

	public void setId_envio(int id_envio) {
		this.id_envio = id_envio;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setCamion(Camion camion) {
		this.camion = camion;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public void setNro_orden(int nro_orden) {
		this.nro_orden = nro_orden;
	}
}

