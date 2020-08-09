package com.logica;

/**
 * @author josesei
 *
 */

public class StockInsumo {
	private Planta planta;
	private double cantidad;
	private double puntoDePedido;
	private Insumo insumo;
	
	public StockInsumo(Planta planta, Insumo insumo, double cantidad, double puntoDePedido) {
		this.planta = planta;
		this.insumo = insumo;
		this.cantidad = cantidad;
		this.puntoDePedido = puntoDePedido;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
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

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

}
