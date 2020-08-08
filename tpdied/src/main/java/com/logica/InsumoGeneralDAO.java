package com.logica;

import java.util.List;

public interface InsumoGeneralDAO extends InsumoDAO<InsumoGeneral> {
	public void create(InsumoGeneral obj);
	public InsumoGeneral read(int id);
	public List<InsumoGeneral> readAll();
	public void update(InsumoGeneral newObj, InsumoGeneral oldObj);
	public boolean delete(InsumoGeneral obj);
}