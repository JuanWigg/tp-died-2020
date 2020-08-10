package com.logica;

import java.util.List;

import kotlin.Pair;


/**
 * @author josesei
 *
 */
public interface InsumoDAO<T extends Insumo> extends GenericDAO<T> {
	public void create(T obj);
	public T read(int id);
	public List<T> readAll();
	public List<Pair<Insumo, Integer>> readAllWithStock();
	public List<Pair<Insumo, Integer>> readAllWithStockFiltered(String descripcion);
	public void update(T newObj, T oldObj);
	public boolean delete(T obj);
	
}
