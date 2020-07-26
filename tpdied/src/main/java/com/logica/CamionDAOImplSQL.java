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
public class CamionDAOImplSQL implements CamionDAO{

	public Camion buscarCamion(List<Predicate> criterios) {
		// TODO
		return null;
	}

	public Optional<Camion> consultarCamion(List<Predicate> criterios) {
		// TODO Auto-generated method stub
		return null;
	}

	public void altaCamion(String patente, Modelo modelo, LocalDate fechaCompra, double costoPorHora,
			double costoPorKilometro) {
		// TODO Auto-generated method stub
		
	}

	public void bajaCamion(Camion camion) {
		// TODO Auto-generated method stub
		
	}

}
