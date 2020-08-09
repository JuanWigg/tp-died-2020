package com.logica;

import java.util.List;

/**
 * @author josesei
 *
 */

public interface OrdenPedidoDAO extends GenericDAO<OrdenPedido> {

	/**
	 * @return
	 */
	
	List<OrdenPedido> readAllWithStatus(EstadoOrden estado);

}
