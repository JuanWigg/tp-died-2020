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
	
	public double pesoPorUnidad() {
		switch(this.unidad) {
		case g:
			return peso*1000d;
		case kg:
			return this.peso;
		}
		
			return 0d;
		
		}

	public InsumoGeneral(int id, String desc, Unidad unidad, double costoPorUnidad, double peso) {
		super(id, desc, unidad, costoPorUnidad);
		this.peso = peso;
	}
	
	public InsumoGeneral(int id) {
		super(id);
	}
	public InsumoGeneral(int id, String desc, String unidad, double costoPorUnidad) {
		// TODO Auto-generated constructor stub
		super(id, desc, Unidad.valueOf(unidad), costoPorUnidad);
	}

}
