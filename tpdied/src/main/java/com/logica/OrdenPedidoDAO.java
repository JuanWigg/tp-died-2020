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
	
	@Deprecated
	public void create(OrdenPedido obj);
	
	public int createAndGetKey(OrdenPedido obj);

}
