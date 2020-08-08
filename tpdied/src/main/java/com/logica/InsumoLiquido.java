package com.logica;

/**
 * @author josesei
 *
 */

public class InsumoLiquido extends Insumo {
	
	protected double densidad;

	public double getDensidad() {
		return densidad;
	}

	public void setDensidad(double densidad) {
		this.densidad = densidad;
	}

	public InsumoLiquido(int id, String desc, Unidad unidad, double costoPorUnidad, double densidad) {
		super(id, desc, unidad, costoPorUnidad);
		this.densidad = densidad;
	}
	
	public double pesoPorUnidad() {
		switch(this.unidad) {
		case l:
			return densidad/1000d;
		case m3:
			return densidad;
		
		}
		
		return 0d;
	}
}
