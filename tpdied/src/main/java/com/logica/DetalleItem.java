package com.logica;

/**
 * @author josesei
 *
 */

public class DetalleItem {
	private int nroOrden;
	private double cantidad;
	private Insumo insumo;

	public DetalleItem() {
		// TODO Auto-generated constructor stub
	}
	
	public DetalleItem(Insumo insumo, double cantidad) {
		this.insumo = insumo;
		this.cantidad = cantidad;
	}
	public int getNroOrden() {
		return nroOrden;
	}
	
	public void setNroOrden(int nroOrden) {
		this.nroOrden = nroOrden;
	}
	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

}
