/**
 * 
 */
package com.logica;

import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author josesei
 *
 */
public class OrdenPedidoDAOImplSQL implements OrdenPedidoDAO {

	Dotenv dotenv = Dotenv.load();
	/**
	 * 
	 */
	public OrdenPedidoDAOImplSQL() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(OrdenPedido obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrdenPedido read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdenPedido> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(OrdenPedido newObj, OrdenPedido oldObj) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(OrdenPedido obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
