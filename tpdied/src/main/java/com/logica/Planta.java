package com.logica;

import java.util.List;
import java.util.Optional;


public class Planta  {
	public String nombre;
	
	public Planta(String nombre) {
		this.nombre = nombre;
	}
	
	public Planta() {
		
	}
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombreP) {
		this.nombre=nombreP;
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
		if(this.nombre.contentEquals(((Planta) obj).getNombre()))
			return true;
		else
			return false;
	}
}
