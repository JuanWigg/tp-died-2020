/**
 * 
 */
package com.logica;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author juanwigg
 *
 */
public interface CamionDAO {
	
	public Optional<Camion> consultarCamion(List<Predicate> criterios);
	public void altaCamion(Camion camion);
	public void bajaCamion(Camion camion);
	
}
