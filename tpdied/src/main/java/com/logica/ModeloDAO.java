/**
 * 
 */
package com.logica;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author juanwigg
 *
 */
public interface ModeloDAO {
	public Optional<Modelo> consultarModelo(List<Predicate<Modelo>> criterios);
	public void altaModelo(Modelo m);
	public void bajaModelo(Modelo m);
	
}
