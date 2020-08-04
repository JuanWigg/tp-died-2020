package com.logica;

import java.time.LocalTime;
import java.util.List;

/*Falta
 * Corregir diagrama de clases cambiar ListaRutas
 * Agregar Metodos
 * Agregar Pesos maximos
 * */
public class Ruta {
	private Integer id;
	private List<Tramo> listaTramos;
	private LocalTime duracionTotal;
	private Integer distanciaTotal;
	//private menorPesoMaximo magnitud;
	
	public void hallarRuta(Grafo g,Planta p1,Planta p2) {
		/*
		 * if(!haycamino) return null;
		 * 
		 * */
		
	}
}
