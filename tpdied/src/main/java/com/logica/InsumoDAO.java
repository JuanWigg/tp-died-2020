package com.logica;

import java.util.List;

public interface InsumoDAO<T extends Insumo> extends GenericDAO<T> {
	public void create(T obj);
	public T read(int id);
	public List<T> readAll();
	public void update(T newObj, T oldObj);
	public boolean delete(T obj);
	
}
