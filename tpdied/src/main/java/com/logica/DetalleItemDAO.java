package com.logica;

import java.util.List;

/**
 * @author josesei
 *
 */

public interface DetalleItemDAO extends GenericDAO<DetalleItem> {
	
	
	@Deprecated
	public void create(DetalleItem obj);
	@Deprecated
	public DetalleItem read(int id);
	@Deprecated
	public List<DetalleItem> readAll();
	@Deprecated
	public void update(DetalleItem newObj, DetalleItem oldObj);
	@Deprecated
	public boolean delete(DetalleItem obj);
	
	
	public DetalleItem read(int idInsumo, int nroOrden);
	public void create(DetalleItem obj, int nroOrden);
	public List<DetalleItem> readAllFromOrden(int nroOrden);
	public void update(int nroOrden, DetalleItem newObj, DetalleItem oldObj);
	public boolean delete(int nroOrden, DetalleItem obj);
}
