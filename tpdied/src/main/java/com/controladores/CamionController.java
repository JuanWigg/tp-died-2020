/**
 * 
 */
package com.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Vector;

import com.logica.Camion;
import com.logica.CamionComparator;
import com.logica.CamionDAOImplSQL;
import com.logica.Modelo;

/**
 * @author Pichi
 *
 */
public class CamionController {
	public void eliminar(String patente) {
		Camion c = new Camion(patente);
		(new CamionDAOImplSQL()).bajaCamion(c);
	}

	public void modificarCamion(ArrayList<String> datosNuevosLista, Vector datosViejos) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Camion camionNuevo = new Camion(datosNuevosLista.get(0),
				Integer.parseInt(datosNuevosLista.get(1)),
				Double.parseDouble(datosNuevosLista.get(2)),
				Double.parseDouble(datosNuevosLista.get(3)),
				LocalDate.parse(datosNuevosLista.get(4), formatter),
				new Modelo(datosNuevosLista.get(5), datosNuevosLista.get(6))
				);
		
		Camion camionViejo = new Camion((String) datosViejos.elementAt(0),
				Integer.parseInt((String) datosViejos.elementAt(1)),
				Double.parseDouble((String) datosViejos.elementAt(2)),
				Double.parseDouble((String) datosViejos.elementAt(3)),
				LocalDate.parse((String) datosViejos.elementAt(4), formatter),
				new Modelo((String) datosViejos.elementAt(5), (String) datosViejos.elementAt(6))
				);
		(new CamionDAOImplSQL()).modificarCamion(camionNuevo, camionViejo);
		
	}
	
	public void altaCamion(ArrayList<String> datos) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Camion camion = new Camion(datos.get(0),
				Integer.parseInt(datos.get(1)),
				Double.parseDouble(datos.get(2)),
				Double.parseDouble(datos.get(3)),
				LocalDate.parse(datos.get(4), formatter),
				new Modelo(datos.get(5), datos.get(6))
				);
		(new CamionDAOImplSQL()).altaCamion(camion);
	}
	
	public Camion colaPrioridadCamiones() {
		PriorityQueue<Camion> cola = new PriorityQueue<Camion>(11, new CamionComparator());
		ArrayList<Camion> camiones = (new CamionDAOImplSQL().consultarCamionesDisponibles());
		cola.addAll(camiones);
		
		return cola.peek();
	}
} 
