/**
 * 
 */
package com.controladores;

import java.util.ArrayList;

import com.logica.Camion;
import com.logica.CamionDAOImplSQL;
import com.logica.Planta;
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

	public void altaTramo(ArrayList<String> datosLista) {
		// TODO Auto-generated method stub
		(new TramoDAOImplSQL()).AltaTramo(new Tramo(
					new Planta(datosLista.get(0)),
					new Planta(datosLista.get(1)),
					Integer.parseInt(datosLista.get(4)),
					Double.parseDouble(datosLista.get(3)),
					Integer.parseInt(datosLista.get(2)),
					"kg"));
		
	}
}
