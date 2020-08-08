package com.logica;

/**
 * @author josesei
 *
 */

public abstract class Insumo {
	
	protected int id;
	

	protected String descripcion;
	protected Unidad unidad;
	protected double costoPorUnidad;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public double getCostoPorUnidad() {
		return costoPorUnidad;
	}

	public void setCostoPorUnidad(double costoPorUnidad) {
		this.costoPorUnidad = costoPorUnidad;
	}

	public Insumo(int id, String desc, Unidad unidad, double costoPorUnidad) {
		this.id = id;
		this.descripcion = desc;
		this.unidad = unidad;
		this.costoPorUnidad = costoPorUnidad;
	}

}
