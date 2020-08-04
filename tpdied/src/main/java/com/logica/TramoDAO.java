package com.logica;

import java.util.List;
import java.util.Optional;

public interface TramoDAO {
	public void AltaTramo(Tramo T);
	public void BajaTramo(Tramo T);
	public Optional<Tramo> ConsultarTramo(Integer id);
	public List<Tramo> obtenerTramos();
}
