package com.logica;

import java.util.List;

/**
 * @author josesei
 *
 */

public interface InsumoLiquidoDAO extends InsumoDAO<InsumoLiquido> {
	public void create(InsumoLiquido obj);
	public InsumoLiquido read(int id);
	public List<InsumoLiquido> readAll();
	public void update(InsumoLiquido newObj, InsumoLiquido oldObj);
	public boolean delete(InsumoLiquido obj);
}
