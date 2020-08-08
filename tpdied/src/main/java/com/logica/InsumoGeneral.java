package com.logica;


/**
 * @author josesei
 *
 */
public class InsumoGeneral extends Insumo {
	
	protected double peso;

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public InsumoGeneral(int id, String desc, Unidad unidad, double costoPorUnidad, double peso) {
		super(id, desc, unidad, costoPorUnidad);
		this.peso = peso;
	}

}
