package com.logica;

import java.util.List;
import java.util.Optional;
/*TO-DO
Corregir el tipo de la lista stocks
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
	
	
}
