/**
 * 
 */
package com.controladores;

import java.util.List;

import com.logica.Insumo;
import com.logica.InsumoDAOImplSQL;

/**
 * @author Pichi
 *
 */
public class InsumoController {
	public List<Insumo> consultarInsumos(){
		
		return ((new InsumoDAOImplSQL()).readAll());
		
		
	}
	
	public String[][] consultarDescripcionesInsumos() {
		List<Insumo> insumos = ((new InsumoDAOImplSQL()).readAll());
		String[][] descripciones = new String[insumos.size()+1][2];
		descripciones[0][0] = "<Ninguno>";
		for(int i=1; i<insumos.size()+1; i++) {
			descripciones[i][0] = insumos.get(i-1).getDescripcion() + "";
			descripciones[i][1] = insumos.get(i-1).getId() + "";
		}
		
		
		return descripciones;
		
	}
	
	
} 
