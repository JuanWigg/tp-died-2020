/**
 * 
 */
package com.controladores;

import com.logica.Planta;
import com.logica.PlantaDAOImplSQL;

/**
 * @author Pichi
 *
 */
public class PlantaController {
	public void altaPlanta(String nombre) {
		(new PlantaDAOImplSQL()).AltaPlanta(new Planta(nombre));
	}
	
	
	public String[] buscarNombresPlanta() {
		return (new PlantaDAOImplSQL()).buscarNombresPlanta();
	}
}
