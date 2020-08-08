package com.logica;

public class StockInsumo {
	private Planta planta;
	private double cantidad;
	private double puntoDePedido;
	private Insumo insumo;

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public StockInsumo() {
		// TODO Auto-generated constructor stub
	}

	public double getPuntoDePedido() {
		return puntoDePedido;
	}

	public void setPuntoDePedido(double puntoDePedido) {
		this.puntoDePedido = puntoDePedido;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}
	
	public Planta getPlanta() {
		return planta;
	}

}
