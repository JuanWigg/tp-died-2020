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
} 
