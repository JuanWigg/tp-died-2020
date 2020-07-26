package com.logica;

public class Modelo {
	private String nombre;
	private Marca marca;
	
	public Modelo(String nombre, Marca marca) {
		this.nombre = nombre;
		this.marca = marca;
	}
	
	public Modelo(String nombre, String marca) {
		this.nombre = nombre;
		this.marca = new Marca(marca);
	}
}
