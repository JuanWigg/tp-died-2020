package com.logica;

/**
 * @author josesei
 *
 */

public interface StockInsumoDAO extends GenericDAO<StockInsumo> {

	/**
	 * 
	 */
	@Deprecated 
	/*
	 * DO NOT USE!
	 */
	StockInsumo read(int id);
	
	/**
	 * USE THIS INSTEAD!
	 */
	StockInsumo read(int id, String nombrePlanta);
	
}
