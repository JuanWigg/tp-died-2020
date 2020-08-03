/**
 * 
 */
package com.logica;

/**
 * @author juanwigg
 *
 */
public class Magnitud {
	private int id;
	private double value;
	private Unidad unidad;
	
	public Magnitud(double value, Unidad unidad) {
		this.value = value;
		this.unidad = unidad;
	}

	public int getId() {
		return id;
	}

	public double getValue() {
		return value;
	}

	public Unidad getUnidad() {
		return unidad;
	}
	
	
	
	
}
