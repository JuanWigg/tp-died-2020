/**
 * 
 */
package com.controladores;

import com.logica.Camion;
import com.logica.CamionDAOImplSQL;
import com.logica.Tramo;
import com.logica.TramoDAOImplSQL;

/**
 * @author Pichi
 *
 */
public class TramoController {
	public void eliminar(Tramo t) {
		(new TramoDAOImplSQL()).BajaTramo(t);
	}
}
