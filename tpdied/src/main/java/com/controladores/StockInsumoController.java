/**
 * 
 */
package com.controladores;

import java.util.ArrayList;
import java.util.HashMap;

import com.logica.Insumo;
import com.logica.InsumoGeneral;
import com.logica.Planta;
import com.logica.StockInsumo;
import com.logica.StockInsumoDAOImplSQL;

/**
 * @author Pichi
 *
 */
public class StockInsumoController {
	public ArrayList<StockInsumo> consultarStocksInsuficientes(){
		return ((ArrayList<StockInsumo>) (new StockInsumoDAOImplSQL()).consultarStocksInsuficientes());
	}
	
	
	
	public HashMap<Integer, Integer> buscarStockTotalInsumos(){
		return (new StockInsumoDAOImplSQL()).buscarStockTotalInsumos();
	}
	
	
	public void agregarStock(ArrayList<String> datosStock) {
		StockInsumo stock = new StockInsumo(new Planta(datosStock.get(0)),
				(Insumo) (new InsumoGeneral(Integer.parseInt(datosStock.get(1)))),
				Double.parseDouble(datosStock.get(2)),
				Double.parseDouble(datosStock.get(3)));
		(new StockInsumoDAOImplSQL()).addStock(stock);
	}
}
