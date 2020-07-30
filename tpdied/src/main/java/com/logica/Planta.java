package com.logica;

import java.util.List;
import java.util.Optional;
/*Falta
 * Corregir el tipo de la lista stocks
 * 
*/ 
public class Planta  {
	public String nombre;
	private List stocks;
	
	public Planta(String nombre) {
		this.nombre = nombre;
	}
	public Planta() {
		super();
		this.nombre=null;
	}
	public String getNombre() {
		return this.nombre;
	}
	public List getStocks() {
		return this.stocks;
	}
	
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if(this.nombre== ((Planta) obj).getNombre())
			return true;
		else
			return false;
	}
}
