/**
 * 
 */
package com.logica;

/**
 * @author juanwigg
 *
 */
public interface DetalleEnvioDAO {
	public void altaDetalleEnvio(DetalleEnvio de);
	public void bajaDetalleEnvio(DetalleEnvio de);
	/**
	 * @param int1
	 * @return
	 */
	public DetalleEnvio consultarDetalleEnvio(int nroOrden);
}
