package com.controladores;

import java.util.ArrayList;
import java.util.List;

import com.logica.*;
//ver, se podria cambiar el int i = 1 y meterlo en el for al i
public class RutaController {
	public List<Ruta> calcularRutasCortas(Grafo g,Planta p1,Planta p2){
		List<Ruta> rutasDisponibles = new ArrayList<Ruta>();
		List<Ruta> rutasMasCortas; 
		int i=1;
		for (List<Planta> listaPlantas : g.caminos(p1, p2)) {
			rutasDisponibles.add(new Ruta(i, listaPlantas));
			i++;
		}
		rutasMasCortas = g.rutaMasCorta(rutasDisponibles);
		return rutasMasCortas;
	}
	public List<Ruta> calcularRutasRapidas(Grafo g,Planta p1,Planta p2){
		List<Ruta> rutasDisponibles = new ArrayList<Ruta>();
		List<Ruta> rutasMasRapidas; 
		int i=1;
		for (List<Planta> listaPlantas : g.caminos(p1, p2)) {
			rutasDisponibles.add(new Ruta(i, listaPlantas));
			i++;
		}
		rutasMasRapidas = g.rutaMasCortaTiempo(rutasDisponibles);
		return rutasMasRapidas;
	}
	
	public int altaRuta(Ruta r) {
		return (new RutaDAOImplSQL()).altaRuta(r);
	}
}
