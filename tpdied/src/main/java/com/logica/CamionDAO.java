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
public interface CamionDAO {
	
	public Optional<Camion> consultarCamion(List<Predicate<Camion>> criterios);
	public void altaCamion(Camion camion);
	public void bajaCamion(Camion camion);
	
}
