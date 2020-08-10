package com.logica;

import java.util.List;

import kotlin.Pair;


/**
 * @author josesei
 *
 */
public interface InsumoGeneralDAO extends InsumoDAO<InsumoGeneral> {
	public void create(InsumoGeneral obj);
	public InsumoGeneral read(int id);
	public List<InsumoGeneral> readAll();
	public List<Pair<Insumo, Integer>> readAllWithStock();
	public void update(InsumoGeneral newObj, InsumoGeneral oldObj);
	public boolean delete(InsumoGeneral obj);
}