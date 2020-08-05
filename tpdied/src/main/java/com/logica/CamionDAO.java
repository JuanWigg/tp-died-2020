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
	public void modificarCamion(Camion camionNuevo, Camion camionViejo);
	public List<Camion> buscarCamiones();
	public void altaCamion(Camion camion);
	public void bajaCamion(Camion camion);
	
}
