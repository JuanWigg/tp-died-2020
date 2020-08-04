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
	
	public List<Camion> consultarCamion(Camion c);
	public void altaCamion(Camion camion);
	public void bajaCamion(Camion camion);
	
}
