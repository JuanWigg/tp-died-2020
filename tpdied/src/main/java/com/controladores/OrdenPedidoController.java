/**
 * 
 */
package com.controladores;

import java.util.ArrayList;


import com.logica.EstadoOrden;
import com.logica.Modelo;
import com.logica.OrdenPedido;
import com.logica.OrdenPedidoDAOImplSQL;
import com.logica.Planta;


/**
 * @author Pichi
 *
 */
public class OrdenPedidoController {
	
	
	public ArrayList<OrdenPedido> getPedidosCreados() {
		
		return (ArrayList<OrdenPedido>) (new OrdenPedidoDAOImplSQL()).readAllWithStatus(EstadoOrden.CREADA);
	}
}
