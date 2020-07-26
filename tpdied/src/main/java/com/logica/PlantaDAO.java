package com.logica;

import java.util.Optional;

public interface PlantaDAO {

	
	public void AltaPlanta(Planta p);
	public void BajaPlanta(String nombrePlanta);
	public Optional<Planta> consultarPlanta(String nombrePlanta);
	
	
}
